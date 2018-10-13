package com.metalheart.bot.messenger.telegram.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sample2Job implements Job {


    public void execute(JobExecutionContext context) throws JobExecutionException {


        log.info("it works! - " + context.getMergedJobDataMap().get("adsf"));
    }
}
