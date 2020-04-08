package demo.supplier;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Car {

    @Getter
    @Setter
    private String name;
    public Car() {
        this.name = "";
    }

    public Car(String name) {
        this.name = name;
    }

    public void go(){
        System.out.println(name+" go.");
    }

    public static void stop(Car car){
        System.out.println(car.getName()+" stop.");
    }

    public void follow(Car car){
        System.out.println(this +" follow "+ car);
    }

}
