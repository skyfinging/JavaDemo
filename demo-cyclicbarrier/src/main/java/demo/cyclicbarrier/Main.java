package demo.cyclicbarrier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        final int size = 5;
        TaskService taskService = context.getBean(TaskService.class);
        MySignal mySignal = new MySignal(size);
        for(int i=0;i<size;i++){
            taskService.swimming(mySignal, "man"+i);
        }
        taskService.troublemaker();
        context.close();
    }
}
