package demo.lombok.Bean;


import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * @Value 生成不可变属性，不会生成setter方法
 * final字段socre赋初始值，就不会在构造函数中赋值
 */
@Value
public class User3 {
    private Integer age;
    @Setter     //setter注解不起作用
    private String name;

    private Integer socre = 10;

    @NonFinal
    @Setter
    private Integer number = 2;
}
