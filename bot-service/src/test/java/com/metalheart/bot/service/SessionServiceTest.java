package com.metalheart.bot.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SessionTestConfiguration.class})
@TestPropertySource("classpath:application.yml")
public class SessionServiceTest {

    @Autowired
    private ISessionService service;

    @Test
    public void test() throws Exception{


        ZoneId msc = ZoneId.of("Europe/Moscow");


        ZonedDateTime zdt = ZonedDateTime.now(msc);

        long date = zdt.plusHours(-3).toInstant().toEpochMilli();
        Clock systemUTC = Clock.systemUTC();
        Instant inst = systemUTC.instant();


        long epochSecond = inst.toEpochMilli();
        int totalSeconds = (int) (epochSecond - date);
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(totalSeconds / 1000);

        OffsetDateTime offset = OffsetDateTime.of(LocalDateTime.ofInstant(inst, systemUTC.getZone()), zoneOffset);


        System.out.println(offset);

    }



}
