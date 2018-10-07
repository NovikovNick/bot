package com.metalheart.bot.repository.impl;

import com.metalheart.bot.model.ActivityLogReport;
import com.metalheart.bot.repository.IActivityLogRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.metalheart.bot.db.Tables.ACTIVITY_CATEGORY;
import static com.metalheart.bot.db.Tables.ACTIVITY_LOG;

@Component
public class ActivityLogRepository implements IActivityLogRepository {


    @Autowired
    private DSLContext dsl;

    @Override
    public void log(Integer contactId, Integer categoryId) {

        Integer lastActivityLogId = dsl.select(ACTIVITY_LOG.ID).from(ACTIVITY_LOG)
            .where(ACTIVITY_LOG.CONTACT_ID.eq(contactId))
            .orderBy(ACTIVITY_LOG.STARTED_AT.desc())
            .limit(1)
            .fetchOneInto(Integer.class);


        Timestamp now = Timestamp.from(Instant.now());


        if (lastActivityLogId != null) {
            dsl.update(ACTIVITY_LOG)
                .set(ACTIVITY_LOG.FINISHED_AT, now)
                .where(ACTIVITY_LOG.ID.eq(lastActivityLogId))
                .execute();
        }

        dsl.insertInto(ACTIVITY_LOG, ACTIVITY_LOG.STARTED_AT, ACTIVITY_LOG.ACTIVITY_CATEGORY_ID, ACTIVITY_LOG.CONTACT_ID)
            .values(now, categoryId, contactId)
            .execute();

    }

    @Override
    public List<ActivityLogReport> report(Integer contactId) {

        return dsl.select(
            ACTIVITY_LOG.STARTED_AT,
            ACTIVITY_LOG.FINISHED_AT,
            ACTIVITY_CATEGORY.NAME.as("category"))
            .from(ACTIVITY_LOG)
            .join(ACTIVITY_CATEGORY).on(ACTIVITY_LOG.ACTIVITY_CATEGORY_ID.eq(ACTIVITY_CATEGORY.ID))
            .where(ACTIVITY_LOG.CONTACT_ID.eq(contactId))
            .orderBy(ACTIVITY_LOG.STARTED_AT)
            .fetchInto(ActivityLogReport.class);

    }


}
