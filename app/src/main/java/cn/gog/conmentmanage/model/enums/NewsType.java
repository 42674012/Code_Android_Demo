package cn.gog.conmentmanage.model.enums;

/**
 * date：2017/4/20 on 9:53
 * desc:
 * author:zhangL
 * mail:1002606871@qq.com
 */
public enum NewsType {

    /**
     * 评论大分类
     * 1:文章
     * 2:直播
     * 3:视频
     * 4:图片
     * 5:VR
     */
    //  TYPE_ARTICLE=1;
//  TYPE_PHOTO_MEDIA=2;
//  TYPE_AUDIO_MEDIA=3;
//  TYPE_VIDEO_MEDIA=4;

    NEWS_TYPE_ARTICLE("文章", 1),
    NEWS_TYPE_PICTURE("图片", 2),
    NEWS_TYPE_AUDIO("音频", 3),
    NEWS_TYPE_VIDEO("视频", 4),
    NEWS_TYPE__LIVE("直播", 5),
    NEWS_TYPE_VR("VR", 6),;

    NewsType(String name, int value) {
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
