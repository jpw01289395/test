package god.jiang.programmer.service.impl;


import god.jiang.programmer.dao.CourseDao;
import god.jiang.programmer.entity.Course;
import god.jiang.programmer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public int edit(Course course) {
        return courseDao.edit(course);
    }

    @Override
    public int add(Course course) {
        return courseDao.add(course);
    }

    @Override
    public int delete(String ids) {
        return courseDao.delete(ids);
    }

    @Override
    public List<Course> findList(Map<String, Object> queryMap) {
        return courseDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return courseDao.getTotal(queryMap);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }
}
