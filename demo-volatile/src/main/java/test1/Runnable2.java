package test1;

public class Runnable2 implements Runnable {
    private  ClassA classA;
    private ClassB classB;

    public Runnable2(ClassA classA,ClassB classB) {
        this.classA = classA;
        this.classB = classB;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        classA.setFlag(true);

    }
}
