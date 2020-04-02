package demo.rmi;

public class HelloRmiService implements  IRmiService {
    private int a;

    public void setA(int a){
        this.a = a;
    }

    @Override
    public String getMsg(String params) throws Exception {
        return "hello world "+a+":"+params;
    }
}