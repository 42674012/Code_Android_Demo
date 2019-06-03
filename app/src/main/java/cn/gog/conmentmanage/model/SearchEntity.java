package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/24.
 */

public class SearchEntity {

    /**
     * type : 任务通知
     * hasMore : false
     * msg : [{"initiatorName":"测试人","type":"任务通知","label":"测试人发布了任务《为雄安点赞》","createtime":1524012240000,"taskid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"},{"initiatorName":"测试人","type":"任务通知","label":"测试人取消了任务《为雄安点赞》","createtime":1524012240000,"taskid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"}]
     */

    private String type;
    private boolean hasMore;
    private List<MsgBean> msg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * initiatorName : 测试人
         * type : 任务通知
         * label : 测试人发布了任务《为雄安点赞》
         * createtime : 1524012240000
         * taskid : 713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1
         * articleid
         */

        private String initiatorName;
        private String type;
        private String label;
        private long createtime;
        private String taskid;
        private String articleid;
        private String systemmsgid;

        public String getArticleid() {
            return articleid;
        }

        public void setArticleid(String articleid) {
            this.articleid = articleid;
        }

        public String getSystemmsgid() {
            return systemmsgid;
        }

        public void setSystemmsgid(String systemmsgid) {
            this.systemmsgid = systemmsgid;
        }

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

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }
    }
}
