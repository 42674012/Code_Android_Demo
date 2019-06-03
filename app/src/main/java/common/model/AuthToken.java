/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.model;


/**
 *
 * Author: Gujin
 * Time: 16/9/9  11:35 
 */
public class AuthToken {


    /**
     * 用户手机号
     */
    private String phoneNum;

    /**
     * created : 1473391013486
     * duration : 30
     * token : 2b356a7e52214e6b88a851c203597421
     * refreshToken : cf8440592ad74582ab6c886e523cd90a
     */

    /**
     * 创建时间
     */
    private long created;

    /**
     * 有效时间  单位:分钟
     */
    private int duration;

    /**
     * Token
     */
    private String token;
    /**
     *
     */
    private String refreshToken;

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


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
