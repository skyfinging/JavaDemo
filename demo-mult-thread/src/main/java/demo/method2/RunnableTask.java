package demo.method2;

public class RunnableTask implements  Runnable {
    private Integer count = 10;
    private TaskSignal taskSignal;
    private TaskInterface task;

    public RunnableTask(TaskSignal taskSignal, TaskInterface task){
        this.taskSignal = taskSignal;
        this.task = task;
    }

    @Override
    public void run() {
        for(int i=0;i<count;i++){
            taskSignal.doTask(task);
        }
    }
}
