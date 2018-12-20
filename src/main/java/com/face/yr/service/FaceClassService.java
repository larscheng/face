package com.face.yr.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.domain.Response;
import com.face.yr.domain.po.FaceUser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceClassMapper;
import com.face.yr.domain.po.FaceClass;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 *
 * FaceClass 表数据服务层接口实现类
 *
 */
@Service
public class FaceClassService extends  ServiceImpl <IFaceClassMapper, FaceClass>  {

    /***
     * 添加课程
     * @param faceClass
     * @return
     */
    public String addClass(FaceClass faceClass) {
        if (ObjectUtils.isEmpty(faceClass)){
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        if (this.insert(faceClass)){
            return new JSONObject(new Response().setError_code(8001).setError_msg("添加成功！")).toString();
        }else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("添加失败！")).toString();
        }
    }

    public String updateClass(FaceClass faceClass) {
        if (ObjectUtils.isEmpty(faceClass)){
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        if (this.updateById(faceClass)){
            return new JSONObject(new Response().setError_code(8001).setError_msg("修改成功！")).toString();
        }else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("修改失败！")).toString();
        }
    }

    public String listClass() {
            List<FaceClass> faceClasses = this.selectList(new EntityWrapper<>());
            if (CollectionUtils.isEmpty(faceClasses)){
                return new JSONObject(new Response().setError_code(4001).setError_msg("暂无数据！")).toString();
            }
            return new JSONObject(faceClasses).toString();

    }
}