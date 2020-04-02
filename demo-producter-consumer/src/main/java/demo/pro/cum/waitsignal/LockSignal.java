package demo.pro.cum.waitsignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSignal {
    private static Lock lock = new ReentrantLock();
    private static Condition noFullCondition = lock.newCondition();
    private static Condition noEmptyCondition = lock.newCondition();

    public static Lock getLock(){
        return lock;
    }

    public static Condition getNoFullCondition(){
        return noFullCondition;
    }

    public static Condition getNoEmptyCondition(){
        return noEmptyCondition;
    }
}
