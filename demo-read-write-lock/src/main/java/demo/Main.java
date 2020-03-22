package demo;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache();

        for(int i=0;i<10;i++) {
            int finalI = i;
            new Thread(()->{
                cache.put(finalI +"", "value_"+ finalI);
                cache.get(finalI+"");
            },String.valueOf(i)).start();
        }
    }
}
