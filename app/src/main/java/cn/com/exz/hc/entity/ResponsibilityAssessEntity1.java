package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/23.
 */

public class ResponsibilityAssessEntity1 {


    /**
     * detailId : 402885ac645135c501645902932f1652
     * id : 402885ac645135c501645902932f1652
     * scoreId : 402885ac6481f3370164865b02bf0028
     * examContent : 完成上一年度工作的述职述廉；
     * examCycle : year
     * limitDate : 2018-03-31
     * classification : 2
     * isExtraScore : N
     * isVetoScore : N
     * standardScore : 2.0
     * reviewer : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * comments : null
     * status : 3
     * completion : A
     * score : 2.0
     */

    private String detailId;
    private String id;
    private String scoreId;
    private String examContent;
    private String examCycle;
    private String limitDate;
    private String classification;
    private String isExtraScore;
    private String isVetoScore;
    private String standardScore;
    private ReviewerBean reviewer;
    private String comments;
    private String status;
    private String completion;
    private String score;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getExamContent() {
        return examContent;
    }

    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }

    public String getExamCycle() {
        return examCycle;
    }

    public void setExamCycle(String examCycle) {
        this.examCycle = examCycle;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getIsExtraScore() {
        return isExtraScore;
    }

    public void setIsExtraScore(String isExtraScore) {
        this.isExtraScore = isExtraScore;
    }

    public String getIsVetoScore() {
        return isVetoScore;
    }

    public void setIsVetoScore(String isVetoScore) {
        this.isVetoScore = isVetoScore;
    }

    public String getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(String standardScore) {
        this.standardScore = standardScore;
    }

    public ReviewerBean getReviewer() {
        return reviewer;
    }

    public void setReviewer(ReviewerBean reviewer) {
        this.reviewer = reviewer;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public static class ReviewerBean {
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
