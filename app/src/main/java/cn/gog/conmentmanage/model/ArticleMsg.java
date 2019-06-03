package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/23.
 */

public class ArticleMsg {

    /**
     * pageNum : 1
     * pageSize : 5
     * total : 2
     * pages : 1
     * list : [{"initiatorName":"测试人","type":"文章列表消息","label":"测试人采纳了文章《我们是初生的太阳》","createtime":1524012360000,"articleid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"},{"initiatorName":"测试人","type":"文章列表消息","label":"测试人驳回了文章《阿西吧》","createtime":1524012360000,"articleid":"713f5bf3-c8d0-4cf2-8a5a-17b0baaddef1"}]
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
         * articleid : 33c0edbc-c44f-4f39-b0db-0afe86ccd4e8
         * createtime : 1532070592000
         * initiatorName : 郭靖
         * label : 郭靖上报了文章《呜呜呜》
         * msgid : 587a390a-844d-4bcc-9bfa-e3db33b3a84d
         * type : 文章列表消息
         */

        private String articleid;
        private long createtime;
        private String initiatorName;
        private String label;
        private String msgid;
        private String type;

        public boolean isChosen() {
            return isChosen;
        }

        public void setChosen(boolean chosen) {
            isChosen = chosen;
        }


        public String getArticleid() {
            return articleid;
        }

        public void setArticleid(String articleid) {
            this.articleid = articleid;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
