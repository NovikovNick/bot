package com.metalheart.bot.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;

public class LogLayout  extends LayoutBase<ILoggingEvent> {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private ObjectMapper objectMapper;

    public LogLayout() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public String doLayout(ILoggingEvent event) {

        String log;
        try{
            LogInfo logInfo = new LogInfo()
                .setLevel(event.getLevel().toString())
                .setClazz(event.getLoggerName())
                .setMsg(event.getMessage())
                .setDate(DATE_FORMAT.format(event.getTimeStamp()))
                .setTime(TIME_FORMAT.format(event.getTimeStamp()));
            log = objectMapper.writeValueAsString(logInfo);
        }catch (Exception e){
            log = "ERROR_LOG:" + e.getMessage();
        }
        return log + CoreConstants.LINE_SEPARATOR;
    }

    @Data
    @Accessors(chain = true)
    private static class LogInfo{

        private String date;
        private String time;
        private String level;
        private String msg;
        private String clazz;
    }
}
