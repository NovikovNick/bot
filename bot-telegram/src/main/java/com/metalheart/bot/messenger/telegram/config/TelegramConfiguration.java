package com.metalheart.bot.messenger.telegram.config;


import com.metalheart.bot.messenger.telegram.core.TelegramBot;
import com.metalheart.bot.repository.config.RepositoryConfiguration;
import com.metalheart.bot.service.config.TelegramBotProperties;
import com.metalheart.bot.service.config.ServiceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.util.Optional;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.metalheart.bot.messenger.telegram.action"})
@Import({RepositoryConfiguration.class, ServiceConfiguration.class})
public class TelegramConfiguration {

    @Autowired
    private TelegramBotProperties config;

    @Bean
    public TelegramBot getTelegramBot(){

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);


        Optional.ofNullable(config.getProxy()).ifPresent(proxy -> {

            log.info("Use proxy {}", proxy);

            botOptions.setProxyType(DefaultBotOptions.ProxyType.valueOf(proxy.getType()));
            botOptions.setProxyHost(proxy.getHost());
            botOptions.setProxyPort(proxy.getPort());
        });


        return new TelegramBot(config.getUsername(), config.getToken(), botOptions);
    }

}
