package demo.rw;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWObject<T> {
    private T t;
    private ReadWriteLock readWriteLock;

    public RWObject(){
        readWriteLock = new ReentrantReadWriteLock();
    }

    public T read(){
        readWriteLock.readLock().lock();
        T t;
        try{
            t = this.t;
        } finally {
            readWriteLock.readLock().unlock();
        }
        return t;
    }

    public void write(T t){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("write "+this.t+" -> "+t);
            this.t = t;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

}
