import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.logging.Logger;

public class Consumer implements MessageListener {

    private static final Logger log = Logger.getLogger(Consumer.class.getName());

    public static void main(String[] args) throws NamingException, JMSException {
        Connection connection = null;
        try {
            System.out.println("Create JNDI Context");
            Context context = JNDIcontext.getInitialContext();
            System.out.println("Get connection facory");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
            System.out.println("Create connection");
            connection = connectionFactory.createConnection();
            System.out.println("Create session");
            Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            System.out.println("Lookup queue");
            Queue queue = (Queue) context.lookup("/queue/test");
            System.out.println("Start connection");
            connection.start();
            System.out.println("Create consumer");
            MessageConsumer consumer = session.createConsumer(queue);
            System.out.println("set message listener");
            consumer.setMessageListener(new Consumer());
        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                connection.close();
            }
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("message received");
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
