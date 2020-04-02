package demo.java8;

/**
 * 测试JAVA8新特性
 * 接口默认方法、接口静态方法
 */
public class Main {
    public static void main(String[] args) {
        TypeEnum typeEnum = IEnum.getEnumByValue(TypeEnum.class, 1);
        System.out.println(typeEnum);

        TypeEnum typeEnum1 = IEnum.getEnumByName(TypeEnum.class, "AType");
        System.out.println(typeEnum1);

        System.out.println(typeEnum.getDefaultValue());
    }
}
