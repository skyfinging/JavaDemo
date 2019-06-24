package demo.method2;

import demo.method1.Signal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskSignal {
    private final Lock lock = new ReentrantLock();
    private Integer number = 1;

    public Condition newCondition(){
        return lock.newCondition();
    }

    public void doTask(TaskInterface task){
        try {
            lock.lock();
            SignalCondition signalCondition = task.getSignalCondition();
            signalCondition.wait(number);

            task.doTask();

            SignalCondition nextSignalCondition = task.getNextSignalCondition();
            number = nextSignalCondition.getNumber();
            nextSignalCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
