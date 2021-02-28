package com.zeed.one.world.assessment.services;

public interface EmailService {

    void sendNotification(String to, String subject, String message);

}
