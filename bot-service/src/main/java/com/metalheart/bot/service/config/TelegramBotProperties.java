package com.metalheart.bot.service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;

@Getter
@Setter
@ToString
@Validated
@Component
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotProperties {


    @NotEmpty
    private String username;

    @NotEmpty
    private String token;

    private Duration sessionTimeout;

    private Proxy proxy;

    @Getter
    @Setter
    @ToString
    public static class Proxy {

        private String type;
        private String host;
        private Integer port;
    }
}