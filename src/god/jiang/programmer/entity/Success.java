package god.jiang.programmer.entity;

import org.springframework.stereotype.Component;

@Component
public class Success {
    private Long id;
    private String sn;//学生学号
    private String cid;//课程号
    private Float success;//成绩

    public double getSuccess() {
        return success;
    }

    public void setSuccess(float success) {
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sn;
    }

    public void setSid(String sid) {
        this.sn = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
