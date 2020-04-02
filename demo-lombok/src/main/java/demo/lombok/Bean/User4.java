package demo.lombok.Bean;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User4 {

    private Integer age;
    private String name;
    @Singular       //与Builder配合使用，将生成number()，numbers()，cleanNumbers()方法，用于往集合中添加元素
    private List<Integer> numbers;
}
