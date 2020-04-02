package demo.entity;


import java.io.*;

@SuppressWarnings("serial")
/**
 * 采用自定义序列化方法，将nameA序列化成nameB
 */
public class Entity2 implements Externalizable {

    private String nameA;
    private String nameB;

    public Entity2(){}

    public Entity2(String nameA, String nameB){
        this.nameA = nameA;
        this.nameB = nameB;
    }

    public String getNameA() {
        return nameA;
    }

    public String getNameB() {
        return nameB;
    }

    @Override
    public String toString() {
        return "nameA:"+nameA+",nameB:"+nameB;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nameA);
        out.writeObject(nameB);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        nameB = (String) in.readObject();
        nameA = (String) in.readObject();
    }
}
