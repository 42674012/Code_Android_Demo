package cn.gog.conmentmanage.model;

/**
 * Created by lenovo on 2018/4/18.
 */

public class DutyItemEntity {

    /**
     * countResponseOrg : 0
     * countResponseOrgLooked : 0
     * creater_manager_userid : 123456789
     * creater_manager_username : 系统管理员
     * creater_orgid : 11
     * createtime : 1524468155000
     * deadline : 1524554520000
     * deleteflag : false
     * feedback_cycle : 0
     * feedback_type : 0
     * feedback_type_deadline : 1524554520000
     * number : 88
     * publishtime : 1524468162000
     * task_code : 201804230088
     * task_importance_degree : 0
     * task_long_term : false
     * task_name : sf
     * task_state : 1
     * task_urgency_degree : 0
     * taskid : ef8bd270-1fee-4769-810b-0563567cb0c6
     */

    private int countResponseOrg;
    private int countResponseOrgLooked;
    private String creater_manager_userid;
    private String creater_manager_username;
    private String creater_orgid;
    private long createtime;
    private long deadline;
    private boolean deleteflag;
    private int feedback_cycle;
    private int feedback_type;
    private long feedback_type_deadline;
    private int number;
    private long publishtime;
    private String task_code;
    private int task_importance_degree;
    private boolean task_long_term;
    private String task_name;
    private int task_state;
    private int task_urgency_degree;
    private String taskid;

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

    public int getFeedback_cycle() {
        return feedback_cycle;
    }

    public void setFeedback_cycle(int feedback_cycle) {
        this.feedback_cycle = feedback_cycle;
    }

    public int getFeedback_type() {
        return feedback_type;
    }

    public void setFeedback_type(int feedback_type) {
        this.feedback_type = feedback_type;
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

    public long getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(long publishtime) {
        this.publishtime = publishtime;
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

    public int getTask_urgency_degree() {
        return task_urgency_degree;
    }

    public void setTask_urgency_degree(int task_urgency_degree) {
        this.task_urgency_degree = task_urgency_degree;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}
