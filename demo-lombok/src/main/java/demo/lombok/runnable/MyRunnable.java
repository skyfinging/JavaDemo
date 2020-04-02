package demo.lombok.runnable;

import demo.lombok.Bean.User5;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyRunnable implements Runnable {
    private User5 user5;
    private String a;

    @Override
    public void run() {
        System.out.println("Thraed start:"+a);
        if(a.equals("1"))
            user5.setA(a);
        else
            user5.setB(a);
    }
}
