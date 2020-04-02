package demo.dead.lock;

import demo.dead.lock.runnable.CheckDeadLock;
import demo.dead.lock.runnable.MyRunnable;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        MyRunnable myRunnable1 = new MyRunnable("A", lock1,lock2);
        MyRunnable myRunnable2 = new MyRunnable("B", lock2,lock1);

        Thread thread1 = new Thread(myRunnable1,myRunnable1.getName());
        Thread thread2 = new Thread(myRunnable2,myRunnable2.getName());

        thread1.start();
        thread2.start();

        CheckDeadLock checkDeadLock = new CheckDeadLock();
        Thread checkThread = new Thread(checkDeadLock);
        checkThread.start();
    }
}
