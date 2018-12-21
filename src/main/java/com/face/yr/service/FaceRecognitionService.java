package com.face.yr.service;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.face.yr.common.AipFaceClient;
import com.face.yr.domain.Response;
import com.face.yr.domain.User_list;
import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceSign;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceUserVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 12:46
 */
@Service
public class FaceRecognitionService {

    @Autowired
    private FaceUserService faceUserService;

    @Autowired
    private FaceSignService faceSignService;

    private AipFace client = AipFaceClient.getInstance();

    @Transactional(rollbackFor = Exception.class)
    public String addUser(FaceUserVo vo) throws Exception {
        //1、注册登录账号
        FaceUser faceUser = new FaceUser()
                .setUserCode(vo.getUserCode()).setUserName(vo.getUserName()).setUserPassword(vo.getUserPassword()).setUserPhone(vo.getUserPhone())
                .setUserType(3).setGmtCreate(new Date());
        try {
            if (!faceUserService.insert(faceUser)){
                return new JSONObject(new Response().setError_code(4001).setError_msg("注册失败！")).toString();
            }
        }catch (Exception e){
            return new JSONObject(new Response().setError_code(4001).setError_msg("注册失败！")).toString();
        }

        //2、注册人脸
        String image = vo.getImage();
        String userInfo = vo.getUserName();
        String userId = faceUser.getId().toString();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        //"传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupId = "group_repeat";
        System.out.println(image);
        // 人脸注册
        JSONObject res = client.addUser(image, imageType, groupId, userId, options);
        Response response = JSON.parseObject(res.toString(), Response.class);
        System.out.println(res.toString(2));
        if (!response.getError_msg().equals("SUCCESS")){
            throw new Exception(new JSONObject(new Response().setError_code(4001).setError_msg("注册失败！请调整光线和角度，重新拍照进行注册！")).toString());
        }
        return new JSONObject(new Response().setError_code(8001).setError_msg("注册成功！")).toString();
    }

    public String search(String image, String userId,Integer classId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", userId);
        options.put("max_user_num", "1");

        //"传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupIdList = "group_repeat";
//        image = image.split(",")[1];
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        Response response = JSON.parseObject(res.toString(), Response.class);
        if (ObjectUtils.isEmpty(response.getResult())) {
//            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        List<User_list> userLists = response.getResult().getUser_list();
        if (CollectionUtils.isEmpty(userLists)) {
//            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        if (userLists.get(0).getScore() < 90f) {
            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        //修改学生签到状态
        FaceUser faceUser = faceUserService.selectById(userId);
        FaceUser user = new FaceUser().setId(faceUser.getId()).setStuSignTimes(faceUser.getStuSignTimes()+1);
        faceUserService.updateById(user);
        //添加学生签到记录
        FaceSign sign = new FaceSign()
                .setClassId(classId).setStuId(faceUser.getId()).setGmtCreate(new Date())
                .setSignDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        faceSignService.insert(sign);
        return new JSONObject(response).toString();
    }


}
