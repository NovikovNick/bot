package com.metalheart.bot.service;

import com.metalheart.bot.model.Contact;

public interface IContactService {

    Contact getContact(Integer userID, String firstName, String lastName, String phoneNumber);
}
