package com.metalheart.bot.flow;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class Signal {

    private final Transition transition;
    private final Map<String, Object> params;

    public Signal(Transition transition, Map<String, Object> params) {
        this.transition = transition;
        this.params = Collections.unmodifiableMap(params);
    }

    public Signal(Transition transition) {
        this.transition = transition;
        this.params = null;
    }

}
