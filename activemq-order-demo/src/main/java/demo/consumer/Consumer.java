package demo.consumer;
import demo.signal.OneSignal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@SuppressWarnings("unused")
public class Consumer {

    @Autowired
    @Qualifier("consumerSignal")
    private OneSignal signal;

    @JmsListener(destination = "${activemq.queue.name.test}", containerFactory="queueListenerFactory")
    public void receiveQueue(String text) {
        signal.add();
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    @Async
    public void printSignal(){
        System.out.println("receive rate:"+signal.getCurrentRate()+" m/s");
        signal.updateLastSignal();
    }

}
