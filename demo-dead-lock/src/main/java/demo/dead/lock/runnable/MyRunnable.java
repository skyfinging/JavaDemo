package demo.dead.lock.runnable;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable{
    @Getter
    private final String name;
    private final ReentrantLock lock1;
    private final ReentrantLock lock2;

    public MyRunnable(String name, ReentrantLock lock1, ReentrantLock lock2) {
        this.name = name;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }


    @Override
    public void run() {
        System.out.println(name+" begin");
        try{
            lock1.lockInterruptibly();
            System.out.println(name+" lock1 lock succ");
            Thread.sleep(1000);
            lock2.lockInterruptibly();
            System.out.println(name+" lock2 lock succ");
        }catch (InterruptedException e){
            System.out.println(name+" was interrupted.");
        }finally{
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
        }
        System.out.println(name+" end");
    }
}
