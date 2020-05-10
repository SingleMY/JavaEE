package spring.mvc.model;
/**
 * Demo class
 *
 * @author moyang
 * @date 2020/5/10
 */
import lombok.Data;

import java.util.Objects;

@Data
public class Homework {
    private String h_id;
    private String title;
    private String content;
    private String create_time;
    private String deadline;
    private String tno;


    @Override
    public int hashCode() {
        return Objects.hashCode(h_id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !( object instanceof Homework)) {
            return false;
        }

        Homework homework = (Homework) object;
        return Objects.equals(h_id, homework.h_id );
    }


}
