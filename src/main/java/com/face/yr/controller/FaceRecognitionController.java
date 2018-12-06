package com.face.yr.controller;

import com.alibaba.fastjson.JSONObject;
import com.face.yr.service.FaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 14:08
 */
@RestController
public class FaceRecognitionController {
    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @RequestMapping(value = "/searchFace", method = RequestMethod.GET)
    public String searchFace(@RequestParam("image") String image,@RequestParam("userId")String userId){
        return faceRecognitionService.search(image, userId);
    }


    @RequestMapping(value = "/addFace", method = RequestMethod.GET)
    public String addFace(@RequestParam("image") String image, @RequestParam("userId")String userId, @RequestParam("userInfo")String userInfo){
        return faceRecognitionService.addUser(image, userId,userInfo) ;
    }
}
