package cn.gog.conmentmanage.view;

import java.util.List;

import cn.gog.conmentmanage.model.SearchEntity;
import common.view.IBaseMvpView;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/5/3 on 20:37
 * desc:
 */

public interface ISearchView extends IBaseMvpView {

    void searchSuccess(List<SearchEntity> data);
}
