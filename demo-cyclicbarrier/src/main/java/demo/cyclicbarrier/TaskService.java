package demo.cyclicbarrier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.BrokenBarrierException;

@Service
public class TaskService {
    @Async
    public void troublemaker(){
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            //从程序当前运行线程中，随机取出一个线程出来干扰
            Set<Thread> threads = Thread.getAllStackTraces().keySet();
            int size = threads.size();
            int i = 0;
            int index = (int)(Math.random()*size);
            for (Thread thread : threads) {
                if(i!=index){
                    i++;
                    continue;
                }
                thread.interrupt();
                break;
            }
        }
    }

    @Async
    public void swimming(MySignal mySignal, String name){
        SportEnum sportEnum = mySignal.getSport();
        doSport(mySignal, name, sportEnum); //第一场比赛

        rest(name);
        sportEnum = mySignal.getSport();
        doSport(mySignal, name, sportEnum); //第二场比赛

        rest(name);
        sportEnum = mySignal.getSport();
        doSport(mySignal, name, sportEnum); //第三场比赛
        boolean isWinner = mySignal.setWinner(name);
        if(isWinner==true){
            System.out.println("我是 "+name+", 我是冠军");
        }
    }

    private void rest(String name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println(name+" 休息10分钟");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }


    private void doSport(MySignal mySignal, String name, SportEnum sportEnum){
        System.out.println(name +" 到达 "+sportEnum.getSportName()+" 起跑线,等待其他人");
        while(sportEnum==mySignal.getSport()) {
            try {
                if(mySignal.getStartLine().isBroken()){
                    try{
                        Thread.sleep(0);
                    }catch (InterruptedException e){}
                }else{
                    mySignal.getStartLine().await();
                    System.out.println(name + " 开始 " + sportEnum.getSportName());
                }
            } catch (InterruptedException e) {
                System.out.println("我是"+name+",有人干扰我,在起跑线的人都重来");
                mySignal.getStartLine().reset();
            } catch (BrokenBarrierException e) {
                System.out.println("我是"+name+",重新等待 "+sportEnum.getSportName());
            }
        }
    }
}
