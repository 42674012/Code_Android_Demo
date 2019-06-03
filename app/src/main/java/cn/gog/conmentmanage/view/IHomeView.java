package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.LiveTokenEntity;
import cn.gog.conmentmanage.model.MsgEntity;
import common.view.IBaseMvpView;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/12/8
 * desc:
 */
public interface IHomeView extends IBaseMvpView {

    void getTokenOk(LiveTokenEntity data);

    void getDecodeResultOk(String data);

    void getNewestMsfListSuccess(List<MsgEntity> data);
}
