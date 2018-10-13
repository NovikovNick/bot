package com.metalheart.bot.service.impl;

import com.metalheart.bot.model.session.Session;
import com.metalheart.bot.service.IDateService;
import com.metalheart.bot.service.ISessionService;
import com.metalheart.bot.service.config.TelegramBotProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class SessionService implements ISessionService {


    @Autowired
    private IDateService dateService;

    @Autowired
    private TelegramBotProperties config;


    private Map<Integer, Session> sessions;

    @Override
    public Session getSession() {


        return null;
    }

}
