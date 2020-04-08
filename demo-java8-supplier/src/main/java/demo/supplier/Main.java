package demo.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 测试JAVA8新特性：方法引用
 * Supplier 其实是一个接口
 * 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
 * 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数
 * 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参
 * 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数
 */
public class Main {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        Supplier<Car> carSupplier = Car::new;
        carList.add(carSupplier.get());
        carList.add(carSupplier.get());
        carList.get(0).setName("A");
        carList.get(1).setName("B");
        Car policeCar = new Car("Police");

        carList.forEach(Car::go);
        carList.forEach(Car::stop);
        carList.forEach(policeCar::follow);
    }
}
