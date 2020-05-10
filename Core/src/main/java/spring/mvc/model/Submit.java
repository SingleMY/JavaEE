package spring.mvc.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Submit {
    private String h_id;
    private String sno;
    private String finished;
    private String commit_time;
    private String commit_content;
    @Override
    public int hashCode() {
        return Objects.hash(sno, h_id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Submit)) {
            return false;
        }

        Submit sh = (Submit) object;
        return Objects.equals(h_id, sh.h_id )&&Objects.equals(sno, sh.sno );
    }


}
