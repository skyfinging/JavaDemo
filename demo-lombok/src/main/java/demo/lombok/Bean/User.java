package demo.lombok.Bean;

import lombok.Data;
import lombok.NonNull;

/**
 * @Data 自动生成toString()
 * @NonNull 自动生成构造函数
 */
@Data
public class User {
    @NonNull
    private Integer age;
    private String name;
}
