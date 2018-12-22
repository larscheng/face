package com.face.yr.web;

import com.face.yr.domain.Response;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceUserVo;
import com.face.yr.service.FaceRecognitionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
     * @param vo
     * @return
     */
    @RequestMapping(value = "/searchFace", method = RequestMethod.POST)
    @ResponseBody
    public String searchFace(FaceUserVo vo) {
        return faceRecognitionService.search(vo);
    }


    /***
     * 注册信息注册人脸
     * @param vo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addFace", method = RequestMethod.POST)
    @ResponseBody
    public String addFace(FaceUserVo vo) {
        try {
            return faceRecognitionService.addUser(vo);
        } catch (Exception e) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("注册失败！请调整光线和角度，重新拍照进行注册！")).toString();
        }
    }
}
