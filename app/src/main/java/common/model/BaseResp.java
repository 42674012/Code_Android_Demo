/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 客户端与服务器的数据传输的接收ValueObject
 * Author: Gujin
 * Time: 16/9/5  12:33
 */
public class BaseResp implements Serializable {


    public static final int SUCC = 200;

    public static final int UNAUTHORIZED = 401;


    public static final int NOT_LOGIN = 403;
    public static final int NOT_FOUND = 404;
    public static final int FORBIDDEN = 403;


    /**
     * 重写toString方法以实现JSON返回
     *
     * @return JSON字符串
     */
    @Override
    public String toString() {

        return JSON.toJSONString(this);
    }
}
