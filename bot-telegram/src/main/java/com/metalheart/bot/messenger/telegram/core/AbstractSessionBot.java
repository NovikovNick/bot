package com.metalheart.bot.messenger.telegram.core;

import com.metalheart.bot.model.Contact;
import com.metalheart.bot.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractSessionBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.session.lifetime.minutes:30}")
    private Integer sessionLifetime;

    @Autowired
    private IContactService contactService;

    private Map<Integer, TelegramSession> sessions;

    public AbstractSessionBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public TelegramSession getSession(Update update) {

        Integer contactId = update.getMessage().getFrom().getId();

        if (sessions == null) {
            sessions = new ConcurrentHashMap();
        }

        TelegramSession session;
        if (!sessions.containsKey(contactId)) {

            Contact contact = contactService.getContact(
                contactId,
                update.getMessage().getFrom().getFirstName(),
                update.getMessage().getFrom().getLastName(),
                "");//todo: fetch phoneNumber

            session = new TelegramSession();
            session.setContact(contact);

        } else {
            session = sessions.get(contactId);
        }

        session.setExpiredAt(LocalDateTime.now().plusMinutes(sessionLifetime));
        sessions.put(contactId, session);


        sessions.entrySet().forEach(entry -> {
            if (entry.getValue().getExpiredAt().isBefore(LocalDateTime.now())) {
                sessions.remove(entry.getKey());
            }
        });

        return session;
    }
}
