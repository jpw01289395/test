package god.jiang.programmer.service;


import god.jiang.programmer.entity.Success;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SuccessService {
    public int add(Success success);
    public int edit(Success success);
    public int delete(String ids);
    public List<Success> findList(Map<String,Object> queryMap);
    public List<Success> findAll();
    public int getTotal(Map<String,Object> queryMap);
}
