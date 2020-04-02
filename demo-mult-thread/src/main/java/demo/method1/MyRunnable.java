package demo.method1;

public class MyRunnable implements Runnable {
    private String name;
    private Integer signal;
    private Signal currentSignal;
    int count = 10;

    public MyRunnable(String name, Integer signal, Signal currentSignal) {
        this.name = name;
        this.signal = signal;
        this.currentSignal = currentSignal;
    }


    @Override
    public void run() {
        int printCount = 0;
        int whileCount = 0;
        while(true){
            whileCount ++;
            synchronized (currentSignal) {
                if (currentSignal.getValue() == signal) {
                    System.out.print(name);
                    printCount++;
                    currentSignal.nextValue();
                    currentSignal.notifyAll();
                    if (printCount >= count)
                        break;
                }else {
                    try {
                        currentSignal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
