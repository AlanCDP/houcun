package cn.com.exz.hc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weicao on 2018/8/22.
 */

public class ThreeDataModel {


    private List<UserListBean> userList;
    private List<PostListBean> postList;
    private List<DepartListBean> departList;

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public List<PostListBean> getPostList() {
        return postList;
    }

    public void setPostList(List<PostListBean> postList) {
        this.postList = postList;
    }

    public List<DepartListBean> getDepartList() {
        return departList;
    }

    public void setDepartList(List<DepartListBean> departList) {
        this.departList = departList;
    }

    public static class UserListBean implements Serializable {
        /**
         * id : 402885ac641fabac01642010bbc2005e
         * realname : 张金贵
         */

        private String id;
        private String realname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }

    public static class PostListBean implements Serializable{
        /**
         * id : 402885ac641fabac0164201abfef01c8
         * post_name : 矿长
         */

        private String id;
        private String post_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPost_name() {
            return post_name;
        }

        public void setPost_name(String post_name) {
            this.post_name = post_name;
        }
    }

    public static class DepartListBean implements Serializable{
        /**
         * id : 402885ac641fabac01641fc081270002
         * departname : 供应科
         */

        private String id;
        private String departname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }
    }
}
