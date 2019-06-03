package common.event;

import java.util.Map;

/**
 * Created by Administrator on 2016/9/24.
 */

public class AliPayEvent {
    public static class RequestEvent {

        private String orderInfo;

        public RequestEvent(String orderInfo) {
            this.orderInfo = orderInfo;
        }

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }
    }

    public static class ResponseEvent {


        private final Map<String, String> payResult;

        public ResponseEvent(Map<String, String> obj) {
            this.payResult =obj;
        }

        public Map<String, String> getPayResult() {
            return payResult;
        }
    }

}
