package com.metalheart.bot.messenger.telegram.core;

import com.metalheart.bot.model.Contact;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class TelegramSession {

    private Contact contact;
    private Map<String, Object> data = new HashMap<>();
    private LocalDateTime expiredAt;
}
