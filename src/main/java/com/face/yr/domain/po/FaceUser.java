package com.face.yr.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("face_user")
public class FaceUser implements Serializable {

	@TableField(exist = false)
	public static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	public Integer id;


	/**  */
	@TableField(value = "user_name")
	public String userName;

	/**  */
	@TableField(value = "user_code")
	public String userCode;

	/**  */
	@TableField(value = "user_phone")
	public String userPhone;

	/**  */
	@TableField(value = "user_password")
	public String userPassword;

	/** 用户类型，0禁用1启用 */
	@TableField(value = "user_state")
	public Integer userState;

	/** 用户类型，1管理员2教师3学生 */
	@TableField(value = "user_type")
	public Integer userType;

	/** 成功签到次数 */
	@TableField(value = "stu_sign_times")
	public Integer stuSignTimes;

	/** 缺勤次数 */
	@TableField(value = "stu_absentee_times")
	public Integer stuAbsenteeTimes;

	/**  */
	@TableField(value = "gmt_create")
	public Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	public Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public FaceUser setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public FaceUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public FaceUser setUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public FaceUser setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public FaceUser setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public FaceUser setUserState(Integer userState) {
		this.userState = userState;
		return this;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public FaceUser setUserType(Integer userType) {
		this.userType = userType;
		return this;
	}

	public Integer getStuSignTimes() {
		return this.stuSignTimes;
	}

	public FaceUser setStuSignTimes(Integer stuSignTimes) {
		this.stuSignTimes = stuSignTimes;
		return this;
	}

	public Integer getStuAbsenteeTimes() {
		return this.stuAbsenteeTimes;
	}

	public FaceUser setStuAbsenteeTimes(Integer stuAbsenteeTimes) {
		this.stuAbsenteeTimes = stuAbsenteeTimes;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public FaceUser setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public FaceUser setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
