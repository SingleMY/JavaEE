package spring.mvc.model;

import lombok.Data;

@Data
public class sHomework extends Homework {
    private String IsFinished=null;
    private String Tname=null;
    private int count=0;

}
