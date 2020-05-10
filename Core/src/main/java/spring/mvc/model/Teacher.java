package spring.mvc.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Teacher {
    private String tno;
    private String tname;
    private String password;

    @Override
    public int hashCode() {
        return Objects.hashCode(tno);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Teacher)) {
            return false;
        }

        Teacher teacher = (Teacher) object;
        return Objects.equals(tno, teacher.tno );
    }


}
