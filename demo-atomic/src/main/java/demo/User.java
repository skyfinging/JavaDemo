package demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;

    @Override
    public boolean equals(Object user){
        if(user==null)
            return false;
        if(user instanceof User){
            User user1 = (User) user;
            return Objects.equals(id,user1.id) && Objects.equals(name, user1.name);
        }
        return false;
    }
}
