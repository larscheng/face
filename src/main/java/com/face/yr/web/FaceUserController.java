package com.face.yr.web;

import com.face.yr.domain.po.FaceUser;
import com.face.yr.service.FaceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * FaceUser 控制层
 *
 */
@Controller
public class FaceUserController {
    @Autowired
    private FaceUserService faceUserService;

    @RequestMapping(value = "/user/addTea", method = RequestMethod.POST)
    @ResponseBody
    public String addTea(FaceUser user){
        return faceUserService.addTea(user);
    }

    @RequestMapping(value = "/user/delTea", method = RequestMethod.GET)
    @ResponseBody
    public String delTea(Integer id){
        return faceUserService.delUser(id);
    }

    @RequestMapping(value = "/user/listTea", method = RequestMethod.GET)
    public String listTea(Model model){
        faceUserService.listUser(model,2);
        return "teaList";
    }

}