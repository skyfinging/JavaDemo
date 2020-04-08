package demo.dead.lock.runnable;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class CheckDeadLock implements Runnable {
    private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
    @Override
    public void run() {
        while(true){
            long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
            if(deadlockedThreadIds!=null){
                ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                for (Thread t : Thread.getAllStackTraces().keySet()) {
                    for (int i = 0; i < threadInfos.length; i++) {
                        if(t.getId() == threadInfos[i].getThreadId()) {
                            System.out.println(threadInfos[i].getThreadName()+" interrupt");
                            t.interrupt();
                        }
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
