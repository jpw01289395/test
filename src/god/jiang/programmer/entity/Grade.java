package god.jiang.programmer.entity;

import org.springframework.stereotype.Component;

/**
 * 年级实体
 * @author llq
 *
 */
@Component
public class Grade {
	private Long id;
	private String mid;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}


}
