package mbean;
import java.util.Date;

public class ServerInfo implements ServerInfoMBean {
    private int index1 = 0;
    private int index2 = 0;
    private int index3 = 0;
    private int index4 = 0;
    private int index5 = 0;

    private String name;
    public ServerInfo(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String getExecutedSqlCmdCount() {
        index1++;
        System.out.println("call getExecuteSqlCmdCount() "+ index1);
        return "call getExecuteSqlCmdCount() " + index1;
    }

    public void print(){
        index2++;
        System.out.println("call print() " + index2);
    }

    public void getPrint(){
        index3++;
        System.out.println("call getPrint() "+ index3);
    }

    public int abc(){
        index4++;
        System.out.println("call abc() "+ index4);
        return index4;
    }

    public int getAbc(){
        index5++;
        System.out.println("call getAbc() "+ index5);
        return index5;
    }

    public int getIndex(String name){
        if(name.equals("index1"))
            return index1;
        else if(name.equals("index2"))
            return index2;
        else if(name.equals("index3"))
            return index3;
        else if(name.equals("index4"))
            return index4;
        else if(name.equals("index5"))
            return index5;
        return 0;
    }
}
