package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class EmailServiceImpl implements EmailService {

    @Value("${smtp.from:noreply@oneworldaccurracy.com}")
    private String from;

    @Autowired
    private JavaMailSender emailSender;

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(2);

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class.getName());

    public void sendNotification(String to, String subject, String message) {
        EXECUTOR.execute(()-> {
            try {
                MimeMessage messag = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(messag, false);
                helper.setTo(to);
                helper.setFrom(from);
                helper.setText(message, true);
                helper.setSubject(subject);
                emailSender.send(messag);
            } catch (Exception e) {
                LOGGER.error("Error occurred while sending mail due to ", e);
            }
        });
    }
}