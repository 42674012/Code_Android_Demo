package common.model;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public class Notice {
    public int type;
    public Object content;

    public Notice(int type) {
        this.type = type;
    }

    public Notice(int type, Object content) {
        this.type = type;
        this.content = content;
    }

    public Notice() {
    }
}
