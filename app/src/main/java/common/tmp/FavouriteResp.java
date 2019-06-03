package common.tmp;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/9/30.
 */

public class FavouriteResp extends BaseResp {
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
