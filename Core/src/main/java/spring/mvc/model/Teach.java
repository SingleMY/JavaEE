package spring.mvc.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Teach {
    private String sno;
    private String tno;

    @Override
    public int hashCode() {
        return Objects.hash(sno,tno);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Teach)) {
            return false;
        }

        Teach teach = (Teach) object;
        return Objects.equals(sno, teach.sno)&&Objects.equals(tno,teach.tno);
    }
}
