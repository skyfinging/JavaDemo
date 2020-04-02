package demo;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("abc")).start();

        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
        System.out.println();
        features.forEach(System.out::println);

        System.out.println();
        test(features, (n)->n.startsWith("L"));

        System.out.println();
        test(features, (n)->n.contains("API"));

        System.out.println();
        consumer(features, n-> System.out.println("hi,"+n));

        System.out.println();
        supplier(()->features.get(new Random().nextInt(4)));

        System.out.println();
        function(features,(t)->t.length());

        Predicate<String> lengthPredicate = (n)->n.length()>10;
        Predicate<String> containtAPI = (n)->n.contains("API");
        Predicate<String> startWithL = (n)->n.startsWith("L");
        System.out.println();
        test(features, lengthPredicate.and(containtAPI));
        System.out.println();
        test(features, startWithL.or(containtAPI));

        System.out.println();
        features.stream().filter(lengthPredicate).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println();
        List<Integer> list = Arrays.asList(3,4,5,6,3,5);
        list.stream().distinct().forEach(System.out::println);
        IntSummaryStatistics stats = list.stream().mapToInt(x->x).summaryStatistics();
        System.out.println(stats.getMax());
        System.out.println(stats.getAverage());
        System.out.println(stats.getSum());


    }

    private static void test(List<String> list, Predicate<String> predicate){
        list.stream().filter(predicate).forEach(System.out::println);
    }

    private static void consumer(List<String> list, Consumer consumer){
        list.forEach(consumer);
    }

    private static void supplier(Supplier<String> supplier){
        for(int i=0;i<5;i++){
            System.out.println(supplier.get());
        }
    }

    private static void function(List<String> list, Function<String, Integer> function){
        list.stream().map(function).forEach(System.out::println);
    }
}
