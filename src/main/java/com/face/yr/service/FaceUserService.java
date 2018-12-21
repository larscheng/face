package com.face.yr.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.domain.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceUserMapper;
import com.face.yr.domain.po.FaceUser;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        user.setUserType(2).setGmtCreate(new Date());
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
    public void listUser(Model model,Integer type) {
        Map<String,Object> map = new HashMap<>();
        List<FaceUser> faceUsers = this.selectList(new EntityWrapper<>(new FaceUser().setUserType(type)));
        if (!CollectionUtils.isEmpty(faceUsers)){
            map.put("teaList",faceUsers);
        }
        model.addAllAttributes(map);
    }

    public String login(FaceUser faceUser, HttpSession session) {
        FaceUser user = this.selectOne(new EntityWrapper<>(faceUser));
        if (ObjectUtils.isEmpty(user)){
            System.out.println("------------------------");
            return new JSONObject(new Response().setError_code(4001).setError_msg("登录失败！")).toString();
        }
        session.setAttribute("sessionUser",user);
        session.setAttribute("userType",user.getUserType());
        return new JSONObject(new Response().setError_code(8001)).toString();
    }
    public String login2(FaceUser faceUser, HttpSession session, Model model) {
        FaceUser user = this.selectOne(new EntityWrapper<>(faceUser));
        Map<String ,Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(user)){
            System.out.println("------------------------");
            map.put("msg","登录失败");
            model.addAllAttributes(map);
            return "";
        }
        session.setAttribute("sessionUser",user);
        session.setAttribute("userType",user.getUserType());
        return "index";
    }

    public String delUser(Integer id) {
        if (ObjectUtils.isEmpty(id)){
            return new JSONObject(new Response().setError_code(4001).setError_msg("删除失败！")).toString();
        }
        if (this.deleteById(id)){
            return new JSONObject(new Response().setError_code(8001).setError_msg("删除成功！")).toString();
        }
        return new JSONObject(new Response().setError_code(4001).setError_msg("删除失败！")).toString();
    }
}