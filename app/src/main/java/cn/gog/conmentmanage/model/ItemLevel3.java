package cn.gog.conmentmanage.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.gog.conmentmanage.ui.adapter.ExpandableItemAdapter;

/**
 * Created by lenovo on 2018/4/22.
 *
 */

public  class ItemLevel3    implements MultiItemEntity {


    private String id;
    private String label;
    private boolean groupIs;
    private String org_parentid;

    private boolean isChosen =false;

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

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



    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_3;
    }
}
