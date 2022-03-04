package god.jiang.programmer.controller;


import god.jiang.programmer.entity.Course;
import god.jiang.programmer.page.Page;
import god.jiang.programmer.service.CourseService;
import god.jiang.programmer.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/course")
@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * �γ��б�ҳ
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("course/course_list");
        return model;
    }

    /**
     * ��ȡ�γ��б�
     * @param cname
     * @param page
     * @return
     */
    @RequestMapping(value="/get_list",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="cname",required=false,defaultValue="") String cname,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("cname", "%"+cname+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", courseService.findList(queryMap));
        ret.put("total", courseService.getTotal(queryMap));
        return ret;
    }

    /**
     * �༭�γ���Ϣ
     * @param course
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Course course){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(course.getCname())){
            ret.put("type", "error");
            ret.put("msg", "�γ����Ʋ���Ϊ�գ�");
            return ret;
        }
        if(courseService.edit(course) <= 0){
            ret.put("type", "error");
            ret.put("msg", "�γ��޸�ʧ�ܣ�");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�γ��޸ĳɹ���");
        return ret;
    }


    /**
     * ��ӿγ���Ϣ
     * @param course
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Course course){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(course.getCname())){
            ret.put("type", "error");
            ret.put("msg", "�γ����Ʋ���Ϊ�գ�");
            return ret;
        }
        if(courseService.add(course) <= 0){
            ret.put("type", "error");
            ret.put("msg", "�γ����ʧ�ܣ�");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�γ���ӳɹ���");
        return ret;
    }

    /**
     * ɾ���γ���Ϣ
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
            if(courseService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
                ret.put("type", "error");
                ret.put("msg", "ɾ��ʧ�ܣ�");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "�ÿγ�������ѡ����Ϣ������嶯��");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�γ�ɾ���ɹ���");
        return ret;
    }

}



