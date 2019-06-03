package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/23.
 */

public class TaskPage {

    /**
     * pageNum : 1
     * pageSize : 5
     * total : 2
     * pages : 1
     * list : [{"initiatorName":"测试人","type":"任务通知","label":"测试人发布了任务《为雄安点赞》","createtime":1524012240000,"taskid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"},{"initiatorName":"测试人","type":"任务通知","label":"测试人取消了任务《为雄安点赞》","createtime":1524012240000,"taskid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"}]
     * isFirstPage : true
     * isLastPage : true
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<ListBean> list;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {


        private boolean isChosen  =false;
        /**
         * createtime : 1530687984000
         * initiatorName : 系统管理员
         * label : 系统管理员取消任务:奥术大师大所大
         * msgid : 3f62ce2f-54bf-4cc5-bc5e-9322318f53b0
         * taskid : 97e7ff3d-59b4-4daf-99a6-c620b199bc15
         * type : 任务通知
         */

        private long createtime;
        private String initiatorName;
        private String label;
        private String msgid;
        private String taskid;
        private String type;

        public boolean isChosen() {
            return isChosen;
        }

        public void setChosen(boolean chosen) {
            isChosen = chosen;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getInitiatorName() {
            return initiatorName;
        }

        public void setInitiatorName(String initiatorName) {
            this.initiatorName = initiatorName;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getMsgid() {
            return msgid;
        }

        public void setMsgid(String msgid) {
            this.msgid = msgid;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
