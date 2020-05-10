package spring.mvc.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Student {
    private String sno;
    private String sname;
    private String password;

    @Override
    public int hashCode() {
        return Objects.hashCode(sno);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Student)) {
            return false;
        }

       Student student = (Student) object;
        return Objects.equals(sno, student.sno);
    }
}
