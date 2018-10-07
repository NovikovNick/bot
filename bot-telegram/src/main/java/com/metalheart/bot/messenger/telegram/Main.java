package com.metalheart.bot.messenger.telegram;

import com.metalheart.bot.messenger.telegram.core.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class Main {



    public static void main(String[] args) {
        try {

            /*System.getProperties().put("proxySet", "true");
            System.getProperties().put("socksProxyHost", "127.0.0.1");
            System.getProperties().put("socksProxyPort", "9150");*/

            ApiContextInitializer.init();



            ApplicationContext ctx = SpringApplication.run(Main.class, args);
            new TelegramBotsApi().registerBot(ctx.getBean(TelegramBot.class));

            log.info("ready");


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
