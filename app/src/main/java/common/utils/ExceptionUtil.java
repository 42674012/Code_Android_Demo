/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Author: Gujin
 * Time: 16/9/5  17:08
 */
public class ExceptionUtil {

    public static String convertExcpetionToString(Throwable throwable) {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));
        String result = "";
        try {
            result = errors.toString();
            errors.flush();
            errors.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
