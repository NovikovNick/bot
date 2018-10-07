package com.metalheart.bot.messenger.telegram.config;


import com.metalheart.bot.messenger.telegram.core.TelegramBot;
import com.metalheart.bot.repository.config.RepositoryConfiguration;
import com.metalheart.bot.service.config.ServiceConfiguration;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Configuration
@ComponentScan(basePackages = {"com.metalheart.bot.messenger.telegram.action"})
@Import({RepositoryConfiguration.class, ServiceConfiguration.class})
public class TelegramConfiguration {

    @Bean
    public TelegramBot getTelegramBot(){

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        return new TelegramBot(botOptions);
    }

}
