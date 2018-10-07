package com.metalheart.bot.messenger.telegram.core;

import com.metalheart.bot.flow.Flow;
import com.metalheart.bot.flow.Signal;
import com.metalheart.bot.flow.Transition;
import com.metalheart.bot.messenger.telegram.BotCommand;
import com.metalheart.bot.messenger.telegram.action.TelegramAction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
@Getter
public class TelegramBot extends AbstractSessionBot {

    @Autowired
    private Map<String, TelegramAction> actionMap;


    public TelegramBot(String botUsername, String botToken, DefaultBotOptions options) {
        super(botUsername, botToken, options);
    }

    //todo: Context
    //todo: schedule
    //todo: quiz
    //todo: finances
    //todo: task management
    //todo: traveler
    //todo: game


    @Override
    public void onUpdateReceived(Update update) {


        TelegramSession session = getSession(update);

        if (session.getData().containsKey("scheduler_flow")) {
            Flow flow = (Flow) session.getData().get("scheduler_flow");

            if (!flow.getCurrentState().equals(Flow.STATE_END)) {

                String msg = update.getMessage().getText();
                for (Transition transition : flow.getAvailableTransitions()) {


                    if (transition.getName().equals("schedule_stop") && msg.equals("schedule_stop")) {
                        flow.signal(new Signal(transition));
                        return;
                    } else if (transition.getName().equals("schedule_report") && msg.equals("schedule_report")) {
                        flow.signal(new Signal(transition));
                        return;
                    } else if (msg.startsWith("schedule_log")) {
                        flow.signal(new Signal(transition, Map.of("category", msg.split(":")[1].trim())));
                        return;
                    }

                }

            }

        }


        BotCommand.fromKey(update.getMessage().getText()).ifPresent(command -> {
            actionMap.get(command.getActionClassQualifier()).action(this, update);
        });
    }


}
