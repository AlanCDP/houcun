package cn.com.exz.hc.entity;

/**
 * Created by weicao on 2018/8/24.
 */

public class YearSearchEntity {


    /**
     * id : 4028830965131fb001651322cc5800c7
     * dutyUnit : {"id":"8a8ab0b246dc81120146dc8180ba0017","departname":"候村煤矿"}
     * dutyMan : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * post : {"id":"402885ac641fabac0164201abfef01c8","post_name":"矿长"}
     * reviewer : {"id":"402885ac641fabac01642010bbc2005e","realname":"张金贵"}
     * summary : 2.0
     * examResult : 失职
     * generateScheme : 系统生成
     * createDate : 2018-08-07 14:46:12
     */

    private String id;
    private DutyUnitBean dutyUnit;
    private DutyManBean dutyMan;
    private PostBean post;
    private ReviewerBean reviewer;
    private String summary;
    private String examResult;
    private String generateScheme;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DutyUnitBean getDutyUnit() {
        return dutyUnit;
    }

    public void setDutyUnit(DutyUnitBean dutyUnit) {
        this.dutyUnit = dutyUnit;
    }

    public DutyManBean getDutyMan() {
        return dutyMan;
    }

    public void setDutyMan(DutyManBean dutyMan) {
        this.dutyMan = dutyMan;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public ReviewerBean getReviewer() {
        return reviewer;
    }

    public void setReviewer(ReviewerBean reviewer) {
        this.reviewer = reviewer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public String getGenerateScheme() {
        return generateScheme;
    }

    public void setGenerateScheme(String generateScheme) {
        this.generateScheme = generateScheme;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public static class DutyManBean {
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
