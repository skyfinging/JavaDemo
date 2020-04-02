package demo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ExcelHead {
    @Id
    private Integer id;
    private String name;

    public ExcelHead(){}

    public ExcelHead(Integer id){
        this.id = id;
    }
}
