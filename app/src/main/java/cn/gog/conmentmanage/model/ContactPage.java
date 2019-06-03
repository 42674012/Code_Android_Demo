package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/20.
 */

public class ContactPage {

    /**
     * pageNum : 1
     * pageSize : 2
     * total : 14
     * pages : 7
     * list : [{"userid":"123456789","orgid":"0","headUrl":"http://bazhuawangluo.oss-cn-hangzhou.aliyuncs.com/teamwork/user/c1a93f30-d456-4c73-b4c7-7be9723b8b7b.jpg","username":"程运贵","login_name":"gui","user_state":true,"phone":"","description":"开发人员","email":"18285119301@139.com","createtime":1520225890000},{"userid":"1234567892","orgid":"0","headUrl":"http://bazhuawangluo.oss-cn-hangzhou.aliyuncs.com/teamwork/user/c1a93f30-d456-4c73-b4c7-7be9723b8b7b.jpg","username":"程运贵2","login_name":"gui2","user_state":true,"phone":"","description":"开发人员","email":"18285119302@139.com","createtime":1520225890000}]
     * isFirstPage : true
     * isLastPage : false
     */

    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean isFirstPage;
    private boolean isLastPage;
    private List<UserInfoBean> list;

    public List<UserInfoBean> getList() {
        return list;
    }

    public void setList(List<UserInfoBean> list) {
        this.list = list;
    }

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



   public class UserInfoBean {


       /**
        * createtime : 1525679701000
        * deleteflag : false
        * description :
        * email :
        * headUrl : http://bazhuawangluo.img-cn-hangzhou.aliyuncs.com/cac_commenters_4j/images/c28562de-9f67-414c-ba0c-415cc8160599.png
        * login_name : lisi
        * org_name : APP测试部门
        * orgid : b7a151c9-7b97-4a64-8e4c-8a091a793e06
        * password : 93cf256c2d62d4c85960f9d7554a9a4c
        * phone :
        * user_state : true
        * userid : 632af709-b871-47cb-8bdd-4fc5140b8b31
        * username : 李斯
        */

       private long createtime;
       private boolean deleteflag;
       private String description;
       private String email;
       private String headUrl;
       private String login_name;
       private String org_name;
       private String orgid;
       private String password;
       private String phone;
       private boolean user_state;
       private String userid;
       private String username;

       public long getCreatetime() {
           return createtime;
       }

       public void setCreatetime(long createtime) {
           this.createtime = createtime;
       }

       public boolean isDeleteflag() {
           return deleteflag;
       }

       public void setDeleteflag(boolean deleteflag) {
           this.deleteflag = deleteflag;
       }

       public String getDescription() {
           return description;
       }

       public void setDescription(String description) {
           this.description = description;
       }

       public String getEmail() {
           return email;
       }

       public void setEmail(String email) {
           this.email = email;
       }

       public String getHeadUrl() {
           return headUrl;
       }

       public void setHeadUrl(String headUrl) {
           this.headUrl = headUrl;
       }

       public String getLogin_name() {
           return login_name;
       }

       public void setLogin_name(String login_name) {
           this.login_name = login_name;
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

       public String getPassword() {
           return password;
       }

       public void setPassword(String password) {
           this.password = password;
       }

       public String getPhone() {
           return phone;
       }

       public void setPhone(String phone) {
           this.phone = phone;
       }

       public boolean isUser_state() {
           return user_state;
       }

       public void setUser_state(boolean user_state) {
           this.user_state = user_state;
       }

       public String getUserid() {
           return userid;
       }

       public void setUserid(String userid) {
           this.userid = userid;
       }

       public String getUsername() {
           return username;
       }

       public void setUsername(String username) {
           this.username = username;
       }
   }
}