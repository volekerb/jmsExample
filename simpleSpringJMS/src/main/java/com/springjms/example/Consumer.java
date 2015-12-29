package com.springjms.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class Consumer implements SessionAwareMessageListener {

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        // This is the received message
        System.out.println("Received message: "+ message.toString());

        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("Acknowledgement: olololo! Kakashka received");
        textMessage.setReplyTo(new ActiveMQQueue("KakashkaReply"));

        // Message send back to the replyTo address of the income message.
        MessageProducer producer = session.createProducer(message.getJMSReplyTo());
        producer.send(textMessage);
    }
}
