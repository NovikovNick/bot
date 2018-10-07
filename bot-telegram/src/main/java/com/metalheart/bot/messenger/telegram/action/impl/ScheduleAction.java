package com.metalheart.bot.messenger.telegram.action.impl;

import com.metalheart.bot.flow.Flow;
import com.metalheart.bot.flow.Transition;
import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import com.metalheart.bot.messenger.telegram.core.AbstractSessionBot;
import com.metalheart.bot.messenger.telegram.core.TelegramBot;
import com.metalheart.bot.model.ActivityCategory;
import com.metalheart.bot.model.Contact;
import com.metalheart.bot.service.IActionCategoryService;
import com.metalheart.bot.service.IActivityLogService;
import com.metalheart.bot.service.IContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ScheduleAction implements TelegramAction {

    @Autowired
    private IActionCategoryService actionCategoryService;

    @Autowired
    private IContactService contactService;

    @Autowired
    private IActivityLogService activityLogService;

    @Autowired
    private MessageSource messageSource;

    private enum Command {
        schedule_stop, schedule_log, schedule_report
    }

    @Override
    public void action(AbstractSessionBot bot, Update update) {


        Long chatId = update.getMessage().getChatId();
        Contact contact = contactService.getContact(
            update.getMessage().getFrom().getId(),
            update.getMessage().getFrom().getFirstName(),
            update.getMessage().getFrom().getLastName(),
            "");//todo: fetch phoneNumber

        List<ActivityCategory> categories = actionCategoryService.get();


        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        message.setText("ScheduleAction");



        Flow flow = new Flow();

        flow.setTransitions(Arrays.asList(
            new Transition(Command.schedule_log.toString(), Flow.STATE_START, Flow.STATE_START),
            new Transition(Command.schedule_report.toString(), Flow.STATE_START, Flow.STATE_START),
            new Transition(Command.schedule_stop.toString(), Flow.STATE_START, Flow.STATE_END)));

        flow.getHandlers().add(event -> {

            switch (Command.valueOf(event.getSignal().getTransition().getName())) {
                case schedule_stop: {
                    SendMessage msg = new SendMessage();
                    msg.enableMarkdown(true);
                    msg.setChatId(update.getMessage().getChatId());
                    msg.setText("good bye!");

                    msg.setReplyMarkup(new ReplyKeyboardRemove());

                    try {
                        bot.execute(msg);
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage(), e);
                    }
                    break;
                }

                case schedule_log:

                    categories.stream()
                        .filter(category -> category.getName().equals(event.getSignal().getParams().get("category")))
                        .findFirst()
                        .ifPresent(category -> {
                            activityLogService.log(contact.getId(), category.getId());
                        });
                    break;
                case schedule_report: {
                    StringBuilder builder = new StringBuilder("");

                    activityLogService.report(contact.getId()).forEach(report -> {

                        String startedAt = report.getStartedAt().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                        String finishAt = report.getFinishedAt() != null ?
                            report.getFinishedAt().format(DateTimeFormatter.ofPattern("HH:mm:ss")) :
                            " --- ";

                        builder.append(startedAt + " - " + finishAt + ": " + report.getCategory() + "\n");
                    });


                    SendMessage msg = new SendMessage();
                    msg.enableMarkdown(true);
                    msg.setChatId(update.getMessage().getChatId());
                    msg.setText(builder.toString());


                    try {

                        bot.execute(msg);

                    } catch (TelegramApiException e) {
                        log.error(e.getMessage(), e);
                    }
                    break;
                }
            }
        });

        flow.start();

        ((TelegramBot) bot).getSession(update).getData().put("scheduler_flow", flow);

        setButtons(categories, message);

        try {

            bot.execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void setButtons(List<ActivityCategory> categories, SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();


        categories.forEach(category -> {
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton(Command.schedule_log.toString() + ": " + category.getName()));
            keyboard.add(keyboardFirstRow);
        });

        KeyboardRow row = new KeyboardRow();
        ;

        row.add(new KeyboardButton(Command.schedule_report.toString()));
        row.add(new KeyboardButton(Command.schedule_stop.toString()));
        keyboard.add(row);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
