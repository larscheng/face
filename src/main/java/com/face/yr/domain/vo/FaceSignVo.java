package com.face.yr.domain.vo;

import java.util.Date;

public class FaceSignVo {
    private Integer id;

    /**  */
    private Integer classId;

    /**  */
    private Integer stuId;

    /** 打卡类型 */
    private Integer signState;
    private String signStateName;

    /**  */
    private Date gmtCreate;

    private String stuName;

    private String stuCode;

    private String className;

    public Integer getId() {
        return id;
    }

    public FaceSignVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getClassId() {
        return classId;
    }

    public FaceSignVo setClassId(Integer classId) {
        this.classId = classId;
        return this;
    }

    public Integer getStuId() {
        return stuId;
    }

    public FaceSignVo setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getSignState() {
        return signState;
    }

    public FaceSignVo setSignState(Integer signState) {
        this.signState = signState;
        return this;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public FaceSignVo setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public String getSignStateName() {
        return signStateName;
    }

    public FaceSignVo setSignStateName(String signStateName) {
        this.signStateName = signStateName;
        return this;
    }

    public String getStuName() {
        return stuName;
    }

    public FaceSignVo setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }

    public String getStuCode() {
        return stuCode;
    }

    public FaceSignVo setStuCode(String stuCode) {
        this.stuCode = stuCode;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public FaceSignVo setClassName(String className) {
        this.className = className;
        return this;
    }
}
