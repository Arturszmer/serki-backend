package com.example.serki.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class DummyMailSender implements MailSender {

    private final Mails mails;
    public DummyMailSender(Mails mails){

        this.mails = mails;
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        mails.add(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }
}
