package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/24.
 */

public class SystemInfoPage {

    /**
     * pageNum : 1
     * pageSize : 5
     * total : 2
     * pages : 1
     * list : [{"initiatorName":"测试人","type":"系统消息","label":"2018.04.23系统更新，为您带来不便，十分抱歉","createtime":1524012480000,"systemmsgid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1","jumpUp":"userinfo"},{"initiatorName":"系统管理员","type":"系统消息","label":"您修改了密码","createtime":1524012480000,"systemmsgid":"16","jumpUp":"userinfo"}]
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
         * createtime : 1529026361000
         * initiatorName : bx
         * jumpUp : userinfo
         * label : 您的个人信息已更新
         * msgid : ff263959-c0db-4640-9c3b-c60c334007d9
         * systemmsgid : ff263959-c0db-4640-9c3b-c60c334007d9
         * type : 系统消息
         */

        private long createtime;
        private String initiatorName;
        private String jumpUp;
        private String label;
        private String msgid;
        private String systemmsgid;
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

        public String getJumpUp() {
            return jumpUp;
        }

        public void setJumpUp(String jumpUp) {
            this.jumpUp = jumpUp;
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

        public String getSystemmsgid() {
            return systemmsgid;
        }

        public void setSystemmsgid(String systemmsgid) {
            this.systemmsgid = systemmsgid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
