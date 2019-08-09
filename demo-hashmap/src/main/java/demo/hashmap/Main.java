package demo.hashmap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("b", 4);
        map.put("d", 4);
        map.put("e", null);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);
        map.put("i", 8);
        map.put("j", 9);
        map.put("k", 9);
        map.put("ab", 10);
        map.put(null, 123);
        map.put("ab", 11);

        System.out.println("a="+map.get("a"));
        System.out.println("b="+map.get("b"));
        System.out.println("c="+map.get("c"));
        System.out.println("d="+map.get("d"));
        System.out.println("e="+map.get("e"));
        System.out.println("f="+map.get("f"));
        System.out.println("g="+map.get("g"));
        System.out.println("h="+map.get("h"));
        System.out.println("i="+map.get("i"));
        System.out.println("j="+map.get("j"));
        System.out.println("k="+map.get("k"));
        System.out.println("ab="+map.get("ab"));
        System.out.println(map.get(null));
    }
}
