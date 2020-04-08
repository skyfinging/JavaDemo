package demo;

public class MyWeakObject extends AbstractObject{


    public MyWeakObject(String id) {
        super(id);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyWeakObject finalize "+id);
        super.finalize();
    }
}
