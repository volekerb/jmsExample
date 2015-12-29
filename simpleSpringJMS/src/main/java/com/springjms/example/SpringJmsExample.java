package com.springjms.example;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJmsExample {

    public static void main(String[] args) {
        // init spring context
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        // get bean from context
        Producer jmsMessageSender = (Producer)ctx.getBean("producer");

        // send to default destination
        jmsMessageSender.send("Catch kakashka!");

        // send to a code specified destination
//        ActiveMQQueue queue = new ActiveMQQueue("AnotherDest");
//        jmsMessageSender.send(queue, "Catch kakashka in another dest!");

        // sleep for 1 second
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // close spring application context
        ((ClassPathXmlApplicationContext)ctx).close();
    }

}
