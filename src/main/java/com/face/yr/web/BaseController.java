package com.face.yr.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/20 15:00
 */

@Controller
public class BaseController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String searchFace(Model model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "register";
    }
}
