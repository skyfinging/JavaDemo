package demo;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class MyObject implements MyCloneable{
    @NonNull
    String name;

    @Override
    public MyObject cloneObject() {
        //返回一个新的MyObject对象出来，相当于new MyObject()
        return MyObject.of(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !obj.getClass().equals(MyObject.class) )
            return false;
        MyObject myObject = (MyObject) obj;
        return Objects.equals(name, myObject.name);
    }
}
