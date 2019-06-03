package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.WangXinBan;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/22.
 */

public interface IXiaFaView  extends IBaseMvpView {
    void groupFindByOrgidSuccess(List<WangXinBan> wangXinBans);

    void assignToGroupSuccess(String jsonStr);
}
