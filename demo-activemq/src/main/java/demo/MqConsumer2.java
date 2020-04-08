package demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class MqConsumer2 {
    public static String ULR= "tcp://127.0.0.1:61616";
    public static String NAME = "Queue01";
    public static void main(String[] args) throws JMSException {
        createConsumer2();
    }

    private static void createConsumer2() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ULR);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);

        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener((message)->{
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("消费者2："+textMessage.getText());
                message.acknowledge();
                System.out.println("消费者2确认并退出处理");
            } catch (JMSException e) {
                e.printStackTrace();
            }

        });

    }
}
