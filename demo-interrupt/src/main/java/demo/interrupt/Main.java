package demo.interrupt;

import demo.interrupt.runnable.MyRunnable;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
        System.out.println("Main interrupt Thread");
        thread.interrupt();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
