package demo.signal;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SignalManager {

    @Bean("producerSignal")
    public OneSignal getProducerSignal(){
        return new OneSignal();
    }

    @Bean("consumerSignal")
    public OneSignal getConsumerSignal(){
        return new OneSignal();
    }

}
