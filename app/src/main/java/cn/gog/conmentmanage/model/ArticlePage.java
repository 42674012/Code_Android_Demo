package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/20.
 */

public class ArticlePage {

    /**
     * pageNum : 1
     * pageSize : 2
     * total : 14
     * pages : 7
     * list : [{"articleid":"5e309818-9d6b-478b-8b04-003c548ba774","article_title":"测试标题test14","article_url":"http://www.baidu.com/ddddddsasdasddddd","site_name":"耕云科技","description":"水字数","createtime":1521453855000},{"articleid":"f6444b8d-12ab-407a-af3a-17e6812ab279","article_title":"测试标题test13","article_url":"http://www.baidu.com/ddddddsasdasddddd","site_name":"耕云科技","description":"水字数","createtime":1521453851000}]
     * isFirstPage : true
     * isLastPage : false
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<WritingItemEntity> list;

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

    public List<WritingItemEntity> getList() {
        return list;
    }

    public void setList(List<WritingItemEntity> list) {
        this.list = list;
    }


}
