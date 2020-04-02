package demo.method1;

public class Signal {
    private Integer value;

    public Signal(Integer value){
        this.value = value;
    }

    public synchronized void nextValue() {
        this.value++;
        if(value>3)
            value =1;
    }

    public synchronized Integer getValue() {
        return value;
    }
}
