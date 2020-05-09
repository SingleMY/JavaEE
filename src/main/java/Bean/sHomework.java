package Bean;

public class sHomework extends Homework {
    private String IsFinished=null;
    private String Tname=null;
    private int count=0;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
