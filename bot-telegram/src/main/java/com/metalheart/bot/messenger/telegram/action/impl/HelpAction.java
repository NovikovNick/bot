package com.metalheart.bot.messenger.telegram.action.impl;

import com.metalheart.bot.messenger.telegram.BotCommand;
import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class HelpAction implements TelegramAction {


    @Override
    public void action(TelegramLongPollingBot bot, Update update) {

        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        message.setText("list of all available command");

        setButtons(message);

        try {

            bot.execute(message);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void setButtons(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        Arrays.stream(BotCommand.values()).forEach(command -> {
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(new KeyboardButton(command.getKey()));
            keyboard.add(keyboardFirstRow);
        });

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
