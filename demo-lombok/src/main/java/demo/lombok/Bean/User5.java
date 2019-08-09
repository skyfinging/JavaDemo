package demo.lombok.Bean;

import lombok.Synchronized;

public class User5 {
    private String a;
    private Object readA = new Object();
    private Object readB = new Object();

    @Synchronized
    public void setA(String a){
        System.out.println("begin setA:"+a);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        this.a = a;
        System.out.println("end setA:"+this.a);
    }

    @Synchronized
    public void setB(String b){
        System.out.println("begin setB:"+b);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        this.a = "_"+b;
        System.out.println("end setA:"+this.a);
    }

    @Synchronized("readA")
    public String getA(){
        System.out.println("begin getA");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("end getA");
        return a;
    }

    @Synchronized("readB")
    public String getB(){
        System.out.println("begin getB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("end getB");
        return a+"_";
    }
}
