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
@TableName("face_sign")
public class FaceSign implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**  */
	@TableField(value = "class_id")
	private Integer classId;

	/**  */
	@TableField(value = "stu_id")
	private Integer stuId;

	/** 打卡日期 */
	@TableField(value = "sign_date")
	private String signDate;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;


	public Integer getId() {
		return this.id;
	}

	public FaceSign setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getClassId() {
		return this.classId;
	}

	public FaceSign setClassId(Integer classId) {
		this.classId = classId;
		return this;
	}

	public Integer getStuId() {
		return this.stuId;
	}

	public FaceSign setStuId(Integer stuId) {
		this.stuId = stuId;
		return this;
	}

	public String getSignDate() {
		return this.signDate;
	}

	public FaceSign setSignDate(String signDate) {
		this.signDate = signDate;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public FaceSign setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

}
