package com.metalheart.bot.messenger.telegram.config;


import com.metalheart.bot.messenger.telegram.core.TelegramBot;
import com.metalheart.bot.repository.config.RepositoryConfiguration;
import com.metalheart.bot.service.config.ServiceConfiguration;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.proxy.type:null}")
    private String proxyType;
    @Value("${telegram.bot.proxy.host:null}")
    private String proxyHost;
    @Value("${telegram.bot.proxy.port:0}")
    private Integer proxyPort;

    @Bean
    public TelegramBot getTelegramBot(){

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        if (proxyType != null && proxyHost != null && proxyPort != 0) {
            botOptions.setProxyType(DefaultBotOptions.ProxyType.valueOf(proxyType));
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(proxyPort);
        }

        return new TelegramBot(botUsername, botToken, botOptions);
    }

}
