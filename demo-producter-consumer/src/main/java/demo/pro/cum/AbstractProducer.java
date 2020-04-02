package demo.pro.cum;

import java.util.Queue;

public abstract class AbstractProducer implements Runnable{
    protected int a = 0;
    protected Queue<String> queue;
    protected String name;

    protected abstract void create();

    public AbstractProducer(Queue<String> queue, String name){
        this.queue = queue;
        this.name = name;
    }

    protected boolean isFull(Queue<?> queue){
        return queue.size() >= 10;
    }

    @Override
    public void run() {
        while(true){
            create();
            try {
                if(a<20)
                    Thread.sleep(10);
                else
                    Thread.sleep(40);
                if(a>100)
                    break;
            } catch (InterruptedException e) {
            }
        }
    }
}
