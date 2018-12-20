package com.face.yr.web;

import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.service.FaceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * FaceClass 控制层
 *
 */
@RestController
public class FaceClassController {

    @Autowired
    private FaceClassService faceClassService;

    @RequestMapping(value = "/class/addClass", method = RequestMethod.POST)
    public String addClass(@RequestBody FaceClass faceClass){
        return faceClassService.addClass(faceClass);
    }

    @RequestMapping(value = "/class/updateClass", method = RequestMethod.POST)
    public String updateClass(@RequestBody FaceClass faceClass){
        return faceClassService.updateClass(faceClass);
    }

    @RequestMapping(value = "/class/listClass", method = RequestMethod.GET)
    public String listClass(){
        return faceClassService.listClass();
    }
}