package demo;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Student {
    private final String name;
    @Getter
    private final Integer scoreA;
    @Getter
    private final Integer scoreB;

    public Student(String name, Integer scoreA, Integer scoreB) {
        this.name = name;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public static int compare(Student studentA, Student studentB){
        if(studentA.getScoreA()>studentB.getScoreA())
            return 1;
        else if(studentA.getScoreA()<studentB.getScoreA())
            return -1;
        return 0;
    }

}
