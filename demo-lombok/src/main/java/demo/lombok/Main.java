package demo.lombok;

import demo.lombok.Bean.*;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Date;

/**
 * 该demo用来试验lombok的用法
 */
@Log
public class Main {

    public static void main(String[] args) {
        User user = new User(2);
        user.getName();
        user.setName("abc");
        user.getAge();
        user.setAge(2);
        System.out.println(user);

        User2 user2 = new User2();
        user2.setName("abc");
        user2.getAge();
        System.out.println(user2.getAge());

        User3 user3 = new User3(3,"abcd",2);
        user3.getAge();
        user3.getName();
        user3.setNumber(4);
        System.out.println(user3);

        User4 user4 = User4.builder().age(1).name("a").number(1).number(2).build();
        System.out.println(user4);

        User5 user5 = new User5();
//        MyRunnable myRunnable1 = new MyRunnable(user5,"1");
//        MyRunnable myRunnable2 = new MyRunnable(user5,"2");
//        Thread thread1 = new Thread(myRunnable1);
//        Thread thread2 = new Thread(myRunnable2);
//        thread1.start();
//        thread2.start();

        User6 user6 = new User6();
        try {
            user6.userCleanUp();
        }catch (IOException e){
            log.info(e.getMessage());
        }
        boolean result = user6.deleteFile();
        System.out.println("delete file "+result);

        User7 user7 = new User7();
        System.out.println(new Date()+""+user7.getCache()); //第一次调用，需要等待1秒
        System.out.println(new Date()+""+user7.getCache()); //第2次调用，不需要等待1秒
        System.out.println(new Date()+""+user7.getCache());
    }
}
