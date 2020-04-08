package demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MqProductor {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MqConsumer.ULR);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(MqConsumer.NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        Message message = session.createTextMessage("abc");
        messageProducer.send(message);
        System.out.println("成功发送mq消息");
        messageProducer.close();
        session.close();
        connection.close();
    }
}
