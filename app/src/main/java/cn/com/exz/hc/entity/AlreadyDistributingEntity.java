package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/21.
 */

public class AlreadyDistributingEntity {


    /**
     * id : 4028833a6569c651016569c98ca20001
     * task_desc : asdasd 
     * distribution : 8a8ab0b246dc81120146dc8181950052
     * dispatch_date : 2018-08-24 10:35:41
     * plan_start_date : 2018-08-22
     * plan_end_date : 2018-08-21
     * accept_depart : {"id":"402885ac641fabac01641fc1fbbc0004","departname":"安全科"}
     * accept_man : {"id":"402885ac641fabac01642010c2980094","realname":"郭育东"}
     * accept_date : null
     * task_status : 1
     * actual_start_date : null
     * actual_end_date : null
     * complete_status : 1
     * comments : null
     * create_date : 2018-08-24 10:35:41
     * create_by : admin
     * create_name : 管理员
     * update_date : null
     * update_by : null
     * update_name : null
     */

    private String id;
    private String task_desc;
    private String distribution;
    private String dispatch_date;
    private String plan_start_date;
    private String plan_end_date;
    private AcceptDepartBean accept_depart;
    private AcceptManBean accept_man;
    private String accept_date;
    private String task_status;
    private String actual_start_date;
    private String actual_end_date;
    private String complete_status;
    private String comments;
    private String create_date;
    private String create_by;
    private String create_name;
    private String update_date;
    private String update_by;
    private String update_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getDispatch_date() {
        return dispatch_date;
    }

    public void setDispatch_date(String dispatch_date) {
        this.dispatch_date = dispatch_date;
    }

    public String getPlan_start_date() {
        return plan_start_date;
    }

    public void setPlan_start_date(String plan_start_date) {
        this.plan_start_date = plan_start_date;
    }

    public String getPlan_end_date() {
        return plan_end_date;
    }

    public void setPlan_end_date(String plan_end_date) {
        this.plan_end_date = plan_end_date;
    }

    public AcceptDepartBean getAccept_depart() {
        return accept_depart;
    }

    public void setAccept_depart(AcceptDepartBean accept_depart) {
        this.accept_depart = accept_depart;
    }

    public AcceptManBean getAccept_man() {
        return accept_man;
    }

    public void setAccept_man(AcceptManBean accept_man) {
        this.accept_man = accept_man;
    }

    public String getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(String accept_date) {
        this.accept_date = accept_date;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getActual_start_date() {
        return actual_start_date;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(String actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public String getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_name() {
        return update_name;
    }

    public void setUpdate_name(String update_name) {
        this.update_name = update_name;
    }

    public static class AcceptDepartBean {
        /**
         * id : 402885ac641fabac01641fc1fbbc0004
         * departname : 安全科
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

    public static class AcceptManBean {
        /**
         * id : 402885ac641fabac01642010c2980094
         * realname : 郭育东
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
}
