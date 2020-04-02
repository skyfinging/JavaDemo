package demo.abc;

public class Counter {
    private volatile Integer index;

    public Counter(){
        index = 0;
    }
    public synchronized Integer getIndex(){
        return index;
    }

    public synchronized void next(){
        index++;
    }

}
