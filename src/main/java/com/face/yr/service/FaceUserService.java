package com.face.yr.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.domain.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceUserMapper;
import com.face.yr.domain.po.FaceUser;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 *
 * FaceUser 表数据服务层接口实现类
 *
 */
@Service
public class FaceUserService extends  ServiceImpl <IFaceUserMapper, FaceUser>  {


    /***
     * 添加教师
     * @param user
     * @return
     */
    public String addTea(FaceUser user) {
        if (ObjectUtils.isEmpty(user)){
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        if (this.insert(user)){
            return new JSONObject(new Response().setError_code(8001).setError_msg("添加成功！")).toString();
        }else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("添加失败！")).toString();
        }
    }


    /***
     * 用户列表
     * @param type 用户类型
     * @return
     */
    public String listUser(Integer type) {
        List<FaceUser> faceUsers = this.selectList(new EntityWrapper<>(new FaceUser().setUserType(type)));
        if (CollectionUtils.isEmpty(faceUsers)){
            return new JSONObject(new Response().setError_code(4001).setError_msg("暂无数据！")).toString();
        }
        return new JSONObject(faceUsers).toString();
    }
}