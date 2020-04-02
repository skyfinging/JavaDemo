package demo.producer;

import demo.config.ActivemqConfig;
import demo.queue.QueueManager;
import demo.signal.OneSignal;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class Producer2 {

    @Autowired
    private ActivemqConfig config;

    @Autowired
    private QueueManager queueManager;

    @Autowired
    private Session session;

    @Autowired
    @Qualifier("producerSignal")
    private OneSignal oneSignal;

    @Bean
    public Session getProducer() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(config.getBorkerURL());
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        return session;
    }

    /**
     * TPS：950
     * @throws JMSException
     */
    @Scheduled(fixedRate = 3600000, initialDelay = 1000)
    @Async
    public void sendMessage() throws JMSException {
        Destination destination = session.createQueue(queueManager.getQueueNameTest());
        MessageProducer producer = session.createProducer(destination);
        for(int i=0;i<1000000;i++){
            TextMessage message = session.createTextMessage(config.getMsg() + i);
            producer.send(message);
            oneSignal.add();
        }
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    @Async
    public void printSignal(){
        System.out.println("send rate:"+oneSignal.getCurrentRate()+" m/s");
        oneSignal.updateLastSignal();
    }
}
