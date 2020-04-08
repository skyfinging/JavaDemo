package demo.signal;

import lombok.Getter;
import lombok.Setter;

public class OneSignal {
    @Getter
    private Signal signal;
    @Getter
    @Setter
    private Integer lastSignal = 0;

    public OneSignal(){
        signal = new Signal(0);
    }

    public void add(){
        signal.add();
    }

    public int getCurrentRate(){
        return signal.getIndex() - lastSignal;
    }

    public void updateLastSignal(){
        this.lastSignal = signal.getIndex();
    }
}
