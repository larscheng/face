package com.face.yr.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.common.FaceMapStructMapper;
import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceSignVo;
import com.face.yr.domain.vo.FaceUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceSignMapper;
import com.face.yr.domain.po.FaceSign;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FaceSign 表数据服务层接口实现类
 */
@Service
public class FaceSignService extends ServiceImpl<IFaceSignMapper, FaceSign> {

    @Autowired
    private IFaceSignMapper faceSignMapper;
    @Autowired
    private FaceUserService faceUserService;
    @Autowired
    private FaceClassService faceClassService;

    public void listSign(Model model,Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<FaceSign> faceSigns = new ArrayList<>();
        if (ObjectUtils.isEmpty(userId)){
            faceSigns = this.selectList(new EntityWrapper<>(new FaceSign()));
        }else {
            faceSigns = this.selectList(new EntityWrapper<>(new FaceSign().setStuId(userId)));
        }

        if (!CollectionUtils.isEmpty(faceSigns)) {
            List<FaceSignVo> vos = FaceMapStructMapper.INSTANCE.signsPoToVo(faceSigns);
            for (FaceSignVo vo : vos) {
                FaceUser stu = faceUserService.selectById(vo.getStuId());
                FaceClass faceClass = faceClassService.selectById(vo.getClassId());
                vo.setClassName(faceClass.getClassName())
                        .setStuCode(stu.getUserCode())
                        .setSignStateName(vo.getSignState() == 0 ? "正常" : "迟到")
                        .setStuName(stu.getUserName());
            }
            map.put("signList", vos);
        }
        model.addAllAttributes(map);
    }
}