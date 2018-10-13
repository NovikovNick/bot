package com.metalheart.bot.messenger.telegram.quartz;

import kotlin.ranges.IntRange;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Range;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SampleJob implements Job {


    public void execute(JobExecutionContext context) throws JobExecutionException {


        try {

            for (int i = 0; i < 20; i++) {
                log.info("eeee!");
                TimeUnit.SECONDS.sleep(3);
            }


        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
