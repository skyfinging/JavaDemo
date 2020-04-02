package demo;

public class AbstractObject implements Iid{
    protected byte[] b = new byte[10 * 1024];

    protected final String id;

    public AbstractObject(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
