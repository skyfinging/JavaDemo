package demo.pro.cum.waitsignal;

import demo.pro.cum.AbstractProducer;

import java.util.Queue;

public class ProducerWaitSignal extends AbstractProducer {
    ProducerWaitSignal(Queue<String> queue, String name){
        super(queue,name);
    }

    @Override
    protected void create(){
        a++;
        String str = a+"";
        LockSignal.getLock().lock();
        try {
            while (isFull(queue)) {
                System.out.println("queue is full," + name + " wait");
                try {
                    LockSignal.getNoFullCondition().await();
                } catch (Exception e) {
                    LockSignal.getNoFullCondition().signal();
                }
            }
            queue.add(str);
            System.out.println(name + " create " + str);
            LockSignal.getNoEmptyCondition().signal();//只唤醒消费者
        }finally {
            LockSignal.getLock().unlock();
        }
    }



}
