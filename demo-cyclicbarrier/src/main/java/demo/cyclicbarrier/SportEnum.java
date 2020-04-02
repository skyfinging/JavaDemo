package demo.cyclicbarrier;

import lombok.Getter;

public enum SportEnum {
    Swim("游泳"),Run("跑步"),Cycle("骑车");
    @Getter
    private final String sportName;

    SportEnum(String sportName) {
        this.sportName = sportName;
    }

    public SportEnum nextSport(){
        if(this == Swim)
            return Run;
        else if(this == Run)
            return Cycle;
        else
            return Swim;
    }
}
