package com.metalheart.bot.model.session;

import com.metalheart.bot.model.Contact;
import lombok.Data;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
public class Session {
    private Contact contact;
    private Map<String, Object> data = new HashMap<>();
    private Instant expiredAt;
}
