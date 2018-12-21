package com.face.yr.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.common.FaceMapStructMapper;
import com.face.yr.domain.Response;
import com.face.yr.domain.po.FaceUser;
import com.face.yr.domain.vo.FaceClassVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceClassMapper;
import com.face.yr.domain.po.FaceClass;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private FaceUserService faceUserService;
    public String listClass(Model model) {
        Map<String,Object> map = new HashMap<>();
            List<FaceClass> faceClasses = this.selectList(new EntityWrapper<>());
            if (!CollectionUtils.isEmpty(faceClasses)){

                String[] week = new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
                List<FaceClassVo> vos = FaceMapStructMapper.INSTANCE.classesPoToVo(faceClasses);
                for (FaceClassVo vo:vos){
                    vo.setClassStateName(vo.getClassState()==1?"启用":"禁用")
                            .setClassWeekName(week[vo.getClassWeek()])
                    .setClassTeaName(faceUserService.selectById(vo.getClassTeaId()).getUserName());
                }
                map.put("classList",vos);
            }
            model.addAllAttributes(map);
            return "classList";

    }

    public String queryClass(Integer id) {
            if (ObjectUtils.isEmpty(id)){
                return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
            }
            FaceClass faceClass = this.selectById(id);
            if (!ObjectUtils.isEmpty(faceClass)){
                return new JSONObject(faceClass).toString();
            }else {
                return new JSONObject(new Response().setError_code(4001).setError_msg("编辑失败！")).toString();
            }

    }
}