package god.jiang.programmer.dao;


import god.jiang.programmer.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseDao {
    public int add(Course course);
    public int edit(Course course);
    public int delete(String course);
    public List<Course> findList(Map<String,Object> queryMap);
    public int getTotal(Map<String,Object> queryMap);
    public List<Course> findAll();
}
