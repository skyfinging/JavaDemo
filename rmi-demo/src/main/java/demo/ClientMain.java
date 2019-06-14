package demo;

import demo.rmi.IHello;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args) {
        try {
            IHello hello = (IHello) Naming.lookup("rmi://10.10.107.21:3099/hello");
            System.out.println(hello.sayHello("zhangxianxin1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
