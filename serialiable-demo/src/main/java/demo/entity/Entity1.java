package demo.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * 会序列化nameB字段
 */
public class Entity1 implements Serializable {

    private String nameA;
    //添加了transient的字段，不会被自动序列化
    private transient String nameB;

    public Entity1(String nameA, String nameB){
        this.nameA = nameA;
        this.nameB = nameB;
    }

    @SuppressWarnings("unused")
    public String getNameA() {
        return nameA;
    }

    @SuppressWarnings("unused")
    public String getNameB() {
        return nameB;
    }

    /**
     * 序列化的时候，会自动调用writeObject和readObject方法，完成自定义序列化的内容
     * @param o 输出流
     * @throws IOException 当写入失败的时候，抛出异常
     */
    private void writeObject(ObjectOutputStream o) throws IOException {
        o.defaultWriteObject();
        o.writeObject(nameB);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
        nameB=(String)stream.readObject();
    }

    @Override
    public String toString() {
        return "nameA:"+nameA+",nameB:"+nameB;
    }
}
