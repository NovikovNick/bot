package com.metalheart.bot.model.msg.impl;

import com.metalheart.bot.model.msg.IMessage;
import lombok.Data;

@Data
public class ContactMsg implements IMessage {

    private final Integer userId;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
}
