package com.metalheart.bot.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Event {

    private final Flow flow;
    private final Signal signal;

}
