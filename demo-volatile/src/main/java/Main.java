import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        while(true){
            semaphore.acquire();
            System.out.println("test");
            Test test = new Test(semaphore);
            MyRunnable runnable1 = new MyRunnable(test::doTask);
            MyRunnable runnable2 = new MyRunnable(test::doTask1);
            Thread thread1 = new Thread(runnable1);
            Thread thread2 = new Thread(runnable2);
            if(Math.random()>0.5) {
                thread1.start();
                thread2.start();
            }else{
                thread2.start();
                thread1.start();
            }
            Thread.sleep(10);
        }
    }
}
