package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/21.
 */

public class FocusDistributingEntity {


    /**
     * id : 4028833a6569ebc501656a91fa180018
     * projectName : 哈哈哈哈
     * distribution : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * dispatchDate : 2018-08-24 14:14:56
     * planStartDate : 2018-08-24
     * planEndDate : 2018-08-25
     * step : 1测试 2.查看
     * acceptDepart : {"id":"8a8ab0b246dc81120146dc8180ba0017","departname":"候村煤矿"}
     * acceptMan : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * acceptDate : null
     * actualStartDate : null
     * actualEndDate : null
     * comments : null
     * stepStatus : 1
     * completeStatus : 0
     */

    private String id;
    private String projectName;
    private DistributionBean distribution;
    private String dispatchDate;
    private String planStartDate;
    private String planEndDate;
    private String step;
    private AcceptDepartBean acceptDepart;
    private AcceptManBean acceptMan;
    private String acceptDate;
    private String actualStartDate;
    private String actualEndDate;
    private String comments;
    private String stepStatus;
    private String completeStatus;
    private String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public DistributionBean getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionBean distribution) {
        this.distribution = distribution;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public AcceptDepartBean getAcceptDepart() {
        return acceptDepart;
    }

    public void setAcceptDepart(AcceptDepartBean acceptDepart) {
        this.acceptDepart = acceptDepart;
    }

    public AcceptManBean getAcceptMan() {
        return acceptMan;
    }

    public void setAcceptMan(AcceptManBean acceptMan) {
        this.acceptMan = acceptMan;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStepStatus() {
        return stepStatus;
    }

    public void setStepStatus(String stepStatus) {
        this.stepStatus = stepStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public static class DistributionBean {
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

    public static class AcceptDepartBean {
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

    public static class AcceptManBean {
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
