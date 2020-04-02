package demo.entity;

import java.io.Serializable;

/**
 * 对象引用，包含entity对象
 */
@SuppressWarnings("serial")
public class Entity4 implements Serializable {
    private String nameC;
    private Entity entity;
    private Entity1 entity1;
    private Entity2 entity2;

    public Entity4(Entity entity, Entity1 entity1, Entity2 entity2, String nameC){
        this.entity = entity;
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.nameC = nameC;
    }

    @Override
    public String toString() {
        return "{"+entity+"}"
                +"{"+entity1+"}"
                +"{"+entity2+"}"
                +",nameC:"+nameC;
    }
}
