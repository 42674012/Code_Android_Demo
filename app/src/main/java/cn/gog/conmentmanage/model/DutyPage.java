package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/19.
 */

public class DutyPage {


    /**
     * pageNum : 1
     * pageSize : 2
     * total : 14
     * pages : 7
     * list : [{"taskid":"5e309818-9d6b-478b-8b04-003c548ba787","task_name":"任务名称任务名称","creater_manager_username":"创建人名字","createtime":1521453855000,"task_code":"201803190001"},{"taskid":"5e309818-9d6b-478b-8b04-003c548ba788","task_name":"任务名称任务名称","creater_manager_username":"创建人名字","createtime":1521463855000,"task_code":"201803190002"}]
     * isFirstPage : true
     * isLastPage : false
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<DutyItemEntity> list;

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

    public List<DutyItemEntity> getList() {
        return list;
    }

    public void setList(List<DutyItemEntity> list) {
        this.list = list;
    }

}
