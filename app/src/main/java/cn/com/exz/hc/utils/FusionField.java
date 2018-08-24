package cn.com.exz.hc.utils;

/**
 * Created by weicao on 2017/2/22.
 * 数据变量
 */

public class FusionField {



    /**
     * TOAKING
     */

    public final static String TAKING = "http://api.cn.ronghub.com/user/getToken.json";

    /**
     * 云端服务器地址
     */

    public final static String CLOUD_SERVER_URL = "http://192.168.3.186:8063/houcun/";





    /**
     * 登陆
     */

    public final static String Login = CLOUD_SERVER_URL+"mobile/mobileLogin/login.do";


    /**
     * 登出
     */

    public final static String LOGOUT = CLOUD_SERVER_URL+"mobile/mobileLogin/logout.do";


    /**
     * 单位，职位，审核人
     */

    public final static String syncData = CLOUD_SERVER_URL+"mobile/mobileSysDataController/syncData.do";

    /**
     * 清单录入
     */

    public final static String LIST_ENTER = CLOUD_SERVER_URL+"mobile/mobileResponsibilityListController/addOrUpdate.do";


    /**
     * 清单列表已上报，驳回
     */

    public final static String LIST_ALREADY_ENTER = CLOUD_SERVER_URL+"mobile/mobileResponsibilityListController/list.do";



    /**
     * 清单审核
     */

    public final static String LIST_CHECK = CLOUD_SERVER_URL+"mobile/mobileResponsibilityListController/doCheck.do";


    /**
     * 考核创建查询
     */

    public final static String ASSESS_CREATE_SEARCH = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamController/list.do";



    /**
     * 考核创建详情查询
     */

    public final static String ASSESS_CREATE_DETAIL_SEARCH = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamDetailController/list.do";



    /**
     * 责任考核列表
     */

    public final static String ResponsibilityAssess = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamDetailController/goExamDetailReplyList.do";


    /**
     * 责任考核考核提交
     */

    public final static String ResponsibilityAssess_Commit = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamDetailController/doAddScore.do";


    /**
     * 责任s审核列表
     */

    public final static String ResponsibilityCheck = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamDetailController/goExamDetailCheckList.do";

    /**
     * 责任审核提交
     */

    public final static String ResponsibilityCheck_Commit = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamDetailController/doExamDetailCheck.do";

    /**
     * 督办落实列表
     */

    public final static String OVERSEE_WORKABLE = CLOUD_SERVER_URL+"mobile/mobileSuperviseController/list.do";


    /**
     * 督办落实信息已督办列表
     */

    public final static String OVERSEE_WORKABLE_ALREADY_WORK = CLOUD_SERVER_URL+"mobile/mobileSuperviseController/handleList.do";


    /**
     * 督办落实信息录入
     */

    public final static String OVERSEE_WORKABLE_ENTER_ADD = CLOUD_SERVER_URL+"mobile/mobileSuperviseController/doAdd.do";



    /**
     * 督办受理列表
     */

    public final static String OVERSEE_ACCEPT_LIST = CLOUD_SERVER_URL+"mobile/mobileSuperviseController/processList.do";


    /**
     * 督办受理上传回复
     */

    public final static String OVERSEE_ACCEPT_UP_SEND = CLOUD_SERVER_URL+"mobile/mobileSuperviseController/doUpdate.do";




      /**
     * 临时任务派发temporary
     */

    public final static String TEMPORARY_SEND = CLOUD_SERVER_URL+"mobile/mobileTaskDispatchController/doAdd.do";


    /**
     * 临时任务已派发
     */

    public final static String TEMPORARY_ALREADY_SEND = CLOUD_SERVER_URL+"mobile/mobileTaskDispatchController/list.do";



    /**
     * 临时任务受理
     */

    public final static String TEMPORARY_DO = CLOUD_SERVER_URL+"mobile/mobileTaskDispatchController/doAccept.do";


    /**
     * 临时任务回复
     */

    public final static String TEMPORARY_REPLY = CLOUD_SERVER_URL+"mobile/mobileTaskDispatchController/doReply.do";


    /**
     * 重点任务受理
     */

    public final static String POINT_TEMPORARY_DO = CLOUD_SERVER_URL+"mobile/mobileMajorProjectController/acceptList.do";



    /**
     * 重点任务受理--受理
     */

    public final static String POINT_TEMPORARY_DO_shouli = CLOUD_SERVER_URL+"mobile/mobileMajorProjectController/doAccept.do";


    /**
     * 重点任务受理--回复
     */

    public final static String POINT_TEMPORARY_DO_REPLY = CLOUD_SERVER_URL+"mobile/mobileMajorProjectController/doReply.do";



    /**
     * 年度综合查询
     */

    public final static String YEAR_SEARCH = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamController/scoreSummaryList.do";



    /**
     * 计算
     */

    public final static String COUNT = CLOUD_SERVER_URL+"mobile/mobileResponsibilityExamController/calculateScore.do";





}