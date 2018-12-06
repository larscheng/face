package com.face.yr.domain;

import java.util.List;

/**
 * 描述:
 *
 * @author zhengql
 * @date 2018/12/6 13:38
 */
public class Result {

        private String face_token;
        private Location location;
        private List<User_list> user_list;


        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }
        public String getFace_token() {
            return face_token;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
        public Location getLocation() {
            return location;
        }

    public List<User_list> getUser_list() {
        return user_list;
    }

    public Result setUser_list(List<User_list> user_list) {
        this.user_list = user_list;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "face_token='" + face_token + '\'' +
                ", location=" + location +
                ", user_list=" + user_list +
                '}';
    }
}
