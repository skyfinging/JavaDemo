package demo.pro.cum.blockqueue;

import demo.pro.cum.AbstractProducer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerQueue extends AbstractProducer {
    private BlockingQueue<String> queue;
    public ProducerQueue(BlockingDeque<String> queue, String name) {
        super(queue, name);
        this.queue = queue;
    }

    @Override
    protected void create() {
        a++;
        String str = a+"";
        long b = System.currentTimeMillis();
        try {
            this.queue.put(str);
        } catch (InterruptedException e) {
            System.err.println(name + " create "+ str +" fail");
        }
        long e = System.currentTimeMillis();
        System.out.println(name+" create "+str + " cost:"+(e-b));
    }
}
