package common.vo;

import common.model.BaseResp;

/**
 * Created by Administrator on 2016/9/21.
 */

public class CreateOrderResp extends BaseResp {


    /**
     * status : 200
     * msg : 订单创建成功
     * data : {"platSerial":"20160921140732076382","orderParams":"{\"sign\":\"AAC7B8C1B636041EA1F2E27318A5BC38\",\"timestamp\":\"1474438053\",\"noncestr\":\"Dm8YboMJdB5ViFNb\",\"partnerid\":\"1236386102\",\"prepayid\":\"wx20160921140733e6462d38cc0796550707\",\"package\":\"Sign=WXPay\",\"appid\":\"wx5a6e026fc55fd2b2\"}"}
     */

    private int status;
    private String msg;
    /**
     * platSerial : 20160921140732076382
     * orderParams : {"sign":"AAC7B8C1B636041EA1F2E27318A5BC38","timestamp":"1474438053","noncestr":"Dm8YboMJdB5ViFNb","partnerid":"1236386102","prepayid":"wx20160921140733e6462d38cc0796550707","package":"Sign=WXPay","appid":"wx5a6e026fc55fd2b2"}
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String platSerial;
        private String orderParams;
        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPlatSerial() {
            return platSerial;
        }

        public void setPlatSerial(String platSerial) {
            this.platSerial = platSerial;
        }

        public String getOrderParams() {
            return orderParams;
        }

        public void setOrderParams(String orderParams) {
            this.orderParams = orderParams;
        }
    }
}
