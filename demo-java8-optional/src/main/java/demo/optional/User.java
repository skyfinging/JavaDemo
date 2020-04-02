package demo.optional;
import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Address address;
    public User(){
        System.out.println("create a user.");
    }
}
