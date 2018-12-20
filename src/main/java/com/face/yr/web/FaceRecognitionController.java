package com.face.yr.web;

import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceUserVo;
import com.face.yr.service.FaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String searchFace(@RequestParam("image") String image, @RequestParam("userId") String userId, @RequestParam("classId") Integer classId) {
        return faceRecognitionService.search(image, userId,classId);
    }


    @RequestMapping(value = "/addFace", method = RequestMethod.POST)
    public String addFace(@RequestBody FaceUserVo vo) {
        return faceRecognitionService.addUser(vo);
    }
}
