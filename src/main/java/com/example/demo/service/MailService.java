package com.example.demo.service;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    private final String username;
    private final String password;
    private final Properties props;

    public MailService(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        assert sf != null;
        sf.setTrustAllHosts(true);
        props.put("mail.imap.ssl.trust", "*");
        props.put("mail.imap.ssl.socketFactory", sf);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "587");
    }


    public void sendMail(String to, String subject, String dvd) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(dvd);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}