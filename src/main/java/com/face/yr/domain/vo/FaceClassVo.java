package com.face.yr.domain.vo;

import java.util.Date;

public class FaceClassVo {
    private Integer id;

    /**  */
    private String className;

    /**  */
    private Integer classTeaId;
    private String classTeaName;

    /**  */
    private String classBegin;

    /**  */
    private String classEnd;

    /**  */
    private Integer classWeek;
    private String classWeekName;

    /**  */
    private Integer classState;

    private String classStateName;

    /**  */
    private Date gmtCreate;

    /**  */
    private Date gmtModify;

    public Integer getId() {
        return id;
    }

    public FaceClassVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public FaceClassVo setClassName(String className) {
        this.className = className;
        return this;
    }

    public Integer getClassTeaId() {
        return classTeaId;
    }

    public FaceClassVo setClassTeaId(Integer classTeaId) {
        this.classTeaId = classTeaId;
        return this;
    }

    public String getClassTeaName() {
        return classTeaName;
    }

    public FaceClassVo setClassTeaName(String classTeaName) {
        this.classTeaName = classTeaName;
        return this;
    }

    public String getClassBegin() {
        return classBegin;
    }

    public FaceClassVo setClassBegin(String classBegin) {
        this.classBegin = classBegin;
        return this;
    }

    public String getClassEnd() {
        return classEnd;
    }

    public FaceClassVo setClassEnd(String classEnd) {
        this.classEnd = classEnd;
        return this;
    }

    public Integer getClassWeek() {
        return classWeek;
    }

    public FaceClassVo setClassWeek(Integer classWeek) {
        this.classWeek = classWeek;
        return this;
    }

    public String getClassWeekName() {
        return classWeekName;
    }

    public FaceClassVo setClassWeekName(String classWeekName) {
        this.classWeekName = classWeekName;
        return this;
    }

    public Integer getClassState() {
        return classState;
    }

    public FaceClassVo setClassState(Integer classState) {
        this.classState = classState;
        return this;
    }

    public String getClassStateName() {
        return classStateName;
    }

    public FaceClassVo setClassStateName(String classStateName) {
        this.classStateName = classStateName;
        return this;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public FaceClassVo setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public FaceClassVo setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
        return this;
    }
}
