package com.face.yr.domain.vo;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 *
 */
public class FaceUserVo implements Serializable {


	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;


	private String userName;

	/**  */
	private String userCode;

	/**  */
	private String userPhone;

	/**  */
	private String userPassword;

	/** 用户类型，0禁用1启用 */
	private Integer userState;

	/** 用户类型，1管理员2教师3学生 */
	private Integer userType;

	/** 成功签到次数 */
	private Integer stuSignTimes;

	/** 缺勤次数 */
	private Integer stuAbsenteeTimes;

	/**  */
	private Date gmtCreate;

	/**  */
	private Date gmtModify;

	private String image;


	public String getUserName() {
		return userName;
	}

	public FaceUserVo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getImage() {
		return image;
	}

	public FaceUserVo setImage(String image) {
		this.image = image;
		return this;
	}

	public Integer getId() {
		return this.id;
	}

	public FaceUserVo setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public FaceUserVo setUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public FaceUserVo setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public FaceUserVo setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}

	public Integer getUserState() {
		return this.userState;
	}

	public FaceUserVo setUserState(Integer userState) {
		this.userState = userState;
		return this;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public FaceUserVo setUserType(Integer userType) {
		this.userType = userType;
		return this;
	}

	public Integer getStuSignTimes() {
		return this.stuSignTimes;
	}

	public FaceUserVo setStuSignTimes(Integer stuSignTimes) {
		this.stuSignTimes = stuSignTimes;
		return this;
	}

	public Integer getStuAbsenteeTimes() {
		return this.stuAbsenteeTimes;
	}

	public FaceUserVo setStuAbsenteeTimes(Integer stuAbsenteeTimes) {
		this.stuAbsenteeTimes = stuAbsenteeTimes;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public FaceUserVo setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public FaceUserVo setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
