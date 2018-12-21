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
@TableName("face_class")
public class FaceClass implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**  */
	@TableField(value = "class_name")
	private String className;

	/**  */
	@TableField(value = "class_tea_id")
	private Integer classTeaId;

	/**  */
	@TableField(value = "class_begin")
	private String classBegin;

	/**  */
	@TableField(value = "class_end")
	private String classEnd;

	/**  */
	@TableField(value = "class_week")
	private Integer classWeek;

	/**  */
	@TableField(value = "class_state")
	private Integer classState;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;


	public Integer getId() {
		return this.id;
	}

	public FaceClass setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getClassName() {
		return this.className;
	}

	public FaceClass setClassName(String className) {
		this.className = className;
		return this;
	}

	public Integer getClassTeaId() {
		return this.classTeaId;
	}

	public FaceClass setClassTeaId(Integer classTeaId) {
		this.classTeaId = classTeaId;
		return this;
	}

	public String getClassBegin() {
		return this.classBegin;
	}

	public FaceClass setClassBegin(String classBegin) {
		this.classBegin = classBegin;
		return this;
	}

	public String getClassEnd() {
		return this.classEnd;
	}

	public FaceClass setClassEnd(String classEnd) {
		this.classEnd = classEnd;
		return this;
	}

	public Integer getClassWeek() {
		return this.classWeek;
	}

	public FaceClass setClassWeek(Integer classWeek) {
		this.classWeek = classWeek;
		return this;
	}

	public Integer getClassState() {
		return this.classState;
	}

	public FaceClass setClassState(Integer classState) {
		this.classState = classState;
		return this;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public FaceClass setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
		return this;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public FaceClass setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
		return this;
	}

}
