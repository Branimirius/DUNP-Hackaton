package com.hackathon.server.service.mail;

import com.sendgrid.helpers.mail.Mail;

public interface MailService {
    void sendEmail(Mail mail);
}
