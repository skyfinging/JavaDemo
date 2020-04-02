package demo.rw;

public class MyWriter implements Runnable {
    private final String name;
    private final RWObject<String> rwObject;

    public MyWriter(String name, RWObject<String> rwObject) {
        this.name = name;
        this.rwObject = rwObject;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            rwObject.write(i+"");
            System.out.println(name+" write "+ i+"");

        }
    }
}
