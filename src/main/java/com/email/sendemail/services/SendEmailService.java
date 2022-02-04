package com.email.sendemail.services;

import com.email.sendemail.models.EmailModel;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class SendEmailService {

    public void sendmail(EmailModel emailModel) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cabyoursolutions@gmail.com", "dtvqhyuafjxajrik");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("cabyoursolutions@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailModel.getTo()));
        msg.setSubject(emailModel.getSubject());
        msg.setContent(emailModel.getContent(), "text/html");
        msg.setSentDate(new Date());

//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("ReMBuSS", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }


}
