/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.vo;

import java.io.Serializable;

import common.model.BaseResp;

/**
 *
 * Author: Gujin
 * Time: 16/9/9  17:45 
 */
public class RefreshTokenResp extends BaseResp implements Serializable {


    /**
     * status : 200
     * msg : 令牌刷新成功
     * data : {"created":1473233133800,"duration":30,"token":"2c361659a71e4c45af3b54a5bb31ed07","refreshToken":"d06befacabd54ff9b9d6bfd6e899af40"}
     */

    private int status;

    private String msg;
    /**
     * created : 1473233133800
     * duration : 30
     * token : 2c361659a71e4c45af3b54a5bb31ed07
     * refreshToken : d06befacabd54ff9b9d6bfd6e899af40
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
        private long created;
        private int duration;
        private String token;
        private String refreshToken;

        private String userLog;

        public String getUserLog() {
            return userLog;
        }

        public void setUserLog(String userLog) {
            this.userLog = userLog;
        }

        public long getCreated() {
            return created;
        }

        public void setCreated(long created) {
            this.created = created;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
