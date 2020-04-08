import java.util.function.Supplier;

public class MyRunnable implements Runnable {
    Supplier<?> test;
    public MyRunnable(Supplier<?> test){
        this.test =test;
    }

    @Override
    public void run() {
        test.get();
    }
}
