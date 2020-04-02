package test1;

public class Main {
    public static void main(String[] args) {
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();

        Runnable1 runnable1 = new Runnable1("线程1",classA,classB);
        Runnable1 runnable11 = new Runnable1("线程2",classA,classB);
        Runnable1 runnable12 = new Runnable1("线程3",classA,classB);
        Runnable1 runnable13 = new Runnable1("线程4",classA,classB);
        Runnable1 runnable14 = new Runnable1("线程5",classA,classB);
        Runnable2 runnable2 = new Runnable2(classA,classB);

        Thread thread1 = new Thread(runnable1,runnable1.getName());
        Thread thread11 = new Thread(runnable11,runnable11.getName());
        Thread thread12 = new Thread(runnable12,runnable12.getName());
        Thread thread13 = new Thread(runnable13,runnable13.getName());
        Thread thread14 = new Thread(runnable14,runnable14.getName());
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread11.start();
        thread12.start();
        thread13.start();
        thread14.start();
        thread2.start();
    }
}
