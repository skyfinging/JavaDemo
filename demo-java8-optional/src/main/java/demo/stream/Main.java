package demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","d","e",null,"","f","c");
        System.out.println(list.stream().filter((t) -> t!=null && !t.isEmpty()).count());   //7个非空元素
        System.out.println(list.stream().filter((t) -> t!=null && !t.isEmpty()).distinct().count()); // 6个不重复元素

        List<String> list2 = list.stream().filter((t) -> t!=null && !t.isEmpty()).collect(Collectors.toList());
        list2.forEach((t)-> System.out.print(t+" "));
        System.out.println();

        String str = list.stream().filter((t) -> t!=null && !t.isEmpty()).limit(5).collect(Collectors.joining(","));
        System.out.println(str);

        System.out.println(list.stream().findFirst());

        list.stream().forEach((t)-> System.out.print(t+" "));
        System.out.println();
        System.out.println(list.stream().map(t->t+t).collect(Collectors.joining(",")));

        System.out.println(list.stream().filter((t) -> t!=null && !t.isEmpty()).sorted(Main::sort).collect(Collectors.joining(",")));

        IntStream intStream = list.parallelStream().flatMapToInt(Main::parse);
        System.out.println(intStream.count());

    }

    private static int sort(String a, String b){
        return -a.compareTo(b);
    }

    private static IntStream parse(String t){
        if(t!=null && t.equals("a"))
            return IntStream.rangeClosed(1,10);
        else if(t!=null && t.equals("b"))
            return IntStream.rangeClosed(11,20);
        return IntStream.of(0);
    }
}
