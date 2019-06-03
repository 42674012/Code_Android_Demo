package cn.gog.conmentmanage.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cn.gog.conmentmanage.ui.adapter.ExpandableItemAdapter;

/**
 * Created by gujin on 2016/8/10.
 */
public class WangXinBan extends AbstractExpandableItem<ItemLevel1> implements MultiItemEntity {


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

    private boolean isChosen =false;

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    List<ItemLevel1> children;

    public List<ItemLevel1> getChildren() {
        return children;
    }

    public void setChildren(List<ItemLevel1> children) {
        this.children = children;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
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
}
