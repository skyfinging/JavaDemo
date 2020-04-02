package demo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 3681460550121362457L;
    private Long userId;
    private String userName;
    private String password;

}
