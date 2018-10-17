package cn.com.exz.hc.entity;

import java.io.Serializable;

/**
 * Created by weicao on 2018/9/29.
 */


public class NoticeEntity implements Serializable{
    private String notice_name;
    private String notice_type;
    private String notice_status;
    private String notice_content;
    private String notice_path;
    private String release_time;


    public String getNotice_name() {
        return notice_name;
    }

    public void setNotice_name(String notice_name) {
        this.notice_name = notice_name;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_status() {
        return notice_status;
    }

    public void setNotice_status(String notice_status) {
        this.notice_status = notice_status;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getNotice_path() {
        return notice_path;
    }

    public void setNotice_path(String notice_path) {
        this.notice_path = notice_path;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }
}
