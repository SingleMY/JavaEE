package com.homework.java.Model;

public class Teach {
    private String sno;
    private String tno;

    public Teach(String sno,String tno) {
        this.sno = sno;
        this.tno = tno;
    }

    public Teach(){

    }
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
