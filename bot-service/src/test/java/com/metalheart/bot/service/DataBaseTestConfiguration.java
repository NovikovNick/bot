package com.metalheart.bot.service;

import com.metalheart.bot.repository.config.RepositoryConfiguration;
import com.metalheart.bot.service.config.TelegramBotProperties;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@TestConfiguration
@Import({RepositoryConfiguration.class})
@ComponentScan(basePackages = {"com.metalheart.bot.service"},
    excludeFilters = {@ComponentScan.Filter(type = ANNOTATION, value = Configuration.class)})
public class DataBaseTestConfiguration {


    private static DSLContext engageContext;

    @Bean
    @Primary
    public DSLContext engageDslContext() {
        if (engageContext == null) {
            engageContext = new DefaultDSLContext(getConfiguration());
        }
        return engageContext;
    }

    private DefaultConfiguration getConfiguration() {
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration();
        defaultConfiguration.set(SQLDialect.POSTGRES_9_5);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        defaultConfiguration.set(dataSource);
        return defaultConfiguration;
    }

}