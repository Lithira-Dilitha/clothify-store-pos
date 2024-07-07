package edu.clothify.pos.bo.email.impl;

import edu.clothify.pos.bo.email.EmailSending;
import lombok.extern.java.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Properties;
@Log
public class EmailSendingImpl implements EmailSending {
    @Override
    public void sendMail(String to, String subject, String content) {
        final String from = "dilithakumara216@gmail.com";
        final String password ="uqkhwpbankgxskot";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            log.info("Email sent Successfully !");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(100000 + secureRandom.nextInt(900000));
    }
}
