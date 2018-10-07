package com.metalheart.bot.messenger.telegram.action;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramAction {

    void action(TelegramLongPollingBot bot, Update update);
}
