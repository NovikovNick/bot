package com.metalheart.bot.repository;

import com.metalheart.bot.model.ActivityCategory;

import java.util.List;

public interface IActivityCategoryRepository {
    List<ActivityCategory> get();
}
