package com.erxi.common.utils;

import java.util.*;

/**
 * 微信相关工具类
 * @author qingyin
 * @date 2016/8/27
 */
public class WeChatUtils {

    /**
     * 随机字符串，不长于32位。
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Utils.GetMD5Code(String.valueOf(random.nextInt(10000)));
    }

    /**
     * 时间戳
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 微信支付签名算法sign
     * @param key 商户的appsecret
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String createSign(SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Utils.GetMD5Code(sb.toString()).toUpperCase();
        return sign;
    }
}
