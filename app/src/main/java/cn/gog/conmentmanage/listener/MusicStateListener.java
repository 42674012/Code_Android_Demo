package cn.gog.conmentmanage.listener;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/21 on 11:32
 * desc:
 */

public interface MusicStateListener {
    /**
     * 更新歌曲状态信息
     */
    void updateTrackInfo();

    void updateTime();

    void changeTheme();

    void reloadAdapter();
}
