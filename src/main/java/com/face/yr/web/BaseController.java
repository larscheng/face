package com.face.yr.web;

import com.face.yr.domain.po.FaceUser;
import com.face.yr.service.FaceUserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/20 15:00
 */

@Controller
@SessionAttributes(value = {"sessionUser","userType"})
public class BaseController {
    @Autowired
    private FaceUserService faceUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String searchFace(Model model) {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/tea", method = RequestMethod.GET)
    public String tea(Model model) {

        return "teaList";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(FaceUser faceUser, HttpSession session,Model model) {
//        return faceUserService.login2(faceUser,session,model);
        return faceUserService.login(faceUser,session);
    }
}
