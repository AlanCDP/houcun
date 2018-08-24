package cn.com.exz.hc.entity;

import cn.com.szw.lib.myframework.entities.AbsUser;

/**
 * Created by weicao on 2018/8/22.
 */

public class UserBean extends AbsUser{

    private String sessionId;
    private String username;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUserId() {
        return sessionId;
    }
}
