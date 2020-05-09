package Bean;

public class Submit {
    private String h_id;
    private String sno;
    private String finished;
    private String commit_time;
    private String commit_content;

    public Submit(){

    }
    public Submit(String h_id, String sno, String finished, String commit_time, String commit_content) {
        this.h_id = h_id;
        this.sno = sno;
        this.finished = finished;
        this.commit_time = commit_time;
        this.commit_content = commit_content;
    }


    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getCommit_time() {
        return commit_time;
    }

    public void setCommit_time(String commit_time) {
        this.commit_time = commit_time;
    }

    public String getCommit_content() {
        return commit_content;
    }

    public void setCommit_content(String commit_content) {
        this.commit_content = commit_content;
    }
}
