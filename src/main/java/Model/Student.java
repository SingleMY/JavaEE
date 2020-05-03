package main.java.Model;

public class Student {
    private String sno;
    private String sname;
    private String password;

    public  Student(){

    }
    public  Student(String sno , String sname , String password){
        this.sno = sno ;
        this.sname=sname;
        this.password=password;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
