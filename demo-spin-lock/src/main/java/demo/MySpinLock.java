package demo;

import java.util.concurrent.atomic.AtomicReference;

public class MySpinLock {
    AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while(!reference.compareAndSet(null ,thread)){

        }
        System.out.println(thread.getName()+" get lock");
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        boolean unlock = reference.compareAndSet(thread, null);
        if(unlock)
            System.out.println(thread.getName()+" unlock succ");
        else
            System.out.println(thread.getName()+" unlock fail");
    }
}
