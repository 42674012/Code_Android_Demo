package cn.gog.conmentmanage.model;

/**
 * Created by lenovo on 2018/4/21.
 */

public class OrgEntity {

    /**
     * orgid : 1114
     * org_name : 第三级4
     */

    private String orgid;
    private String org_name;

    private Boolean isChosen = false;

    public Boolean getChosen() {
        return isChosen;
    }

    public void setChosen(Boolean chosen) {
        isChosen = chosen;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
}
