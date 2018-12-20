package com.face.yr.web;

import com.face.yr.domain.po.FaceUser;
import com.face.yr.service.FaceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * FaceUser 控制层
 *
 */
@RestController
public class FaceUserController {
    @Autowired
    private FaceUserService faceUserService;

    @RequestMapping(value = "/user/addTea", method = RequestMethod.POST)
    public String addTea(@RequestBody FaceUser user){
        return faceUserService.addTea(user);
    }

    @RequestMapping(value = "/user/listTea", method = RequestMethod.GET)
    public String listTea(){
        return faceUserService.listUser(2);
    }

}