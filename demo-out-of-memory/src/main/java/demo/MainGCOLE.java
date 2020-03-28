package demo;

import java.util.ArrayList;
import java.util.List;

public class MainGCOLE {
    // -Xmx10m -Xms10m -XX:MaxDirectMemorySize=5m
    public static void main(String[] args) {

        int i=0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(i + "");
            }
        }catch (Throwable e){
            System.out.println("----------------"+i+"--------------");
            e.printStackTrace();
        }
    }
}
