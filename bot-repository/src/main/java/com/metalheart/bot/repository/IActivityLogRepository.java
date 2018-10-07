package com.metalheart.bot.repository;

import com.metalheart.bot.model.ActivityLogReport;

import java.util.List;

public interface IActivityLogRepository {
    void log(Integer contactId, Integer categoryId);

    List<ActivityLogReport> report(Integer contactId);
}
