package Model;

import Model.Homework;
public class sHomework extends Homework {
    private String IsFinished=null;
    private String Tname=null;

    public sHomework(){
        super();
    }

    public String getIsFinished() {
        return IsFinished;
    }

    public void setIsFinished(String isFinished) {
        IsFinished = isFinished;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }
}
