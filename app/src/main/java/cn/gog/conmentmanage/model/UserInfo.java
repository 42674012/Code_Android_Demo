package cn.gog.conmentmanage.model;

/**
 * Created by gujin on 2018/4/18.
 * 登录用户信息
 */

public class UserInfo {


    /**
     * commenters_token : 2298395d-edbb-4338-afa2-77f3d4ebe7d2
     * createtime : 1524882737000
     * deleteflag : false
     * description :
     * email : 123@qq.com
     * headUrl : http://bazhuawangluo.img-cn-hangzhou.aliyuncs.com/cac_commenters_4j/images/41f4a2fc-3092-4978-854e-ae735216a33b.jpg
     * login_name : bx
     * org_name : APP测试部门
     * orgid : b7a151c9-7b97-4a64-8e4c-8a091a793e06
     * phone : 17685311111
     * user_state : true
     * userid : 138a396c-7c54-4da4-9be2-10ca4b2a38b5
     * username : bx
     */

    private String commenters_token;
    private long createtime;
    private boolean deleteflag;
    private String description;
    private String email;
    private String headUrl;
    private String login_name;
    private String org_name;
    private String orgid;
    private String phone;
    private boolean user_state;
    private String userid;
    private String username;

    public String getCommenters_token() {
        return commenters_token;
    }

    public void setCommenters_token(String commenters_token) {
        this.commenters_token = commenters_token;
    }

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
