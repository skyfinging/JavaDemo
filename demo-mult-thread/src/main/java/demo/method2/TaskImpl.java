package demo.method2;

public class TaskImpl implements TaskInterface {
    private String name;
    private SignalCondition signalCondition;
    private SignalCondition nextSignalCondition;

    public TaskImpl(String name, SignalCondition signalCondition, SignalCondition nextSignalCondition){
        this.name = name;
        this.signalCondition = signalCondition;
        this.nextSignalCondition = nextSignalCondition;
    }

    @Override
    public void doTask() {
        System.out.print(name);
    }

    @Override
    public SignalCondition getSignalCondition() {
        return signalCondition;
    }

    @Override
    public SignalCondition getNextSignalCondition() {
        return nextSignalCondition;
    }

    @Override
    public String toString(){
        return "task"+name;
    }
}
