package com.metalheart.bot.flow;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "uuid")
public class Transition {

    private final UUID uuid;
    private final String name;
    private final State source;
    private final State target;

    public Transition(String name, State source, State target) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.source = source;
        this.target = target;
    }
}
