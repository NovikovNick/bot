package com.metalheart.bot.repository.impl;

import com.metalheart.bot.model.ActivityCategory;
import com.metalheart.bot.repository.IActivityCategoryRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.metalheart.bot.db.Tables.ACTIVITY_CATEGORY;

@Component
public class ActivityCategoryRepository implements IActivityCategoryRepository {


    @Autowired
    private DSLContext dsl;

    @Override
    public List<ActivityCategory> get(){
        return dsl.select().from(ACTIVITY_CATEGORY).fetchInto(ActivityCategory.class);
    }
}
