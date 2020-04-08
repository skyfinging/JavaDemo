package demo.optional;

import java.util.Optional;

/**
 * 测试JAVA8新特性：Optional
 *
 */
public class Main {
    public static void main(String[] args) {
        Optional<String> optionalNull = Optional.ofNullable(null);  //允许空值
//        Optional<String> optionalNotNull = Optional.of(null);       //不允许空值，会抛出空指针异常
        Optional<String> optionalNotNull = Optional.of("A");
        System.out.println("optionalNull = "+optionalNull);
        System.out.println("optionalNotNull = "+optionalNotNull);
        System.out.println();

        //isPresent：是否为非空
        System.out.println("optionalNull is notNull :"+optionalNull.isPresent());
        System.out.println("optionalNotNull is notNull :"+optionalNotNull.isPresent());
        System.out.println();

        //orElseGet与orElse作用一样,只是orElseGet调用生产者方法，orElse直接取值
        System.out.println("optionalNull.orElseGet = "+optionalNull.orElseGet(Main::giveAValue));
        System.out.println("optionalNotNull.orElseGet = "+optionalNotNull.orElseGet(Main::giveAValue));
        System.out.println();

        System.out.println("optionalNull.orElse = "+optionalNull.orElse("abc"));
        System.out.println("optionalNotNull.orElse = "+optionalNotNull.orElse("abc"));
        System.out.println();

        //在非空的时候，orElse会调用构造函数，orElseGet不会调用构造函数
        Optional<User> optionalUser1 = Optional.of(new User());
        optionalUser1.get().setName("abc");
        System.out.println("whatever value is null or not, orElse will create Object");
        optionalUser1.orElse(new User());
        System.out.println("when value is not null, orElseGet will not create Object");
        optionalUser1.orElseGet(User::new);
        System.out.println();

        try {
            System.out.println(optionalNull.orElseThrow(IllegalAccessException::new));
        } catch (IllegalAccessException e) {
            System.out.println("optionalNull.orElseThrow throws Exception :"+e);
        }
        try {
            System.out.println(optionalNotNull.orElseThrow(IllegalAccessException::new));
        } catch (IllegalAccessException e) {
            System.out.println("optionalNotNull.orElseThrow throws Exception "+e);
        }
        System.out.println();

        System.out.println(optionalUser1.map(Main::sayHi));         //自动将String封装成Optional<String>
        System.out.println(optionalUser1.flatMap(Main::sayHello));  //需要手动返回封装好的Optional<String>
        System.out.println();

        String countryName = optionalUser1.map(User::getAddress)
                                .map(Address::getCountry)
                                .map(Country::getCountryName).orElse("");
//        String countryName = "";
//        User user = optionalUser1.get();
//        if(user.getAddress()!=null
//                && user.getAddress().getCountry()!=null
//                && user.getAddress().getCountry().getCountryName()!=null)
//            countryName = user.getAddress().getCountry().getCountryName();

        System.out.println("countryName="+countryName);
    }

    private static String giveAValue(){
        return "main";
    }

    private static String sayHi(User user){
        return "Hi, "+user.getName();
    }

    private static Optional<String> sayHello(User user){
        return Optional.of("Hello, "+user.getName());
    }
}
