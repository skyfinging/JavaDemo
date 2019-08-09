package demo.pro.cum.waitnotify;

import demo.pro.cum.AbstractConsumer;

import java.util.Queue;

public class ConsumerWaitNotify extends AbstractConsumer {

    public ConsumerWaitNotify(Queue<String> queue, String name){
        super(queue, name);
    }

    public void handle(){
        String str = null;
        synchronized (queue){
            while(queue.isEmpty()){
                System.out.println("queue is empty,"+name+" wait.");
                try{
                    queue.wait();
                }catch(Exception e){}
            }
            str = queue.poll();
            System.out.println(name+" handle "+str);
            queue.notifyAll();
        }
    }


}
