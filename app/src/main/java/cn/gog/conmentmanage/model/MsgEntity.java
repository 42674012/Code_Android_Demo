package cn.gog.conmentmanage.model;

/**
 * 消息
 * Created by gujin on 2017/9/29.
 */

public class MsgEntity {

    /**
     * initiatorName : 测试人
     * type : 系统消息
     * label : 2018.04.23系统更新，为您带来不便，十分抱歉
     * createtime : 1524012480000
     * systemmsgid : 713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1
     * jumpUp : userinfo
     * articleid : 713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1
     * taskid : 713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1
     */

    private String initiatorName;
    private String type;
    private String label;
    private long createtime;
    private String systemmsgid;
    private String jumpUp;
    private String articleid;
    private String taskid;

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getSystemmsgid() {
        return systemmsgid;
    }

    public void setSystemmsgid(String systemmsgid) {
        this.systemmsgid = systemmsgid;
    }

    public String getJumpUp() {
        return jumpUp;
    }

    public void setJumpUp(String jumpUp) {
        this.jumpUp = jumpUp;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}
