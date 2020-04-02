package demo.cyclicbarrier;

import lombok.Getter;
import lombok.Synchronized;

import java.util.concurrent.CyclicBarrier;

public class MySignal {
    @Getter
    private final CyclicBarrier startLine;
    @Getter
    private SportEnum sport;

    private String winner;

    public MySignal(int size) {
        this.startLine = new CyclicBarrier(size,()-> {System.out.println(this.getSport().getSportName()+" 比赛开始");this.nextSport();});
        sport = SportEnum.Swim;
    }

    @Synchronized
    public void nextSport(){
        sport = sport.nextSport();
    }

    @Synchronized
    public boolean setWinner(String winner){
        if(this.winner==null){
            this.winner = winner;
            return true;
        }
        return false;
    }

}

class StartLine implements Runnable{
    private final MySignal mySignal;

    StartLine(MySignal mySignal) {
        this.mySignal = mySignal;
    }

    @Override
    public void run() {
        System.out.println(mySignal.getSport().getSportName()+" 比赛开始");
        mySignal.nextSport();
    }
}
