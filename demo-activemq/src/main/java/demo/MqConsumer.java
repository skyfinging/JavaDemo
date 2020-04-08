package demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class MqConsumer {
    public static String ULR= "tcp://127.0.0.1:61616";
    public static String NAME = "Queue01";
    public static void main(String[] args) throws JMSException {
        createConsumer1();
    }

    private static void createConsumer1() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ULR);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);

        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener((message)->{
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("消费者1："+textMessage.getText());
                TimeUnit.SECONDS.sleep(10);
                //message.acknowledge();
                //消费者1没有手动确认，直接断开连接，之后消费者2才能这条消息
                System.out.println("消费者1退出处理");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }
}
