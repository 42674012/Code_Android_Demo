package cn.gog.conmentmanage.model;

/**
 * Created by gujin on 2018/4/18.
 * 我的部分的实体
 */

public class MeEntity {
    public static final int PERSONNELINFO = 1 ;
    public static final int CONTACTINFO = 2;
    public static final int SAFETY = 3 ;
    public static final int SETTING = 4;
    public static final int ABOUT = 5;
    private int type;
    private String name;
    private int resID;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }
}
