/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.utils;

/**
 *
 * Author: Gujin
 * Time: 16/9/7  13:25 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }
}
