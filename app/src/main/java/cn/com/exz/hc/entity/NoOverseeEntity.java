package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/15.
 */

public class NoOverseeEntity {


    /**
     * suStatus : 1
     * id : 402885ac6481f3370164865b0187001e
     * exam_content : 无死亡，杜绝重大事故的发生；
     * limit_date : 2018-12-31
     * classification : 1
     * is_Extra_Score : N
     * is_Veto_Score : Y
     * standard_Score : 1.0
     * reviewer : 402885ac641fabac01642010bbc2005e
     * comments : null
     * STATUS : 1
     * COMPLETION : null
     * inspected_person : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * post : {"id":"402885ac641fabac0164201abfef01c8","post_name":"矿长"}
     * duty_unit : {"id":"8a8ab0b246dc81120146dc8180ba0017","departname":"候村煤矿"}
     * score : 0.0
     */

    private int suStatus;
    private String id;
    private String exam_content;
    private String limit_date;
    private String classification;
    private String is_Extra_Score;
    private String is_Veto_Score;
    private String standard_Score;
    private String reviewer;
    private String comments;
    private String STATUS;
    private String COMPLETION;
    private InspectedPersonBean inspected_person;
    private PostBean post;
    private DutyUnitBean duty_unit;
    private String score;

    public int getSuStatus() {
        return suStatus;
    }

    public void setSuStatus(int suStatus) {
        this.suStatus = suStatus;
    }

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

    public Object getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public Object getCOMPLETION() {
        return COMPLETION;
    }

    public void setCOMPLETION(String COMPLETION) {
        this.COMPLETION = COMPLETION;
    }

    public InspectedPersonBean getInspected_person() {
        return inspected_person;
    }

    public void setInspected_person(InspectedPersonBean inspected_person) {
        this.inspected_person = inspected_person;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public DutyUnitBean getDuty_unit() {
        return duty_unit;
    }

    public void setDuty_unit(DutyUnitBean duty_unit) {
        this.duty_unit = duty_unit;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static class InspectedPersonBean {
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
}
