package god.jiang.programmer.service;

import god.jiang.programmer.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface CourseService {
    public int  edit(Course course);
    public int  add(Course course);
    public int  delete(String joinString);
    public List<Course> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public List<Course> findAll();
}
