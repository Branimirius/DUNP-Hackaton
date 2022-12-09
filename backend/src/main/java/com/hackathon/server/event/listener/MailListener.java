package com.hackathon.server.event.listener;

import com.hackathon.server.model.user.User;
import com.hackathon.server.service.mail.MailService;
import com.hackathon.server.util.mail.MailContentBuilder;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 Basic sending of a mail.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@DependsOn("mailConfig")
public class MailListener {

    private static final String NO_REPLY = "noreply_novi-pazar@hackathon.rs";
    private static final String USER_PASSWORD_SUBJECT = "Hackathon | Novi Pazar";

    private final MailContentBuilder mailContentBuilder;

    private final MailService mailService;

    @EventListener
    public void handleNewUserMailEvent(NewUserMailEvent newUserMailEvent) {
        User user = newUserMailEvent.getUser();
        if (user.getEmail().isEmpty()) {
            log.info("No email, skipping password reset mail for: {} {}",
                    user.getFirstName(),
                    user.getLastName());
            return;
        }
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("user", user);
        String tokenUrl;
        tokenUrl = "https://localhost:4200/#/authentication/set-password/" + newUserMailEvent.getToken() + "&" + user.getUsername();
        variables.put("tokenUrl", tokenUrl);
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 30);
        Date currentDatePlus30Days = c.getTime();
        variables.put("tokenExpDate", dateFormat.format(currentDatePlus30Days));
        String content = mailContentBuilder.buildNewUserPasswordMail(variables);

        try{
            FileWriter myWriter = new FileWriter("/home/danilo/Desktop/mail/mail.html");
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

//        Mail mail = new Mail(new Email(NO_REPLY), USER_PASSWORD_SUBJECT, new Email(user.getEmail()), new Content("text/html", content));
//        mailService.sendEmail(mail);

    }

}