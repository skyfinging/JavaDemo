package demo.lombok.Bean;

import lombok.Getter;

public class User7 {
    @Getter(lazy = true)        //将生成getCache()方法
    private final Integer cache = getResult();

    public Integer getResult(){
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }
}
