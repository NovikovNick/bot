package com.metalheart.bot.service.impl;

import com.metalheart.bot.model.Contact;
import com.metalheart.bot.repository.IContactRepository;
import com.metalheart.bot.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactService implements IContactService {

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public Contact getContact(Integer userID, String firstName, String lastName, String phoneNumber) {
        return contactRepository.getContact(userID, firstName, lastName, phoneNumber);
    }
}
