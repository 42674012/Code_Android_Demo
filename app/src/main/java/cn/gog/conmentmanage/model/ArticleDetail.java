package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/20.
 */

public class ArticleDetail {


    /**
     * article_title : AAA
     * article_url : WWW.aaa.com
     * articleid : b359176e-afeb-4d93-9358-d53f97dd1976
     * createtime : 1525427002000
     * description : 11
     * org_name : APP测试部门
     * orgid : b7a151c9-7b97-4a64-8e4c-8a091a793e06
     * response_state : 0
     * response_stateStr : 待审核
     * similarityArticleSize : 0
     * similarityArticles : []
     * site_name : www.aaa.com
     */

    private String article_title;
    private String article_url;
    private String articleid;
    private long createtime;
    private String description;
    private String org_name;
    private String orgid;
    private int response_state;
    private String response_stateStr;
    private int similarityArticleSize;
    private String site_name;


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

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public int getResponse_state() {
        return response_state;
    }

    public void setResponse_state(int response_state) {
        this.response_state = response_state;
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
