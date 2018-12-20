package com.face.yr.service;

import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceSignMapper;
import com.face.yr.domain.po.FaceSign;
import com.baomidou.framework.service.impl.ServiceImpl; 

/**
 *
 * FaceSign 表数据服务层接口实现类
 *
 */
@Service
public class FaceSignService extends  ServiceImpl <IFaceSignMapper, FaceSign>  {


    public String listSign() {
        return "111";
    }
}