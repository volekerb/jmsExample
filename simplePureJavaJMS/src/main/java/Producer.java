import javax.jms.*;

public class Producer {

    public static void sendMessage() {

        try {
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();
            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("Kakashka");
            MessageProducer producer = session.createProducer(destination);
            connection.start();

            TextMessage message = session.createTextMessage();
            message.setText("Caught Kakashka (" + System.currentTimeMillis() + ") from Producer");

            System.out.println("Sent from Producer");
            producer.send(message);

            // close everything
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException ex) {
            System.out.println("Error = " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        Producer.sendMessage();
    }
}
