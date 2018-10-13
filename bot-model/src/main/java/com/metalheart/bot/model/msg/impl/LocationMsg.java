package com.metalheart.bot.model.msg.impl;

import com.metalheart.bot.model.msg.IMessage;
import lombok.Data;

@Data
public class LocationMsg implements IMessage {

    private final Float latitude;
    private final Float longitude;
}
