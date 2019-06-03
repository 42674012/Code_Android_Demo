package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.OrgEntity;
import common.view.IBaseMvpView;

/**
 * Created by lenovo on 2018/4/21.
 */

public interface IAddRequestOrgView extends IBaseMvpView {
    void childrenOrgSuccess(List<OrgEntity> data);

    void assignToOrgSuccess(String data);
}
