package cn.com.exz.hc.entity;

import java.util.List;

import cn.com.szw.lib.myframework.entities.AbsUser;

/**
 * Created by weicao on 2018/8/22.
 */

public class UserBean extends AbsUser{


    public UserBean(String sessionId, String username, List<String> functions) {
        this.sessionId = sessionId;
        this.username = username;
        this.functions = functions;
    }

    /**
     * functions : ["AQ","AQ001","AQ002","AQ003","AQ004","AQ005","AQ006","AQ007","AQ008","AQ009","LS","LS001","LS002","ZD","ZD001"]
     * sessionId : C5E8A15AD569CC3C0B4EAA8D6338436A
     * username : 管理员
     */

    private String sessionId;
    private String username;
    private List<String> functions;

    @Override
    public String getUserId() {
        return sessionId;
    }

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

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }
}
