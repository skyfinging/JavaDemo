package test1;

import lombok.Getter;

public class Runnable1 implements Runnable {
    private ClassA classA;
    private ClassB classB;
    @Getter
    private String name;

    public Runnable1(String name, ClassA classA,ClassB classB) {
        this.name = name;
        this.classA = classA;
        this.classB = classB;
    }

    @Override
    public void run() {
        while(!classA.isFlag()){
            //classB.isFlag();
            // volatile变量的作用“禁止指令重排”，原理即是使该线程的工作内存全部失效，后面会重新加载最新的值
            // 也就是说明在这之前执行的代码全部生效。
        }
        System.out.println(name+"end");
    }
}
