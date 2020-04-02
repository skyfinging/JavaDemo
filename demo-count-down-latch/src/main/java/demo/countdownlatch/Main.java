package demo.countdownlatch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        asyncTaskService.executeAsyncFire(countDownLatch, "");
        for(int i=0;i<10;i++){
            asyncTaskService.executeAsyncCounter(countDownLatch, i+"");
        }
        context.close();
    }
}
