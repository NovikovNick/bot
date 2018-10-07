package com.metalheart.bot.service.impl;

import com.metalheart.bot.model.ActivityCategory;
import com.metalheart.bot.repository.IActivityCategoryRepository;
import com.metalheart.bot.service.IActionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionCategoryService implements IActionCategoryService {

    @Autowired
    private IActivityCategoryRepository categoryRepository;

    @Override
    public List<ActivityCategory> get() {
        return categoryRepository.get();
    }
}
