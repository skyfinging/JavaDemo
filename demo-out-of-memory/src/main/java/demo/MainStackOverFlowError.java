package demo;

public class MainStackOverFlowError {
    // -Xmx10m -Xms10m
    public static void main(String[] args) {
        stackOverFlowError(0);
    }

    public static void stackOverFlowError(int i){
        System.out.println(i);
        stackOverFlowError(i+1);
    }

}
