package demo.signal;

import lombok.Data;
import lombok.NonNull;

@Data
public class Signal {
    @NonNull
    private volatile Integer index;

    public void add(){
        index ++;
    }
}
