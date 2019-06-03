package cn.gog.conmentmanage.model;

/**
 * Created by lenovo on 2018/4/18.
 */

public class WritingItemEntity {

    /**
     * article_title : 耕云
     * article_url : www.gy.com
     * articleid : c7042e8b-301e-46b8-b6d3-207fb56483c6
     * createtime : 1524120358000
     * description :
     * orgid : 20fcc5fe-bedc-4761-a17a-31bb0ea96863
     * response_stateStr : 采纳
     * similarityArticleSize : 0
     * site_name : 耕云
     */

    private String article_title;
    private String article_url;
    private String articleid;
    private long createtime;
    private String description;
    private String orgid;
    private String response_stateStr;
    private int similarityArticleSize;
    private String site_name;

    private boolean isChosen =false;

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getResponse_stateStr() {
        return response_stateStr;
    }

    public void setResponse_stateStr(String response_stateStr) {
        this.response_stateStr = response_stateStr;
    }

    public int getSimilarityArticleSize() {
        return similarityArticleSize;
    }

    public void setSimilarityArticleSize(int similarityArticleSize) {
        this.similarityArticleSize = similarityArticleSize;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }
}
