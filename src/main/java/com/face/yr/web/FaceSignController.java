package com.face.yr.web;

import com.face.yr.service.FaceSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * FaceSign 控制层
 *
 */
@Controller
public class FaceSignController {

    @Autowired
    private FaceSignService faceSignService;

    @RequestMapping(value = "/sign/list", method = RequestMethod.GET)
    public String listSign(Model model){
        faceSignService.listSign(model);
        return "signList";
    }

}