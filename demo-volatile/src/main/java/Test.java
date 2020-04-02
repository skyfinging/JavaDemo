import java.util.concurrent.Semaphore;

public class Test {
    private Semaphore semaphore;
    private Integer value;
    private  boolean init;

    public Test(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public Integer doTask()  {
        System.out.println("init");
        value = setValue();
        init = true;

        return 0;
    }

    public Integer doTask1()  {
        while (!init) {

        }
        System.out.println(value.toString());
        semaphore.release();
        return 0;
    }

    private Integer setValue() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return 123456;
    }
}
