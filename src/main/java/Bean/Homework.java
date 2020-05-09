package Bean;

public class Homework {
    private String h_id;
    private String title;
    private String content;
    private String create_time;
   // private String update_time;
    private String deadline;
    private String tno;

    public Homework() {
    }
    public Homework( String h_id, String title, String content ,
                     String create_time, String deadline , String tno) {
        this.h_id=h_id;
        this.title=title;
        this.content=content;
        this.create_time=create_time;
        this.deadline=deadline;
        this.tno=tno;
    }


    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

//    public String getUpdate_time() {
//        return update_time;
//    }
//
//    public void setUpdate_time(String update_time) {
//        this.update_time = update_time;
//    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
