package demo.pro.cum.waitsignal;

import demo.pro.cum.AbstractConsumer;

import java.util.Queue;

public class ConsumerWaitSignal extends AbstractConsumer {
    ConsumerWaitSignal(Queue<String> queue, String name){
        super(queue, name);
    }

    protected void handle(){
        String str;
        LockSignal.getLock().lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("queue is empty," + name + " wait.");
                try {
                    LockSignal.getNoEmptyCondition().await();
                } catch (Exception e) {
                    LockSignal.getNoEmptyCondition().signal();
                }
            }
            str = queue.poll();
            System.out.println(name + " handle " + str);
            LockSignal.getNoFullCondition().signal();   //只唤醒生产者
        }finally {
            LockSignal.getLock().unlock();
        }
    }
}
