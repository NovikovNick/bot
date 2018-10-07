package com.metalheart.bot.messenger.telegram.action;

import com.metalheart.bot.messenger.telegram.core.AbstractSessionBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramAction {

    void action(AbstractSessionBot bot, Update update);
}
