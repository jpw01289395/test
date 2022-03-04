package god.jiang.programmer.service.impl;

import god.jiang.programmer.dao.SuccessDao;
import god.jiang.programmer.entity.Success;
import god.jiang.programmer.service.SuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SuccessServiceImpl implements SuccessService {
    @Autowired
    private SuccessDao successDao;
    @Override
    public int add(Success success) {
        return successDao.add(success);
    }

    @Override
    public int edit(Success success) {
        return successDao.edit(success);
    }

    @Override
    public int delete(String ids) {
        return successDao.delete(ids);
    }

    @Override
    public List<Success> findList(Map<String, Object> queryMap) {
        return successDao.findList(queryMap);
    }

    @Override
    public List<Success> findAll() {
        return successDao.findAll();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return 0;
    }
}
