package demo.abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程循环打印ABC
 */
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ReentrantLock lock = new ReentrantLock();
        Condition aCondition = lock.newCondition();
        Condition bCondition = lock.newCondition();
        Condition cCondition = lock.newCondition();

        MyCondition myConditionA = new MyCondition(lock, aCondition, bCondition, 0, 3);
        MyCondition myConditionB = new MyCondition(lock, bCondition, cCondition, 1, 3);
        MyCondition myConditionC = new MyCondition(lock, cCondition, aCondition, 2, 3);

        Printer printerA = new Printer(counter,"A", myConditionA);
        Printer printerB = new Printer(counter,"B", myConditionB);
        Printer printerC = new Printer(counter,"C", myConditionC);

        Thread threadA = new Thread(printerA);
        Thread threadB = new Thread(printerB);
        Thread threadC = new Thread(printerC);

        threadB.start();
        threadA.start();
        threadC.start();
    }
}
