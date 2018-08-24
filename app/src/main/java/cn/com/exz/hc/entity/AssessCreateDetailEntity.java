package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/21.
 */

public class AssessCreateDetailEntity {


    /**
     * id : 402885ac645135c501645902932f1648
     * limit_date : 2018-12-31
     * resp_id : 402885ac645135c5016458f46a6900f7
     * resp_exam_id : 402885ac645135c50164590271cb1645
     * duty_unit : {"id":"8a8ab0b246dc81120146dc8180ba0017","departname":"候村煤矿"}
     * post : {"id":"402885ac641fabac0164201abfef01c8","post_name":"矿长"}
     * exam_cycle : year
     * classification : 1
     * exam_content : 无死亡，杜绝重大事故的发生；
     * is_extra_score : N
     * is_veto_score : Y
     * completion : null
     * standard_score : 0.0
     * exam_score : null
     * review_date : 2018-07-02 11:06:10
     * reviewer : 402885ac641fabac01642010bbc2005e
     * remark : null
     * status : 0
     * is_report : null
     * is_delete : N
     * create_date : 2018-07-02 11:06:10
     * create_by : admin
     * create_name : 管理员
     * update_date : null
     * update_by : null
     * update_name : null
     */

    private String id;
    private String limit_date;
    private String resp_id;
    private String resp_exam_id;
    private DutyUnitBean duty_unit;
    private PostBean post;
    private String exam_cycle;
    private String classification;
    private String exam_content;
    private String is_extra_score;
    private String is_veto_score;
    private String completion;
    private String standard_score;
    private String exam_score;
    private String review_date;
    private String reviewer;
    private String remark;
    private String status;
    private String is_report;
    private String is_delete;
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

    public String getLimit_date() {
        return limit_date;
    }

    public void setLimit_date(String limit_date) {
        this.limit_date = limit_date;
    }

    public String getResp_id() {
        return resp_id;
    }

    public void setResp_id(String resp_id) {
        this.resp_id = resp_id;
    }

    public String getResp_exam_id() {
        return resp_exam_id;
    }

    public void setResp_exam_id(String resp_exam_id) {
        this.resp_exam_id = resp_exam_id;
    }

    public DutyUnitBean getDuty_unit() {
        return duty_unit;
    }

    public void setDuty_unit(DutyUnitBean duty_unit) {
        this.duty_unit = duty_unit;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public String getExam_cycle() {
        return exam_cycle;
    }

    public void setExam_cycle(String exam_cycle) {
        this.exam_cycle = exam_cycle;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getExam_content() {
        return exam_content;
    }

    public void setExam_content(String exam_content) {
        this.exam_content = exam_content;
    }

    public String getIs_extra_score() {
        return is_extra_score;
    }

    public void setIs_extra_score(String is_extra_score) {
        this.is_extra_score = is_extra_score;
    }

    public String getIs_veto_score() {
        return is_veto_score;
    }

    public void setIs_veto_score(String is_veto_score) {
        this.is_veto_score = is_veto_score;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getStandard_score() {
        return standard_score;
    }

    public void setStandard_score(String standard_score) {
        this.standard_score = standard_score;
    }

    public Object getExam_score() {
        return exam_score;
    }

    public void setExam_score(String exam_score) {
        this.exam_score = exam_score;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getIs_report() {
        return is_report;
    }

    public void setIs_report(String is_report) {
        this.is_report = is_report;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public Object getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public Object getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Object getUpdate_name() {
        return update_name;
    }

    public void setUpdate_name(String update_name) {
        this.update_name = update_name;
    }

    public static class DutyUnitBean {
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

    public static class PostBean {
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
