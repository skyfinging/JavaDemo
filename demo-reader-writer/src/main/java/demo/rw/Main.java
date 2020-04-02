package demo.rw;

public class Main {
    public static void main(String[] args) {
        RWObject<String> rwObject = new RWObject<>();

        MyReader reader1 = new MyReader("A", rwObject);
        MyReader reader2 = new MyReader("B", rwObject);
        MyReader reader3 = new MyReader("C", rwObject);

        MyWriter writer1 = new MyWriter("J",rwObject);
        MyWriter writer2 = new MyWriter("K",rwObject);
        MyWriter writer3 = new MyWriter("L",rwObject);

        Thread thread1 = new Thread(reader1);
        Thread thread2 = new Thread(reader2);
        Thread thread3 = new Thread(reader3);
        Thread thread4 = new Thread(writer1);
        Thread thread5 = new Thread(writer2);
        Thread thread6 = new Thread(writer3);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
