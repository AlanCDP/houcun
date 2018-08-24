package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/21.
 */

public class NoOverseeAcceptEntity {

    /**
     * id : 402885ac64cbef030164f3080b675157
     * exam_content : 每月参加工伤抢救并上报相关资料。
     * limit_date : 2018-07-31 14:35:09
     * classification : 2
     * is_Extra_Score : N
     * is_Veto_Score : N
     * standard_Score : 8.0
     * reviewer : 402885ac641fabac01642010bcad0061
     * scoreStatus : 3
     * inspected_person : 402885ac641fabac01642010c89400c4
     * post : 402885ac641fabac0164201ac3a80204
     * duty_unit : 402885ac641fabac01641fc69951001e
     * score : 6.4
     * supervise_unit : 8a8ab0b246dc81120146dc8180ba0017
     * supervise_man : {"id":"402885ac641fabac01642010bcad0061","realname":"张必同"}
     * supervise_remark : 严格在规定时间内按要求完成！
     * level : 2
     * comments :
     * reply : 已完成
     * status : 1
     */

    private String id;
    private String exam_content;
    private String limit_date;
    private String classification;
    private String is_Extra_Score;
    private String is_Veto_Score;
    private String standard_Score;
    private String reviewer;
    private String scoreStatus;
    private String inspected_person;
    private String post;
    private String duty_unit;
    private String score;
    private String supervise_unit;
    private SuperviseManBean supervise_man;
    private String supervise_remark;
    private String level;
    private String comments;
    private String reply;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExam_content() {
        return exam_content;
    }

    public void setExam_content(String exam_content) {
        this.exam_content = exam_content;
    }

    public String getLimit_date() {
        return limit_date;
    }

    public void setLimit_date(String limit_date) {
        this.limit_date = limit_date;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getIs_Extra_Score() {
        return is_Extra_Score;
    }

    public void setIs_Extra_Score(String is_Extra_Score) {
        this.is_Extra_Score = is_Extra_Score;
    }

    public String getIs_Veto_Score() {
        return is_Veto_Score;
    }

    public void setIs_Veto_Score(String is_Veto_Score) {
        this.is_Veto_Score = is_Veto_Score;
    }

    public String getStandard_Score() {
        return standard_Score;
    }

    public void setStandard_Score(String standard_Score) {
        this.standard_Score = standard_Score;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(String scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public String getInspected_person() {
        return inspected_person;
    }

    public void setInspected_person(String inspected_person) {
        this.inspected_person = inspected_person;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDuty_unit() {
        return duty_unit;
    }

    public void setDuty_unit(String duty_unit) {
        this.duty_unit = duty_unit;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSupervise_unit() {
        return supervise_unit;
    }

    public void setSupervise_unit(String supervise_unit) {
        this.supervise_unit = supervise_unit;
    }

    public SuperviseManBean getSupervise_man() {
        return supervise_man;
    }

    public void setSupervise_man(SuperviseManBean supervise_man) {
        this.supervise_man = supervise_man;
    }

    public String getSupervise_remark() {
        return supervise_remark;
    }

    public void setSupervise_remark(String supervise_remark) {
        this.supervise_remark = supervise_remark;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class SuperviseManBean {
        /**
         * id : 402885ac641fabac01642010bcad0061
         * realname : 张必同
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
