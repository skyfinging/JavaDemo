package demo;

public class MySoftObject extends AbstractObject {

    private long createTime = 0;

    public MySoftObject(String id) {
        super(id);
        createTime = System.currentTimeMillis();
    }

    @Override
    protected void finalize() throws Throwable {
        long now = System.currentTimeMillis();
        System.out.println("MySoftObject finalize "+id+",live time:"+(now-createTime));
        super.finalize();
    }
}
