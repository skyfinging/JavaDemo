package demo;

public class MainUCT {
    // -Xmx10m -Xms10m 跑不出来错误,电脑直接死机
    //Unable to create new native Thread
    public static void main(String[] args) {
        int i=0;
        while(true){
            i++;
            new Thread(()->{
                try {
                    Thread.sleep(1000000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
            System.out.println(i);
        }
    }
}
