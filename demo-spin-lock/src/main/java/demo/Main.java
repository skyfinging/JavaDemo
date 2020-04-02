package demo;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MySpinLock mySpinLock = new MySpinLock();
        new Thread(
                ()->{
                    mySpinLock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                    }
                    mySpinLock.unlock();
                },"A"
        ).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(
                ()->{
                    mySpinLock.lock();
                    mySpinLock.unlock();
                },"B"
        ).start();


    }
}
