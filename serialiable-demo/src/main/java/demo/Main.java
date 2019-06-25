package demo;

import demo.entity.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity("nameA", "nameB");
        Entity entity1 = (Entity) readObject(writeObject(entity));
        System.out.println("Entity  "+entity1);

        Entity1 entity11 = new Entity1("nameA", "nameB");
        Entity1 entity12 = (Entity1) readObject(writeObject(entity11));
        System.out.println("Entity1  "+entity12);

        Entity2 entity21 = new Entity2("nameA", "nameB");
        Entity2 entity22 = (Entity2) readObject(writeObject(entity21));
        System.out.println("Entity2  "+entity22);

        Entity4 entity41 = new Entity4(entity,entity11,entity21,"nameC");
        Entity4 entity42 = (Entity4) readObject(writeObject(entity41));
        System.out.println("Entity4  "+entity42);

//        try {
//////            Entity5 entity51 = new Entity5("");
//////            Entity5 entity52 = (Entity5) readObject(writeObject(entity51));
//////            System.out.println("Entity5  " + entity52);
//////        }catch (Exception e){
//////            e.printStackTrace();
//////        }
    }

    private static ByteArrayOutputStream writeObject(Object object){
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        try{
            ObjectOutputStream out1 = new ObjectOutputStream(buf);
            out1.writeObject(object);
        }catch(IOException e){
            e.printStackTrace();
        }
        return buf;
    }

    private static Object readObject(ByteArrayOutputStream buf){
        try {
            ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
            Object entity = in1.readObject();
            return entity;
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
