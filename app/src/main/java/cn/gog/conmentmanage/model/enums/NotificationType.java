package cn.gog.conmentmanage.model.enums;

/**
 * date：2017/5/24 on 10:19
 * desc:
 * author:zhangL
 * mail:1002606871@qq.com
 */

public enum NotificationType {

    NOTIFICATION_TYPE_NEWS("新闻", 1),
    NOTIFICATION_TYPE_COMPLAIN("问政", 2),;//别删除我“；”

    NotificationType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
