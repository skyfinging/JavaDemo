package demo.pro.cum.waitnotify;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        Thread producer1 = new Thread(new ProducerWaitNotify(queue,"p1"));
        Thread producer2 = new Thread(new ProducerWaitNotify(queue,"p2"));

        Thread consumer1 = new Thread(new ConsumerWaitNotify(queue,"c1"));
        Thread consumer2 = new Thread(new ConsumerWaitNotify(queue,"c2"));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
