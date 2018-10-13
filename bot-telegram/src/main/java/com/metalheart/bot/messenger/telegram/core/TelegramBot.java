package com.metalheart.bot.messenger.telegram.core;

import com.metalheart.bot.messenger.telegram.BotCommand;
import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import com.metalheart.bot.model.msg.IMessage;
import com.metalheart.bot.model.msg.impl.ContactMsg;
import com.metalheart.bot.model.msg.impl.LocationMsg;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;

@Slf4j
@Getter
public class TelegramBot extends AbstractSessionBot {

    @Autowired
    private Map<String, TelegramAction> actionMap;


    public TelegramBot(String botUsername, String botToken, DefaultBotOptions options) {
        super(botUsername, botToken, options);
    }

    //todo: write docs
    //todo: session
    //todo: add inline buttons with l10n
    //todo: Context fix undo/redo functional
    //todo: pretty report without duplicates
    //todo: fix date time
    //todo: category log input - onstart, onend
    //todo: schedule rebase into master
    //todo: write README.md
    //todo: quiz
    //todo: finances
    //todo: task management
    //todo: traveler
    //todo: game

    //todo: quartz
    //todo: jpa
    //todo: mail

    @Override
    public void onUpdateReceived(Update update) {

        TelegramSession session = getSession(update);

        IMessage msg = getMessage(update);

        Integer date = update.getMessage().getDate();

        Clock systemUTC = Clock.systemUTC();
        Instant inst = systemUTC.instant();



        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds((int) (inst.toEpochMilli() - date) / 1000);

        OffsetDateTime offset = OffsetDateTime.of(LocalDateTime.ofInstant(inst, systemUTC.getZone()), zoneOffset);


        log.info(offset.toString());


        if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("1")) {
                String answer = "Updated message text";

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                {
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();

                    rowInline.add(new InlineKeyboardButton().setText("<-").setCallbackData("undo"));
                    rowInline.add(new InlineKeyboardButton().setText("->").setCallbackData("redo"));
                    rowsInline.add(rowInline);
                }

                markupInline.setKeyboard(rowsInline);


                EditMessageText new_message = new EditMessageText()
                    .setChatId(chat_id)
                    .setMessageId(toIntExact(message_id))
                    .setReplyMarkup(markupInline)
                    .setText(answer);





                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


        if (session.getData().containsKey("scheduler_flow")) {
            //Flow flow = (Flow) session.getData().get("scheduler_flow");
            //flow.getAvailableTransitions();
            //flow.signal(new Signal(transition));
        }


        BotCommand.fromKey(update.getMessage().getText()).ifPresent(command -> {
            actionMap.get(command.getActionClassQualifier()).action(this, update);
        });
    }


    private IMessage getMessage(Update update) {

        Message message = update.getMessage();


        if (message.getContact() != null) {

            Contact contact = message.getContact();

            return new ContactMsg(
                contact.getUserID(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber());
        }
        if (message.getLocation() != null) {
            Location location = message.getLocation();
            return new LocationMsg(
                location.getLatitude(),
                location.getLongitude());
        }

        if(message.getText() != null){

        }
        return null;
    }


}
