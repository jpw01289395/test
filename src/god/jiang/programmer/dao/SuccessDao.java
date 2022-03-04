package god.jiang.programmer.dao;

import god.jiang.programmer.entity.Success;

import java.util.List;
import java.util.Map;

public interface SuccessDao {
    public int add(Success success);
    public int delete(String ids);
    public int edit(Success success);
    public List<Success> findList(Map<String, Object> queryMap);
    public List<Success> findAll();
}
