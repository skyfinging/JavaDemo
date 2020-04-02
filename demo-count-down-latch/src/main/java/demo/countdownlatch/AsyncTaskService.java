package demo.countdownlatch;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class AsyncTaskService {
    @Async
    public void executeAsyncCounter(CountDownLatch countDownLatch, String name){
        System.out.println(name+" ready.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        countDownLatch.countDown();
    }

    @Async
    public void executeAsyncFire(CountDownLatch countDownLatch, String name){
        while(countDownLatch.getCount()>0){
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(" fire");
    }
}
