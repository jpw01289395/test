package god.jiang.programmer.entity;

import org.springframework.stereotype.Component;

@Component
public class Course {
    private Long id;
    private String cid;//¿Î³ÌºÅ
    private String cname;//¿Î³ÌÃû

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
