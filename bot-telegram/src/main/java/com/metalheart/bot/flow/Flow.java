package com.metalheart.bot.flow;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Flow implements Serializable {

    public static final State STATE_START = new State("START");
    public static final State STATE_END = new State("END");

    private List<State> states = Arrays.asList(STATE_START, STATE_END);
    private List<Transition> transitions;
    private Map<String, Object> context;

    private List<EventHandler> handlers = new LinkedList<>();

    private State currentState;

    public List<Transition> getAvailableTransitions(){

        return transitions.stream()
            .filter(transition -> transition.getSource().equals(currentState))
            .collect(Collectors.toList());
    }

    public void signal(Signal signal){

        if(!signal.getTransition().getSource().equals(currentState)){
            throw new RuntimeException("wrong signal");
        }

        Event event = new Event(this, signal);
        handlers.forEach(handler -> handler.handle(event));

        currentState = signal.getTransition().getTarget();
    }

    public Map<String, Object> getContext() {
        if (context == null) {
            context = new HashMap<>();
        }
        return context;
    }

    public void start(){
        setCurrentState(STATE_START);
    }

    public void stop(){
        setCurrentState(STATE_END);
    }

}
