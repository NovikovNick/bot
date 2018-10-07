package com.metalheart.bot.flow;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "uuid")
public class State {


    private final UUID uuid;
    private final String name;

    public State(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }
}
