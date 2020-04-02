package demo.entity;


import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * 不会序列化nameB字段
 */
public class Entity implements Serializable {

    private String nameA;
    //添加了transient的字段，不会被自动序列化
    private transient String nameB;

    public Entity(String nameA, String nameB){
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
}
