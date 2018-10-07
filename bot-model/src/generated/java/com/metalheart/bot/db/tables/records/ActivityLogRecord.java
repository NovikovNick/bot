/*
 * This file is generated by jOOQ.
*/
package com.metalheart.bot.db.tables.records;


import com.metalheart.bot.db.tables.ActivityLog;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityLogRecord extends UpdatableRecordImpl<ActivityLogRecord> implements Record5<Integer, Timestamp, Timestamp, Integer, Integer> {

    private static final long serialVersionUID = -1512990812;

    /**
     * Setter for <code>public.activity_log.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.activity_log.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.activity_log.started_at</code>.
     */
    public void setStartedAt(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.activity_log.started_at</code>.
     */
    public Timestamp getStartedAt() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.activity_log.finished_at</code>.
     */
    public void setFinishedAt(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.activity_log.finished_at</code>.
     */
    public Timestamp getFinishedAt() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.activity_log.activity_category_id</code>.
     */
    public void setActivityCategoryId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.activity_log.activity_category_id</code>.
     */
    public Integer getActivityCategoryId() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.activity_log.contact_id</code>.
     */
    public void setContactId(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.activity_log.contact_id</code>.
     */
    public Integer getContactId() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Timestamp, Timestamp, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Timestamp, Timestamp, Integer, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return ActivityLog.ACTIVITY_LOG.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return ActivityLog.ACTIVITY_LOG.STARTED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return ActivityLog.ACTIVITY_LOG.FINISHED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return ActivityLog.ACTIVITY_LOG.ACTIVITY_CATEGORY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return ActivityLog.ACTIVITY_LOG.CONTACT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getStartedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getFinishedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getActivityCategoryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getContactId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord value2(Timestamp value) {
        setStartedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord value3(Timestamp value) {
        setFinishedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord value4(Integer value) {
        setActivityCategoryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord value5(Integer value) {
        setContactId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityLogRecord values(Integer value1, Timestamp value2, Timestamp value3, Integer value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityLogRecord
     */
    public ActivityLogRecord() {
        super(ActivityLog.ACTIVITY_LOG);
    }

    /**
     * Create a detached, initialised ActivityLogRecord
     */
    public ActivityLogRecord(Integer id, Timestamp startedAt, Timestamp finishedAt, Integer activityCategoryId, Integer contactId) {
        super(ActivityLog.ACTIVITY_LOG);

        set(0, id);
        set(1, startedAt);
        set(2, finishedAt);
        set(3, activityCategoryId);
        set(4, contactId);
    }
}
