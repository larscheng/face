package com.face.yr.web;

import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.service.FaceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * FaceClass 控制层
 *
 */
@Controller
public class FaceClassController {

    @Autowired
    private FaceClassService faceClassService;

    /***
     * 课程添加
     * @param faceClass
     * @return
     */
    @RequestMapping(value = "/class/add", method = RequestMethod.POST)
    @ResponseBody
    public String addClass(FaceClass faceClass){
        return faceClassService.addClass(faceClass);
    }

    /***
     * 课程更新
     * @param faceClass
     * @return
     */
    @RequestMapping(value = "/class/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateClass( FaceClass faceClass){
        return faceClassService.updateClass(faceClass);
    }

    /***
     * 课程查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/class/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryClass(Integer id){
        return faceClassService.queryClass(id);
    }

    /***
     * 课程列表跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/class/list", method = RequestMethod.GET)
    public String listClass(Model model){
        return faceClassService.listClass(model);
    }
}