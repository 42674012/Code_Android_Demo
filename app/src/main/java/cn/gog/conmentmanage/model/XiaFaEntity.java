package cn.gog.conmentmanage.model;

import java.util.List;

/**
 * Created by lenovo on 2018/4/23.
 */

public class XiaFaEntity {

    /**
     * taskid : dddd2d2d2d2d2d2d
     * groupidList : ["dddd2d2d2d2d2d2da","dddd2d2d2d2d2d2ds"]
     */

    private String taskid;
    private List<String> groupidList;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public List<String> getGroupidList() {
        return groupidList;
    }

    public void setGroupidList(List<String> groupidList) {
        this.groupidList = groupidList;
    }
}
