package com.metalheart.bot.service;

import com.metalheart.bot.model.ActivityLogReport;

import java.util.List;

public interface IActivityLogService {
    void log(Integer contactId, Integer categoryId);

    List<ActivityLogReport> report(Integer contactId);
}
