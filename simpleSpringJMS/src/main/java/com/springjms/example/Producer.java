package com.springjms.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final String text) {

        this.jmsTemplate.send(session -> {
            TextMessage message = session.createTextMessage(text);
            message.setJMSReplyTo(new ActiveMQQueue("ActiveMQsample"));
            return message;
        });
    }

    public void sendText(final String text) {
        this.jmsTemplate.convertAndSend(text);
    }

    public void send(final ActiveMQQueue dest, final String text) {

        this.jmsTemplate.send((javax.jms.Destination) dest, (MessageCreator) session -> {
            TextMessage message = session.createTextMessage(text);
            return message;
        });
    }
}
