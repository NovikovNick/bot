package com.metalheart.bot.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityLogReport {

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String category;
}
