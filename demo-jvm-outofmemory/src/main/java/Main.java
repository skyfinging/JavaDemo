public class Main {
    public static void main(String[] args) {
        recall(10000);
    }

    public static int recall(int last){
        if(last>0){
            double a = 1;
            double b = 2;
            double c = 3;
            double d = 4;
            return recall(last-1);
        }
        return 0;
    }
}
