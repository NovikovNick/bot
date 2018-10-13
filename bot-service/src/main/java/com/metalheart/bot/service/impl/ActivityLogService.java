package com.metalheart.bot.service.impl;

import com.metalheart.bot.model.ActivityLogReport;
import com.metalheart.bot.repository.IActivityLogRepository;
import com.metalheart.bot.service.IActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ActivityLogService implements IActivityLogService {


    @Autowired
    private IActivityLogRepository activityLogRepository;

    @Transactional
    @Override
    public void log(Integer contactId, Integer categoryId) {
        activityLogRepository.log(contactId, categoryId);
    }

    @Override
    public List<ActivityLogReport> report(Integer contactId) {
        return activityLogRepository.report(contactId);
    }
}
