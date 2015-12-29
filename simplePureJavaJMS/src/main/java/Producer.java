import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Queue;
import java.util.logging.Logger;

public class Producer {

    private static final Logger log = Logger.getLogger(Producer.class.getName());

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
            Queue queue = (Queue) context.lookup("/queue/HelloWorldQueue");
            System.out.println("Start connection");
            connection.start();
            System.out.println("Create producer");
            MessageProducer producer = session.createProducer((Destination) queue);
            System.out.println("Create hello world message");
            Message hellowWorldText = session.createTextMessage("Hello World!");
            System.out.println("Send hello world message");
            producer.send(hellowWorldText);
        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                connection.close();
            }
        }
    }
}
