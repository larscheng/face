package com.face.yr.domain;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 14:02
 */

public class User_list {

    private double score;
    private String group_id;
    private String user_id;
    private String user_info;
    public void setScore(double score) {
        this.score = score;
    }
    public double getScore() {
        return score;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
    public String getGroup_id() {
        return group_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }
    public String getUser_info() {
        return user_info;
    }


    @Override
    public String toString() {
        return "User_list{" +
                "score=" + score +
                ", group_id='" + group_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_info='" + user_info + '\'' +
                '}';
    }
}