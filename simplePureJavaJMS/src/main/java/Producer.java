import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Producer {

    public static void sendMessage() {
        final Logger log = LoggerFactory.getLogger(Producer.class);
        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("Kakashka");
            MessageProducer producer = session.createProducer(destination);
            connection.start();

            TextMessage message = session.createTextMessage();
            log.info("Caught Kakashka (" + System.currentTimeMillis() + ") from Producer");
            log.info("Sent from Producer");
            producer.send(message);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException ex) {
            log.warn("Error = " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        Producer.sendMessage();
    }
}
