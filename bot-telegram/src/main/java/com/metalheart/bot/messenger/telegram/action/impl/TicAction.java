package com.metalheart.bot.messenger.telegram.action.impl;

import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import com.metalheart.bot.messenger.telegram.core.AbstractSessionBot;
import com.metalheart.bot.messenger.telegram.quartz.Sample2Job;
import com.metalheart.bot.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
public class TicAction implements TelegramAction {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public void action(AbstractSessionBot bot, Update update) {

        /*Runnable beeper = () -> {

            try {
                HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();

                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.github.io/"))
                    .build();
                HttpResponse<String> response = null;

                //response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                //log.info("Response status code: " + response.statusCode());
                //log.info("Response headers: " + response.headers());



                mutable.setValue(!mutable.booleanValue());

                SendMessage message = new SendMessage();
                message.enableHtml(true);
                message.setChatId(update.getMessage().getChatId());
                message.setText(mutable.booleanValue() ? "tic" : "<b>tac</b>");
                bot.execute(message);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        };*/

        try {
            Scheduler scheduler = schedulerFactoryBean.getObject();

            List<JobExecutionContext> jobs = scheduler.getCurrentlyExecutingJobs();

            JobKey jobKey = JobKey.jobKey("myNewJob", "myJobGroup");
            JobDataMap data = new JobDataMap();
            Contact contact = new Contact();
            contact.setId(121223);
            data.put("adsf", contact);

            JobDetail job = JobBuilder.newJob(Sample2Job.class).withIdentity(jobKey)
                .storeDurably()
                .setJobData(data)
                .build();

            //Register this job to the scheduler
            scheduler.addJob(job, true);

            //Immediately fire the Job MyJob.class
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }


}
