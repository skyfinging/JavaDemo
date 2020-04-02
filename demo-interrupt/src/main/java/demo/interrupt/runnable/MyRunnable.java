package demo.interrupt.runnable;

/**
 * hread.interrupted()与Thread.currentThread().isInterrupted()作用一样，只是前者调用一次之后就会重置isInterrupt状态
 * 在sleep中被interrupt，会退出sleep，并抛出异常，但是isInterrupt会设置成false
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Yes,I am interruted,but I am still running");
                if(Thread.interrupted()){
                    System.out.println("Yes,I am interruted,but just ones time return true");
                    if(Thread.interrupted() == false){
                        System.out.println("interrupt status is reseted");
                    }
                }
                break;
            }else{
                System.out.println(" not yet interrupted");
            }

//            try{
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                System.out.println("I am sleep, but interrupt by other thread, and interrupt status set false");
//            }
        }
    }
}
