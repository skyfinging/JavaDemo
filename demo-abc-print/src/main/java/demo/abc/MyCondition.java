package demo.abc;

import lombok.Value;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Value
class MyCondition {
    private ReentrantLock lock;
    private Condition conditionToWait;
    private Condition conditionToSignal;
    private Integer target;
    private Integer totalPrinter;
}
