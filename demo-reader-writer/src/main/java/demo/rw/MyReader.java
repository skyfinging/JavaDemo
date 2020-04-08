package demo.rw;

public class MyReader implements  Runnable {
    private final String name;
    private final RWObject<?> rwObject;

    public MyReader(String name, RWObject<?> rwObject) {
        this.name = name;
        this.rwObject = rwObject;
    }

    @Override
    public void run() {
        while(true){
            Object obj = rwObject.read();
            System.out.println(name+" read "+obj);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
