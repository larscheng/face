package com.face.yr.service;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import java.util.*;

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
    @Autowired
    private FaceClassService faceClassService;

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

    //签到+人脸认证
    public String search(FaceUserVo vo) {
        Integer userId = vo.getId();
        String image = vo.getImage();


        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", userId.toString());
        options.put("max_user_num", "1");

        //"传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String imageType = "BASE64";
        String groupIdList = "group_repeat";
//        image = image.split(",")[1];
        // 人脸搜索
        JSONObject res = client.search(image, imageType, groupIdList, options);
        Response response = JSON.parseObject(res.toString(), Response.class);
        if (ObjectUtils.isEmpty(response.getResult())) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("签到失败！请调整光线和角度，重新拍照进行签到！")).toString();
        }
        List<User_list> userLists = response.getResult().getUser_list();
        if (CollectionUtils.isEmpty(userLists)) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("签到失败！请调整光线和角度，重新拍照进行签到！")).toString();
        }
        if (userLists.get(0).getScore() < 90f) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("签到失败！请调整光线和角度，重新拍照进行签到！")).toString();
        }
        FaceUser faceUser = faceUserService.selectById(userId);
        FaceUser user = new FaceUser();
        //判断正常还是迟到
        List<FaceClass> faceClasses = faceClassService.selectList(new EntityWrapper<>());
        FaceClass faceClass = faceClasses.get(0);
        String[] time = faceClass.getClassBegin().split(":");
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(new Date());
        calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar1.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar1.set(Calendar.SECOND, 0);
        //上课时间
        long signStartTime = calendar1.getTime().getTime();
        long now = System.currentTimeMillis();

        FaceSign sign = new FaceSign();
        if (now>signStartTime){
            //已错过签到时间，迟到
            user = new FaceUser().setId(faceUser.getId()).setGmtLogin(new Date()).setStuLateTimes(faceUser.getStuLateTimes()+1);
            sign = new FaceSign()
                    .setClassId(faceClass.getId()).setStuId(faceUser.getId()).setGmtCreate(new Date())
                    .setSignState(1)
                    .setGmtCreate(new Date());

            //更新学生信息
            faceUserService.updateById(user);
            //添加学生签到记录
            faceSignService.insert(sign);
            return new JSONObject(new Response().setError_code(4001).setError_msg("签到成功，今天来的有点晚了迟到了哦！！！")).toString();
        }else {
            //提前打卡
            user = new FaceUser().setId(faceUser.getId()).setGmtLogin(new Date()).setStuSignTimes(faceUser.getStuSignTimes()+1);
            sign = new FaceSign()
                    .setClassId(faceClass.getId()).setStuId(faceUser.getId()).setGmtCreate(new Date())
                    .setSignState(0)
                    .setGmtCreate(new Date());

            //更新学生信息
            faceUserService.updateById(user);
            //添加学生签到记录
            faceSignService.insert(sign);
            return new JSONObject(new Response().setError_code(4001).setError_msg("签到成功！！！")).toString();
        }
    }


}
