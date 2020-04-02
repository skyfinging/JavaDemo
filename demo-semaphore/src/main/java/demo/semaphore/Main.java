package demo.semaphore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,true);
        for(int i=0;i<10;i++) {
            MyRunnable myRunnable = new MyRunnable(i+"", semaphore);
            Thread thread = new Thread(myRunnable);
            thread.start();
        }
    }
}
