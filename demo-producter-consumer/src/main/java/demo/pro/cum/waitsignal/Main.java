package demo.pro.cum.waitsignal;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        Thread producer1 = new Thread(new ProducerWaitSignal(queue,"p1"));
        Thread producer2 = new Thread(new ProducerWaitSignal(queue,"p2"));

        Thread consumer1 = new Thread(new ConsumerWaitSignal(queue,"c1"));
        Thread consumer2 = new Thread(new ConsumerWaitSignal(queue,"c2"));

        consumer1.start();
        consumer2.start();
        producer1.start();
        producer2.start();
    }
}
