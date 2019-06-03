package common.vo;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ResultVo extends BaseResp {


    /**
     * status : 200
     * msg : 订单创建成功
     */

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
