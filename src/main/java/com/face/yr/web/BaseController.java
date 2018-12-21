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
//@SessionAttributes(value = {"sessionUser","userType"})
public class BaseController {
    @Autowired
    private FaceUserService faceUserService;

    /**
     * 跳转登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String searchFace(Model model,HttpSession session) {
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,HttpSession session) {
        session.invalidate();
        return "login";
    }
    /**
     * 跳转首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    /**
     * 跳转教师页
     * @param model
     * @return
     */
    @RequestMapping(value = "/tea", method = RequestMethod.GET)
    public String tea(Model model) {

        return "teaList";
    }


    /***
     * 跳转注册页
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }


    /***
     * 登录
     * @param faceUser
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(FaceUser faceUser, HttpSession session,Model model) {
//        return faceUserService.login2(faceUser,session,model);
        return faceUserService.login(faceUser,session);
    }
}
