package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/21.
 */

public class AssessCreateEntity {


    /**
     * id : 402885ac645135c50164590271cb1645
     * unit : {"id":"8a8ab0b246dc81120146dc8180ba0017","departname":"候村煤矿"}
     * duty : {"id":"402885ac641fabac0164201abfef01c8","post_name":"矿长"}
     * score : null
     * reviewer : 402885ac641fabac01642010bbc2005e
     * is_report : 1
     * create_date : 2018-07-02 11:21:30
     * create_by : admin1
     * create_name : 管理员1
     * update_date : 2018-07-11 06:41:08
     * update_by : admin
     * update_name : 管理员
     * countNum : 80
     */

    private String id;
    private UnitBean unit;
    private DutyBean duty;
    private String score;
    private String reviewer;
    private String is_report;
    private String create_date;
    private String create_by;
    private String create_name;
    private String update_date;
    private String update_by;
    private String update_name;
    private int countNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UnitBean getUnit() {
        return unit;
    }

    public void setUnit(UnitBean unit) {
        this.unit = unit;
    }

    public DutyBean getDuty() {
        return duty;
    }

    public void setDuty(DutyBean duty) {
        this.duty = duty;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getIs_report() {
        return is_report;
    }

    public void setIs_report(String is_report) {
        this.is_report = is_report;
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

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public static class UnitBean {
        /**
         * id : 8a8ab0b246dc81120146dc8180ba0017
         * departname : 候村煤矿
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

    public static class DutyBean {
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
}
