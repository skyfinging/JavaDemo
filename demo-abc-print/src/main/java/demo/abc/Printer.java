package demo.abc;

public class Printer implements Runnable {
    private Counter counter;
    private final String label;
    private final MyCondition myCondition;

    Printer(Counter counter, String label, MyCondition myCondition){
        this.counter = counter;
        this.label = label;
        this.myCondition = myCondition;
    }

    @Override
    public void run() {
        while(true){
            myCondition.getLock().lock();
            try{
                while((counter.getIndex()%myCondition.getTotalPrinter())!=myCondition.getTarget()){
                    try {
                        myCondition.getConditionToWait().await();
                    } catch (InterruptedException e) {
                        myCondition.getConditionToWait().signal();
                    }
                }
                System.out.println(label);
                counter.next();
                myCondition.getConditionToSignal().signal();
                if(counter.getIndex()>99)
                    break;
            }finally {
                myCondition.getLock().unlock();
            }
        }
    }
}
