package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.exceptions.MailServiceException;
import fon.njt.cvbuilderapi.model.NotificationEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Slf4j
public class MailService {
    private final String username;
    private final MailContentBuilder mailContentBuilder;

    @Autowired
    public MailService(@Value("${spring.mail.username}") String username, MailContentBuilder mailContentBuilder) {
    this.username = username;
    this.mailContentBuilder = mailContentBuilder;
    }

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        System.out.println(username);
        final String password = "duleprojekat1203";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.connectiontimeout", "t1"); //vlado dodao ovu i narednu liniju koda da bi video u cemu je problem sa linijom
        prop.put("mail.smtp.timeout", "t2"); //Transport.send(message) ove klase
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cvBuilderApp@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(notificationEmail.getRecipient())
            );
            message.setSubject(notificationEmail.getSubject());
            message.setContent(mailContentBuilder.build(notificationEmail.getBody()), "text/html");
            Transport.send(message);
            System.out.println("Done");
            log.info("Activation email sent! Recipient:" + notificationEmail.getRecipient());

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MailServiceException("Exception occurred when sending email.");
        }

    }
}
