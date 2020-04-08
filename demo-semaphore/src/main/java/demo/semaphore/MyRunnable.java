package demo.semaphore;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class MyRunnable implements  Runnable {
    private final String name;
    private final Semaphore semaphore;

    public MyRunnable(String name, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try{
            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println(new Date()+"  "+name+" release");
        } catch (InterruptedException e) {
            System.out.println(name+" was interrupted");
        }finally {
            semaphore.release();
        }
    }
}
