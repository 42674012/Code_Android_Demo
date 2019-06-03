package cn.gog.conmentmanage.model;

import java.io.Serializable;

/**
 * Created by gujin on 2017/10/13.
 * 工作审批类型
 */

public class TypeItemEntity implements Serializable {

    /**
     * TaskTypeID : 6cd91d0f-826d-4e1a-bea4-1e343b6ffeae
     * TaskTypeName : 发文审批
     */

    private String TaskTypeID;
    private String TaskTypeName;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTaskTypeID() {
        return TaskTypeID;
    }

    public void setTaskTypeID(String TaskTypeID) {
        this.TaskTypeID = TaskTypeID;
    }

    public String getTaskTypeName() {
        return TaskTypeName;
    }

    public void setTaskTypeName(String TaskTypeName) {
        this.TaskTypeName = TaskTypeName;
    }


}
