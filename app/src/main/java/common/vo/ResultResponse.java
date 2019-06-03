package common.vo;

import common.model.BaseResp;

/**
 * author:gujin
 * mail:1002606871@qq.com
 * date：2017/4/8
 * desc:
 */
public class ResultResponse<T> extends BaseResp {

    /**
     * status : true
     * code : 1
     * result : 操作成功
     */

    private boolean status;
    private int code;
    private T result;
    public ResultResponse(){
        super();
    }

    public ResultResponse(boolean status, int code, T result) {
        this.status = status;
        this.code = code;
        this.result = result;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
