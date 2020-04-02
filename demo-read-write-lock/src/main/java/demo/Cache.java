package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {
    Map<String, String> map = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 正在写入,key:"+key);
            map.put(key, value);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" 正在完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.writeLock().unlock();
        }
    }

    public String get(String key){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 正在读取");
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName()+" 读取完成:"+value);
            return value;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.readLock().unlock();
        }
        return null;
    }
}
