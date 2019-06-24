package demo;

import demo.method1.MyRunnable;
import demo.method1.Signal;
import demo.method2.RunnableTask;
import demo.method2.SignalCondition;
import demo.method2.TaskImpl;
import demo.method2.TaskSignal;

/**
 * 使用三个线程，顺序交替打印ABC
 * 方法1：synchronized
 * 方法2：Lock condition
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("method1Synchronized:");
        method1Synchronized();

        Thread.sleep(1000);

        System.out.println("\r\nmethod2Lock:");
        method2Lock();
    }

    public static void method1Synchronized(){
        Signal signal = new Signal(1);
        MyRunnable runnableA = new MyRunnable("A", 1, signal);
        MyRunnable runnableB = new MyRunnable("B", 2, signal);
        MyRunnable runnableC = new MyRunnable("C", 3, signal);
        startThread(runnableA, runnableB, runnableC);
    }

    public static void method2Lock(){
        TaskSignal taskSignal = new TaskSignal();
        SignalCondition signalConditionA = new SignalCondition(1, taskSignal.newCondition());
        SignalCondition signalConditionB = new SignalCondition(2, taskSignal.newCondition());
        SignalCondition signalConditionC = new SignalCondition(3, taskSignal.newCondition());
        TaskImpl taskA = new TaskImpl("A", signalConditionA, signalConditionB);
        TaskImpl taskB = new TaskImpl("B", signalConditionB, signalConditionC);
        TaskImpl taskC = new TaskImpl("C", signalConditionC, signalConditionA);

        RunnableTask runnableTaskA = new RunnableTask(taskSignal, taskA);
        RunnableTask runnableTaskB = new RunnableTask(taskSignal, taskB);
        RunnableTask runnableTaskC = new RunnableTask(taskSignal, taskC);

        startThread(runnableTaskA, runnableTaskB, runnableTaskC);
    }

    private static void startThread(Runnable runnableA, Runnable runnableB, Runnable runnableC){
        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        Thread threadC = new Thread(runnableC);
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
