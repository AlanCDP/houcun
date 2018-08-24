package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/18.
 */

public class ResponsibilityAssessEntity {

    private String id;
    private String scoreId;
    private String examContent;
    private String limitDate;
    private String examCycle;
    private String completion;


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

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getExamCycle() {
        return examCycle;
    }

    public void setExamCycle(String examCycle) {
        this.examCycle = examCycle;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }
}
