package com.metalheart.bot.flow;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class FlowTest {


    @Test
    public void test() {

        Flow flow = new Flow();


        Transition transition1 = new Transition("scheduleCategory", Flow.STATE_START, Flow.STATE_START);
        Transition transition2 = new Transition("report", Flow.STATE_START, Flow.STATE_START);
        Transition transition3 = new Transition("exit", Flow.STATE_START, Flow.STATE_END);

        flow.setTransitions(Arrays.asList(transition1, transition2, transition3));

        flow.getHandlers().add(event -> {
            if (event.getSignal().getTransition().getName().equals("scheduleCategory")) {

                System.out.println("scheduleCategory!");
                event.getFlow().getContext()
                    .put(System.currentTimeMillis() + "", event.getSignal().getParams().get("category"));

            }
        });

        flow.getHandlers().add(event -> {
            if (event.getSignal().getTransition().getName().equals("report")) {
                System.out.println("report!");
                event.getFlow().getContext().forEach((key, value) -> {
                    System.out.println(key + ": " + value);
                });
            }
        });

        flow.getHandlers().add(event -> {
            if (event.getSignal().getTransition().getName().equals("exit")) {
                System.out.println("exit!");
            }
        });

        flow.start();
        flow.signal(new Signal(transition1, Map.of("category", "Работа")));
        flow.signal(new Signal(transition1, Map.of("category", "Учеба")));
        flow.signal(new Signal(transition2));
        flow.signal(new Signal(transition3));


    }


   // @Test
    public void test1() {




    }
}
