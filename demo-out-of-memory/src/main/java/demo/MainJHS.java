package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainJHS {
    // -Xmx10m -Xms10m
    public static void main(String[] args) {
//        byte[] bytes = new byte[50 * 1024 * 1024];
        String str = "";
        List<String> list = new ArrayList<>();
        while(true){
            str += "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            list.add(str.intern());
        }

    }
}
