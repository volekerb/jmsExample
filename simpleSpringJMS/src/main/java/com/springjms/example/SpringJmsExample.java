package com.springjms.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsExample {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Producer jmsMessageSender = (Producer)ctx.getBean("producer");

        // send to default destination
        jmsMessageSender.send("Catch kakashka!");

        // send to a code specified destination
        ActiveMQQueue queue = new ActiveMQQueue("AnotherDest");
        jmsMessageSender.send(queue, "Catch kakashka in another dest!");

        // sleep for 10 seconds
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((ClassPathXmlApplicationContext)ctx).close();
    }

}
