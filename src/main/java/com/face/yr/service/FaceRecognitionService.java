package com.face.yr.service;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.face.yr.common.AipFaceClient;
import com.face.yr.domain.Response;
import com.face.yr.domain.User_list;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

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

    private AipFace client = AipFaceClient.getInstance();

    public String addUser(String image, String userId, String userInfo) {
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
        return new JSONObject(response).toString();
    }

    public String search(String image,String userId) {
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
        if (ObjectUtils.isEmpty(response.getResult())){
//            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        List<User_list> userLists = response.getResult().getUser_list();
        if (CollectionUtils.isEmpty(userLists)){
//            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        if (userLists.get(0).getScore() < 90f){
            response = new Response().setError_code(404);
            return new JSONObject(response).toString();
        }
        return new JSONObject(response).toString();
    }


}
