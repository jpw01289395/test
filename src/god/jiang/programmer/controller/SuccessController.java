package god.jiang.programmer.controller;


import god.jiang.programmer.entity.Course;
import god.jiang.programmer.entity.Student;
import god.jiang.programmer.entity.Success;
import god.jiang.programmer.page.Page;
import god.jiang.programmer.service.CourseService;
import god.jiang.programmer.service.StudentService;
import god.jiang.programmer.service.SuccessService;
import god.jiang.programmer.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ɼ���Ϣ����
 * @author llq
 *
 */

@Controller
@RequestMapping("/success")
public class SuccessController {
    @Autowired
    private SuccessService successService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    /**
     * �ɼ��б�ҳ
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("success/success_list");
        List<Course> findAll = courseService.findAll();
        List<Student> sfindAll = studentService.findAll();
        model.addObject("studentList",sfindAll);
        model.addObject("studentListJson",JSONArray.fromObject(sfindAll));
        model.addObject("courseList",findAll );
        model.addObject("courseListJson", JSONArray.fromObject(findAll));
        return model;
    }

    /**
     * ��ȡ�ɼ��б�
     * @param success
     * @param page
     * @param sid
     * @param cid
     * @return
     */
    @RequestMapping(value="/get_list",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="success",required=false,defaultValue="") String success,
            @RequestParam(value="sid",required=false) String sid,
            @RequestParam(value = "cid",required = false) String cid,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("success", "%"+success+"%");
        if(cid != null){
            queryMap.put("cid", cid);
        }
        if (sid != null){
            queryMap.put("sid",sid);
        }
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", courseService.findList(queryMap));
        ret.put("total", courseService.getTotal(queryMap));
        return ret;
    }

    /**
     * �༭�ɼ���Ϣ
     * @param success
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Success success){
        Map<String, String> ret = new HashMap<String, String>();
        if(success.getSid() == null ){
            ret.put("type", "error");
            ret.put("msg", "ѧ��ѧ�Ų���Ϊ�գ�");
            return ret;
        }
        if(success.getCid() == null){
            ret.put("type", "error");
            ret.put("msg", "�����γ̺Ų���Ϊ�գ�");
            return ret;
        }
        if(successService.edit(success) <= 0){
            ret.put("type", "error");
            ret.put("msg", "�ɼ��޸�ʧ�ܣ�");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�ɼ��޸ĳɹ���");
        return ret;
    }


    /**
     * ��ӳɼ���Ϣ
     * @param success
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Success success){
        Map<String, String> ret = new HashMap<String, String>();
        if(success.getSid() == null){
            ret.put("type", "error");
            ret.put("msg", "ѧ��ѧ�Ų���Ϊ�գ�");
            return ret;
        }
        if(success.getCid() == null){
            ret.put("type", "error");
            ret.put("msg", "��ѡ�������γ̺ţ�");
            return ret;
        }
        if(successService.add(success) <= 0){
            ret.put("type", "error");
            ret.put("msg", "�ɼ����ʧ�ܣ�");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�ɼ���ӳɹ���");
        return ret;
    }

    /**
     * ɾ���ɼ���Ϣ
     * @param ids
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value="ids[]",required=true) Long[] ids
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null || ids.length == 0){
            ret.put("type", "error");
            ret.put("msg", "��ѡ��Ҫɾ�������ݣ�");
            return ret;
        }
        try {
            if(successService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
                ret.put("type", "error");
                ret.put("msg", "ɾ��ʧ�ܣ�");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "�óɼ��¿��ܴ���������Ϣ������嶯��");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�ɼ�ɾ���ɹ���");
        return ret;
    }

}


