package cn.gog.conmentmanage.model;

/**
 * Created by lenovo on 2018/4/23.
 */

public class GroupEntity {

    /**
     * id : 11
     * label : 网信办
     * groupIs : false
     * org_parentid : 0
     */

    private String id;
    private String label;
    private boolean groupIs;
    private String org_parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isGroupIs() {
        return groupIs;
    }

    public void setGroupIs(boolean groupIs) {
        this.groupIs = groupIs;
    }

    public String getOrg_parentid() {
        return org_parentid;
    }

    public void setOrg_parentid(String org_parentid) {
        this.org_parentid = org_parentid;
    }
}
