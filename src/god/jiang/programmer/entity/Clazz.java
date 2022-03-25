package god.jiang.programmer.entity;

import org.springframework.stereotype.Component;

/**
 * 班级实体
 * @author llq
 *
 */
@Component
public class Clazz {
	private Long id;
	private String mid;//年级id
	private String classid;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}
}
