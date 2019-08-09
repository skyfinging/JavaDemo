package demo.pro.cum.blockqueue;

import demo.pro.cum.AbstractConsumer;
import demo.pro.cum.waitsignal.LockSignal;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;

public class ConsumerQueue extends AbstractConsumer {
    private BlockingDeque<String> queue;
    public ConsumerQueue(BlockingDeque<String> queue, String name) {
        super(queue, name);
        this.queue = queue;
    }

    @Override
    protected void handle() {
        String str = null;
        long b = System.currentTimeMillis();
        try {
            str = queue.take();
        } catch (InterruptedException e) {
            System.out.println(name+" take fail");
        }
        long e = System.currentTimeMillis();
        System.out.println(name+" handle "+str + " cost:"+(e-b));
    }
}
