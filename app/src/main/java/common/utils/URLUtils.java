/**
 * Copyright (c) 2016年 贵州多彩宝互联网服务有限公司. All rights reserved.
 */
package common.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * URL常用工具
 * <p>
 * Author: Gujin
 * Time: 16/9/16  14:37
 */
public class URLUtils {

    /**
     * 为url追加参数
     *
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String appendParam(String url, String key, Object value) {

        StringBuffer urlStringBuffer = new StringBuffer();

        urlStringBuffer.append(url);

        if (!url.contains("?")) {
            urlStringBuffer.append("?");
        } else {
            urlStringBuffer.append("&");
        }
        urlStringBuffer.append(key).append("=").append(value);
        return urlStringBuffer.toString();
    }

    /**
     * 为url追加参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String appendParams(String url, Map<String, Object> params) {
        if (params == null) {
            return url;
        }

        StringBuffer urlStringBuffer = new StringBuffer();

        urlStringBuffer.append(url);

        if (!url.contains("?")) {
            urlStringBuffer.append("?");
        } else {
            urlStringBuffer.append("&");
        }


        for (String key : params.keySet()) {
            urlStringBuffer.append(key).append("=").append(params.get(key)).append("&");
        }

        if (urlStringBuffer.toString().endsWith("&")) {
            return urlStringBuffer.toString().substring(0, urlStringBuffer.toString().length() - 1);
        }

        return urlStringBuffer.toString();

    }

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    public static Map<String, String> splitQuery(String stringUrl, String protocol) throws UnsupportedEncodingException, MalformedURLException {

        URL url = new URL(stringUrl.replace(protocol + ":", "http:"));


        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        if (StringUtils.isEmpty(query)) {
            return new HashMap<>();
        }
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
}
