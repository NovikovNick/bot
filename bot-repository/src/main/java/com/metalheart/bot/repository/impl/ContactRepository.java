package com.metalheart.bot.repository.impl;

import com.metalheart.bot.db.tables.records.ContactRecord;
import com.metalheart.bot.model.Contact;
import com.metalheart.bot.repository.IContactRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.metalheart.bot.db.tables.Contact.CONTACT;

@Component
public class ContactRepository implements IContactRepository {

    @Autowired
    private DSLContext dsl;

    @Override
    public Contact getContact(Integer userID, String firstName, String lastName, String phoneNumber) {


        SelectConditionStep<Record> selectById = dsl.select().from(CONTACT).where(CONTACT.ID.eq(userID));

        if (!dsl.fetchExists(selectById)) {

            Record record = new ContactRecord();
            record.set(CONTACT.ID, userID);
            record.set(CONTACT.FIRST_NAME, firstName);
            record.set(CONTACT.LAST_NAME, lastName);
            record.set(CONTACT.PHONE_NUMBER, phoneNumber);

            dsl.insertInto(CONTACT)
                .set(record)
                .execute();
        }

        return selectById.limit(1).fetchOneInto(Contact.class);
    }
}
