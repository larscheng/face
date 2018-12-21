package com.face.yr.web;

import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceUserVo;
import com.face.yr.service.FaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 14:08
 */
@Controller
public class FaceRecognitionController {
    @Autowired
    private FaceRecognitionService faceRecognitionService;

    /**
     * 签到
     * @param image
     * @param userId
     * @param classId
     * @return
     */
    @RequestMapping(value = "/searchFace", method = RequestMethod.GET)
    @ResponseBody
    public String searchFace(@RequestParam("image") String image, @RequestParam("userId") String userId, @RequestParam("classId") Integer classId) {
        return faceRecognitionService.search(image, userId,classId);
    }


    /***
     * 注册信息注册人脸
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addFace", method = RequestMethod.POST)
    @ResponseBody
    public String addFace(FaceUserVo vo) throws Exception {
        return faceRecognitionService.addUser(vo);
    }
}
