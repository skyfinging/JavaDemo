package demo.pro.cum.waitnotify;

import demo.pro.cum.AbstractProducer;

import java.util.Queue;

public class ProducerWaitNotify extends AbstractProducer {

    ProducerWaitNotify(Queue<String> queue, String name){
        super(queue, name);
    }

    @Override
    protected void create(){
        a++;
        String str = a+"";
        synchronized (queue){
            while(isFull(queue)){
                System.out.println("queue is full,"+name+" wait");
                try{
                    queue.wait();
                }catch(Exception e){}
            }
            queue.add(str);
            System.out.println(name+" create "+str);
            queue.notifyAll();
        }
    }
}
