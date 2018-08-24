package cn.com.exz.hc.entity;

import java.io.Serializable;

/**
 * Created by weicao on 2018/8/18.
 */

public class ListEnterNoPassEntity implements Serializable {


    /**
     * id : 4028833a65643a340165645a5dbf0001
     * duty_unit : {"id":"402885ac641fabac01641fc26ca90006","departname":"安标办"}
     * post : {"id":"402885ac641fabac0164201ac03d01cc","post_name":"生产矿长"}
     * exam_cycle : 季
     * classification : 任务
     * exam_content : 哈哈哈
     * exam_date : null
     * is_extra_score : Y
     * is_veto_score : Y
     * standard_score : 10.0
     * review_date : null
     * reviewer : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * status : 2
     * review_result : null
     * review_remark : null
     * is_delete : N
     * create_date : 2018-08-23 09:16:09
     * create_by : admin
     * create_name : 管理员
     * update_date : null
     * update_by : null
     * update_name : null
     */

    private String id;
    private DutyUnitBean duty_unit;
    private PostBean post;
    private String exam_cycle;
    private String classification;
    private String exam_content;
    private String exam_date;
    private String is_extra_score;
    private String is_veto_score;
    private String standard_score;
    private Object review_date;
    private ReviewerBean reviewer;
    private String status;
    private Object review_result;
    private String review_remark;
    private String is_delete;
    private String create_date;
    private String create_by;
    private String create_name;
    private Object update_date;
    private Object update_by;
    private Object update_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String  getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
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

    public String getStandard_score() {
        return standard_score;
    }

    public void setStandard_score(String standard_score) {
        this.standard_score = standard_score;
    }

    public Object getReview_date() {
        return review_date;
    }

    public void setReview_date(Object review_date) {
        this.review_date = review_date;
    }

    public ReviewerBean getReviewer() {
        return reviewer;
    }

    public void setReviewer(ReviewerBean reviewer) {
        this.reviewer = reviewer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getReview_result() {
        return review_result;
    }

    public void setReview_result(Object review_result) {
        this.review_result = review_result;
    }

    public String  getReview_remark() {
        return review_remark;
    }

    public void setReview_remark(String review_remark) {
        this.review_remark = review_remark;
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

    public void setUpdate_date(Object update_date) {
        this.update_date = update_date;
    }

    public Object getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Object update_by) {
        this.update_by = update_by;
    }

    public Object getUpdate_name() {
        return update_name;
    }

    public void setUpdate_name(Object update_name) {
        this.update_name = update_name;
    }

    public static class DutyUnitBean implements Serializable{
        /**
         * id : 402885ac641fabac01641fc26ca90006
         * departname : 安标办
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

    public static class PostBean implements Serializable{
        /**
         * id : 402885ac641fabac0164201ac03d01cc
         * post_name : 生产矿长
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

    public static class ReviewerBean implements Serializable{
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
}
