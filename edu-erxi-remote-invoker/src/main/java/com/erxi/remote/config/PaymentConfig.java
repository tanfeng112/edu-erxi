package com.erxi.remote.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author qingyin
 * @date 2016/8/27
 */
@Configuration
@PropertySource("classpath:paymentConfig.properties")
public class PaymentConfig {

    @Value("${WECHAT_APPID}")
    private String wechatAppid;

    @Value("${WECHAT_MCH_ID}")
    private String wechatMch_id;

    @Value("${WECHAT_APPSECRET}")
    private String wechatAppsecret;

    @Value("${WECHAT_MCHSECRET}")
    private String wechatMchsecret;

    @Value("${WECHAT_NOTIFYURL}")
    private String wechatNotifyurl;

    @Value("${WECHAT_UNIFIED_ORDER}")
    private String wechatUnifiedOrder;

    @Value("${WECHAT_ORDER_QUERY}")
    private String checkOrderUrl;

    public String getWechatAppid() {
        return wechatAppid;
    }

    public String getWechatMch_id() {
        return wechatMch_id;
    }

    public String getWechatAppsecret() {
        return wechatAppsecret;
    }

    public String getWechatMchsecret() {
        return wechatMchsecret;
    }

    public String getWechatNotifyurl() {
        return wechatNotifyurl;
    }

    public String getWechatUnifiedOrder() {
        return wechatUnifiedOrder;
    }

    public String getCheckOrderUrl() {
        return checkOrderUrl;
    }
}
