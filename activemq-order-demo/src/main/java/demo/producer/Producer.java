package demo.producer;

import demo.signal.OneSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

//@Service
@SuppressWarnings("unused")
public class Producer{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    @Qualifier("producerSignal")
    private OneSignal oneSignal;

    /**
     * 打开池化开关，TPS为800-900
     * 关闭池化开关，TPS为100
     */
    @Scheduled(fixedRate=3600000,initialDelay=1000)
    @Async
    public void sendQueue() {
        for(int i=0;i<1000000;i++){
            this.jmsMessagingTemplate.convertAndSend(queue, "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzmsg"+i);
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
