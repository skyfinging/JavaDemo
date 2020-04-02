package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    /**
     * 测试4：使用CopyOnWriteArrayList,写时复制
     */
    public static void test4(){
        List<Integer> list = new CopyOnWriteArrayList<>();
        test(list);
    }

    /**
     * 测试3：使用Collections.synchronizedList
     */
    public static void test3(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        test(list);
    }


    /**
     * 测试2：使用Vector代替ArrayList
     */
    public static void test2(){
        List<Integer> list = new Vector<>();
        test(list);
    }

    /**
     *  测试1：使用ArrayList，线程不安全
     *  有可能会抛出异常ConcurrentModificationException
     */
    public static void test1(){
        List<Integer> list = new ArrayList<>();
        test(list);
    }

    private static void test(List<Integer> list){
        for(int i=0;i<10;i++) {
            new Thread(()->{
                list.add((int) (Math.random()*100));
                System.out.println(list);
            }).start();
        }
    }
}
