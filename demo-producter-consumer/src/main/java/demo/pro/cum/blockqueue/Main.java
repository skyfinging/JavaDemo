package demo.pro.cum.blockqueue;

import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>(10);

        queueTest(queue);

        Thread consumer1 = new Thread(new ConsumerQueue(queue, "c1"));
        Thread consumer2 = new Thread(new ConsumerQueue(queue, "c2"));

        Thread producer1 = new Thread(new ProducerQueue(queue, "p1"));
        Thread producer2 = new Thread(new ProducerQueue(queue, "p2"));

        consumer1.start();
        consumer2.start();

        producer1.start();
        producer2.start();
    }

    private static void queueTest(LinkedBlockingDeque<String> queue){
        //先把queue填满
        while(queue.remainingCapacity()>0){
            queue.add(queue.remainingCapacity()+"");
        }

        try {
            queue.add("");
        }catch(IllegalStateException e){
            System.out.println("when queue is full, add() throw IllegalStateException.");
        }
        boolean result = queue.offer("");
        System.out.println("when queue is full, offer() return "+result);
//        try {
//            queue.put("");
//        } catch (InterruptedException e) {
//        }

        System.out.println(queue.element());
        System.out.println(queue.peek());

        while(queue.size()>0){
            queue.remove();
        }

        try {
            queue.remove();
        }catch(NoSuchElementException e){
            System.out.println("when queue is empty, remove() throw NoSuchElementException.");
        }

        String str = queue.poll();
        System.out.println("when queue is empty, poll() return "+str);
//        try {
//            queue.take();
//        } catch (InterruptedException e) {
//        }

        try {
            queue.element();
        }catch(NoSuchElementException e){
            System.out.println("when queue is empty, element() throw NoSuchElementException.");
        }

        str = queue.peek();
        System.out.println("when queue is empty, peek() return "+str);
    }
}
