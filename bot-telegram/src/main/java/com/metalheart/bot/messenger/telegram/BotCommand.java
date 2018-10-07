package com.metalheart.bot.messenger.telegram;

import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import com.metalheart.bot.messenger.telegram.action.impl.HelpAction;
import com.metalheart.bot.messenger.telegram.action.impl.ScheduleAction;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum BotCommand {

    SCHEDULE_START("/schedule_start", ScheduleAction.class),

    //SCHEDULE_STOP("/schedule_stop", ScheduleAction.class),
   // SCHEDULE_REPORT("/schedule_report", ScheduleAction.class),
    HELP("/help", HelpAction.class);

    private String key;
    private String actionClassQualifier;

    BotCommand(String key, Class<? extends TelegramAction> actionClass) {
        this.key = key;
        this.actionClassQualifier = StringUtils.uncapitalize(actionClass.getSimpleName());
    }

    public String getKey() {
        return key;
    }

    public String getActionClassQualifier() {
        return actionClassQualifier;
    }

    public static Optional<BotCommand> fromKey(String key) {
        return Arrays.stream(BotCommand.values())
            .filter(command -> command.getKey().equals(key))
            .findFirst();
    }
}
