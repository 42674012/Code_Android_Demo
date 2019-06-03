package common.vo;

import java.util.List;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/20 on 14:46
 * desc:分页数据模版
 */

/**
 * 分页数据模版
 * @param <T>
 */
public class Page<T> {

    private List<T> content;
    private int pageIndex;
    private int pageSize;
    private int total;

    public boolean isFirst(){
        if(pageIndex == 1){

            return  true;
        }
        return  false;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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
}
