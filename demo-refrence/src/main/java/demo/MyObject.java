package demo;

public class MyObject extends AbstractObject{

    public MyObject(String id) {
        super(id);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("MyObject finalize "+id);
        super.finalize();
    }
}
