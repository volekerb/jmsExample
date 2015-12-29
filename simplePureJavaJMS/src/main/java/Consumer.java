import javax.jms.*;

public class Consumer {

    public static void consumeMessage() {
        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("Kakashka");
            MessageConsumer consumer = session.createConsumer(destination);
            connection.start();

            System.out.println("Start listen for kakashkas Queue from Consumer");
            long now = System.currentTimeMillis();
            do {
                TextMessage m = (TextMessage) consumer.receive();
                System.out.println(m.getText() + " timestamp=" + m.getJMSTimestamp());

            } while (now + 1000 * 60 * 10 > System.currentTimeMillis());
            System.out.println("End listen kakashkas Queue from Consumer");

            session.close();
            connection.close();
            consumer.close();

        } catch (JMSException ex) {
            System.out.println("Error = " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        Consumer.consumeMessage();
    }
}
