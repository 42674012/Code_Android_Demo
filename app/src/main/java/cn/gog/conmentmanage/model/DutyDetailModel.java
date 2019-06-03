package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/19.
 */

public class DutyDetailModel {


    /**
     * countResponseOrg : 0
     * countResponseOrgLooked : 0
     * creater_manager_userid : 123456789
     * creater_manager_username : 系统管理员
     * creater_orgid : 11
     * createtime : 1524371372000
     * deadline : 1524412800000
     * deleteflag : false
     * feedback_type : 0
     * feedback_typeStr : 限时反馈
     * feedback_type_deadline : 1524412800000
     * number : 76
     * responseOrgList : [{"id":"d39b86dd-8b3d-4a91-9f13-6934656566a6","label":"遵义市网信办"},{"id":"d7cd43bb-b7a7-43d1-aebb-7313058baa20","label":"黔东南网信办"}]
     * taskDetailsVoList : [{"article_title":"ff","createtime":1524371372000,"deleteflag":false,"description":"ffffffff","detailsid":"5f310f01-cd98-4df8-bdcd-b586ffeee292","site_name":"ff","site_url":"fffffffffff","task_sum":55,"taskid":"24c80862-ba3e-4bc3-8ca6-b51d989739a6","type_name":"不限","typeid":"1"}]
     * task_code : 201804220076
     * task_importance_degree : 0
     * task_importance_degreeStr : 一般
     * task_long_term : false
     * task_name : ffffff
     * task_state : 0
     * task_stateStr : 未发布
     * task_urgency_degree : 0
     * task_urgency_degreeStr : 一般
     * taskid : 24c80862-ba3e-4bc3-8ca6-b51d989739a6
     */

    private int feedback_cycle;

    public int getFeedback_cycle() {
        return feedback_cycle;
    }

    public void setFeedback_cycle(int feedback_cycle) {
        this.feedback_cycle = feedback_cycle;
    }

    private int countResponseOrg;
    private int countResponseOrgLooked;
    private String creater_manager_userid;
    private String creater_manager_username;
    private String creater_orgid;
    private long createtime;
    private long deadline;
    private boolean deleteflag;
    private int feedback_type;
    private String feedback_typeStr;
    private long feedback_type_deadline;
    private int number;
    private String task_code;
    private int task_importance_degree;
    private String task_importance_degreeStr;
    private boolean task_long_term;
    private String task_name;
    private int task_state;
    private String task_stateStr;
    private int task_urgency_degree;
    private String task_urgency_degreeStr;
    private String taskid;
    private List<ResponseOrgListBean> responseOrgList;
    private List<TaskDetailsVoListBean> taskDetailsVoList;

    public int getCountResponseOrg() {
        return countResponseOrg;
    }

    public void setCountResponseOrg(int countResponseOrg) {
        this.countResponseOrg = countResponseOrg;
    }

    public int getCountResponseOrgLooked() {
        return countResponseOrgLooked;
    }

    public void setCountResponseOrgLooked(int countResponseOrgLooked) {
        this.countResponseOrgLooked = countResponseOrgLooked;
    }

    public String getCreater_manager_userid() {
        return creater_manager_userid;
    }

    public void setCreater_manager_userid(String creater_manager_userid) {
        this.creater_manager_userid = creater_manager_userid;
    }

    public String getCreater_manager_username() {
        return creater_manager_username;
    }

    public void setCreater_manager_username(String creater_manager_username) {
        this.creater_manager_username = creater_manager_username;
    }

    public String getCreater_orgid() {
        return creater_orgid;
    }

    public void setCreater_orgid(String creater_orgid) {
        this.creater_orgid = creater_orgid;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public boolean isDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    public int getFeedback_type() {
        return feedback_type;
    }

    public void setFeedback_type(int feedback_type) {
        this.feedback_type = feedback_type;
    }

    public String getFeedback_typeStr() {
        return feedback_typeStr;
    }

    public void setFeedback_typeStr(String feedback_typeStr) {
        this.feedback_typeStr = feedback_typeStr;
    }

    public long getFeedback_type_deadline() {
        return feedback_type_deadline;
    }

    public void setFeedback_type_deadline(long feedback_type_deadline) {
        this.feedback_type_deadline = feedback_type_deadline;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTask_code() {
        return task_code;
    }

    public void setTask_code(String task_code) {
        this.task_code = task_code;
    }

    public int getTask_importance_degree() {
        return task_importance_degree;
    }

    public void setTask_importance_degree(int task_importance_degree) {
        this.task_importance_degree = task_importance_degree;
    }

    public String getTask_importance_degreeStr() {
        return task_importance_degreeStr;
    }

    public void setTask_importance_degreeStr(String task_importance_degreeStr) {
        this.task_importance_degreeStr = task_importance_degreeStr;
    }

    public boolean isTask_long_term() {
        return task_long_term;
    }

    public void setTask_long_term(boolean task_long_term) {
        this.task_long_term = task_long_term;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getTask_state() {
        return task_state;
    }

    public void setTask_state(int task_state) {
        this.task_state = task_state;
    }

    public String getTask_stateStr() {
        return task_stateStr;
    }

    public void setTask_stateStr(String task_stateStr) {
        this.task_stateStr = task_stateStr;
    }

    public int getTask_urgency_degree() {
        return task_urgency_degree;
    }

    public void setTask_urgency_degree(int task_urgency_degree) {
        this.task_urgency_degree = task_urgency_degree;
    }

    public String getTask_urgency_degreeStr() {
        return task_urgency_degreeStr;
    }

    public void setTask_urgency_degreeStr(String task_urgency_degreeStr) {
        this.task_urgency_degreeStr = task_urgency_degreeStr;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public List<ResponseOrgListBean> getResponseOrgList() {
        return responseOrgList;
    }

    public void setResponseOrgList(List<ResponseOrgListBean> responseOrgList) {
        this.responseOrgList = responseOrgList;
    }

    public List<TaskDetailsVoListBean> getTaskDetailsVoList() {
        return taskDetailsVoList;
    }

    public void setTaskDetailsVoList(List<TaskDetailsVoListBean> taskDetailsVoList) {
        this.taskDetailsVoList = taskDetailsVoList;
    }

    public static class ResponseOrgListBean {
        /**
         * id : d39b86dd-8b3d-4a91-9f13-6934656566a6
         * label : 遵义市网信办
         */

        private String id;
        private String label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class TaskDetailsVoListBean {


        private String article_title;
        private long createtime;
        private boolean deleteflag;
        private String description;
        private String detailsid;
        private String site_name;
        private String site_url;
        private int task_sum;
        private String taskid;
        private String type_name;
        private String typeid;

        public String getArticle_title() {
            return article_title;
        }

        public void setArticle_title(String article_title) {
            this.article_title = article_title;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public boolean isDeleteflag() {
            return deleteflag;
        }

        public void setDeleteflag(boolean deleteflag) {
            this.deleteflag = deleteflag;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetailsid() {
            return detailsid;
        }

        public void setDetailsid(String detailsid) {
            this.detailsid = detailsid;
        }

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getSite_url() {
            return site_url;
        }

        public void setSite_url(String site_url) {
            this.site_url = site_url;
        }

        public int getTask_sum() {
            return task_sum;
        }

        public void setTask_sum(int task_sum) {
            this.task_sum = task_sum;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }
    }
}
