package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.services.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailService emailService = new EmailServiceImpl();

    @Test
    public void sendNotification() {
        emailService.sendNotification("testemail@gmail.com", "Test", "Hello");
    }
}