package demo.method2;

import java.util.concurrent.locks.Condition;

public class SignalCondition {
    private Integer number;
    private Condition condition;

    public SignalCondition(Integer number, Condition condition){
        this.number = number;
        this.condition = condition;
    }

    public void wait(Integer numberCondition) throws InterruptedException {
        if (number != numberCondition) {
            condition.await();
        }
    }

    public void signal(){
        condition.signal();
    }

    public Integer getNumber(){
        return number;
    }
}
