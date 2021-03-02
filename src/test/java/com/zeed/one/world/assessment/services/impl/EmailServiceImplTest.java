package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.services.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailService emailService = new EmailServiceImpl();

    @Mock
    private JavaMailSender javaMailSender;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(emailService, "from", "test@email.com");
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(new JavaMailSenderImpl().createMimeMessage());
    }

    @Test
    public void sendNotification() {
        emailService.sendNotification("testemail@gmail.com", "Test", "Hello");
    }
}