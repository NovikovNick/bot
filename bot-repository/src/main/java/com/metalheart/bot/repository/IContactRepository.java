package com.metalheart.bot.repository;

import com.metalheart.bot.model.Contact;

public interface IContactRepository {
    Contact getContact(Integer userID, String firstName, String lastName, String phoneNumber);
}
