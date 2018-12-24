package com.face.yr.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.face.yr.common.FaceMapStructMapper;
import com.face.yr.domain.Response;
import com.face.yr.domain.po.FaceClass;
import com.face.yr.domain.po.FaceSign;
import com.face.yr.domain.vo.FaceUserVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.face.yr.dao.IFaceUserMapper;
import com.face.yr.domain.po.FaceUser;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FaceUser 表数据服务层接口实现类
 */
@Service
public class FaceUserService extends ServiceImpl<IFaceUserMapper, FaceUser> {

    @Autowired
    private FaceClassService faceClassService;

    @Autowired
    private FaceSignService faceSignService;
    /***
     * 添加教师
     * @param user
     * @return
     */
    public String addTea(FaceUser user, Integer type) {
        if (ObjectUtils.isEmpty(user)) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        user.setUserType(type).setGmtCreate(new Date());
        if (this.insert(user)) {
            return new JSONObject(new Response().setError_code(8001).setError_msg("添加成功！")).toString();
        } else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("添加失败！")).toString();
        }
    }


    /***
     * 用户列表
     * @param type 用户类型
     * @return
     */
    public void listUser(Model model, Integer type) {
        Map<String, Object> map = new HashMap<>();
        List<FaceUser> faceUsers = this.selectList(new EntityWrapper<>(new FaceUser().setUserType(type)));
        if (!CollectionUtils.isEmpty(faceUsers)) {
            List<FaceUserVo> vos = FaceMapStructMapper.INSTANCE.UsersPoToVo(faceUsers);
            for (FaceUserVo vo : vos) {
                vo.setUserStateName(vo.getUserState() == 1 ? "启用" : "禁用");
            }
            map.put("userList", vos);
        }
        model.addAllAttributes(map);
    }

    public String login(FaceUser faceUser, HttpSession session, Model model) {

        Map<String,Object> map = new HashMap<>();
        FaceUser user = this.selectOne(new EntityWrapper<>(faceUser));
        if (ObjectUtils.isEmpty(user)) {
            map.put("msg", "登陆失败,请重试");
            model.addAllAttributes(map);
            return "login";
        }
        //统计当天打卡情况
        map = checkSign(map,user);


        model.addAllAttributes(map);


        session.setAttribute("sessionUser", user);
        session.setAttribute("userType", user.getUserType());
        return "index";
    }

    /**
     * 统计打卡情况
     * @param map
     */
    public Map<String,Object> checkSign(Map<String,Object> map,FaceUser user){
        List<FaceClass> faceClasses = faceClassService.selectList(new EntityWrapper<>());
        FaceClass faceClass = faceClasses.get(0);
        String[] time = faceClass.getClassBegin().split(":");
        List<FaceUser> faceUsers = this.selectList(new EntityWrapper<>(new FaceUser().setUserType(3)).orderBy("gmt_login desc"));
        List<FaceUser> signList = new ArrayList<>();
        List<FaceUser> noSignList = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar.set(Calendar.SECOND, 0);
        //获取今天的星期，1星期天---7星期六
        int weekday=calendar.get(Calendar.DAY_OF_WEEK);
        int chinaWeek;
        if (weekday==1){
            chinaWeek =7;
        }else {
            chinaWeek=weekday-1;
        }
        Date signStartTime = calendar.getTime();
        //今天的打卡开始时间,提前10分钟
        long startTime = signStartTime.getTime()-80*60*60;
        //截止时间
        long endTime = signStartTime.getTime();
        String[] overTime = faceClass.getClassEnd().split(":");
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(new Date());
        calendar1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(overTime[0]));
        calendar1.set(Calendar.MINUTE, Integer.parseInt(overTime[1]));
        calendar1.set(Calendar.SECOND, 0);
        //下课时间
        long signOverTime = calendar1.getTime().getTime();
        for (FaceUser faceUser1:faceUsers){
            if (!ObjectUtils.isEmpty(faceUser1.gmtLogin)&&faceUser1.getGmtLogin().getTime()>startTime&&faceUser1.getGmtLogin().getTime()<signOverTime){
                //当天已打卡
                signList.add(faceUser1);
            }else {
                faceUser1.setStuAbsenteeTimes(faceUser1.getStuAbsenteeTimes()+1);
                noSignList.add(faceUser1);
            }
        }
        if (user.getUserType().equals(3)){

            if (chinaWeek==faceClass.getClassWeek()){
                if (!ObjectUtils.isEmpty(user.gmtLogin)&&user.getGmtLogin().getTime()>startTime&&user.getGmtLogin().getTime()<signOverTime){
                    //当天已打卡
                    map.put("isSign",1);
                }else {
                    long now = System.currentTimeMillis();
                    if (now>signOverTime){
                        //已错过下课时间，旷课
                        map.put("isSign",3);
                    }else if (now>endTime){
                        //已错过上课时间，迟到
                        map.put("isSign",4);
                    }else {
                        //等待打卡
                        map.put("isSign",0);
                    }
                }
            }else {
                //今天不用上课
                map.put("isSign",2);
            }
        }


        map.put("user",user);
        map.put("signList",signList);
        map.put("noSignList",noSignList);
        return map;
    }


    public String login2(FaceUser faceUser, HttpSession session, Model model) {
        FaceUser user = this.selectOne(new EntityWrapper<>(faceUser));
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(user)) {
            System.out.println("------------------------");
            map.put("msg", "登录失败");
            model.addAllAttributes(map);
            return "";
        }
        session.setAttribute("sessionUser", user);
        session.setAttribute("userType", user.getUserType());
        return "index";
    }

    @Transactional
    public String delUser(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("删除失败！")).toString();
        }
        FaceUser user = this.selectById(id);
        if (this.deleteById(id)) {
            if (user.getUserType()==3){
                List<FaceSign> list = faceSignService.selectList(new EntityWrapper<>(new FaceSign().setStuId(user.getId())));
                if (!CollectionUtils.isEmpty(list)){
                   list.forEach(a->faceSignService.deleteById(a.getId()));
                }
            }
            return new JSONObject(new Response().setError_code(8001).setError_msg("删除成功！")).toString();
        }
        return new JSONObject(new Response().setError_code(4001).setError_msg("删除失败！")).toString();
    }

    public String updateUser(FaceUser user) {
        if (ObjectUtils.isEmpty(user)) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        if (this.updateById(user)) {
            return new JSONObject(new Response().setError_code(8001).setError_msg("编辑成功！")).toString();
        } else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("编辑失败！")).toString();
        }
    }

    public String queryUser(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            return new JSONObject(new Response().setError_code(4001).setError_msg("重新填写！")).toString();
        }
        FaceUser user = this.selectById(id);
        if (!ObjectUtils.isEmpty(user)) {
            return new JSONObject(user).toString();
        } else {
            return new JSONObject(new Response().setError_code(4001).setError_msg("编辑失败！")).toString();
        }
    }
}