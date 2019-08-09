package demo.pro.cum;

import java.util.Queue;

public abstract class AbstractConsumer implements Runnable{
    protected Queue<String> queue;
    protected String name;
    public AbstractConsumer(Queue<String> queue, String name){
        this.queue = queue;
        this.name = name;
    }

    protected abstract void handle();

    @Override
    public void run() {
        while(true){
            handle();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }
}
