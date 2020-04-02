package demo.queue;

import lombok.Getter;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
@SuppressWarnings("unused")
public class QueueManager {

    @Value("${activemq.queue.name.test}")
    @Getter
    private String queueNameTest;

    @Value("${activemq.topic.name.test}")
    private String topicNameTest;

    @Bean
    public Queue getTestQueue(){
        Queue queue = new ActiveMQQueue(queueNameTest);
        return queue;
    }

    @Bean
    public Topic getTestTopic(){
        Topic topic = new ActiveMQTopic(topicNameTest);
        return topic;
    }
}
