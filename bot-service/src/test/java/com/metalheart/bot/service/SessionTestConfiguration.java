package com.metalheart.bot.service;

import com.metalheart.bot.repository.config.RepositoryConfiguration;
import com.metalheart.bot.service.config.ServiceConfiguration;
import com.metalheart.bot.service.config.TelegramBotProperties;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@TestConfiguration
@Import({RepositoryConfiguration.class, ServiceConfiguration.class})
public class SessionTestConfiguration {


    @Bean
    @Primary
    public TelegramBotProperties getTelegramBotProperties(){
        TelegramBotProperties properties = new TelegramBotProperties();
        properties.setSessionTimeout(Duration.of(30, ChronoUnit.MINUTES));
        return properties;
    }

    @Bean
    @Primary
    public DSLContext engageDslContext() {
       return  new DefaultDSLContext(new DefaultConfiguration());
    }

}