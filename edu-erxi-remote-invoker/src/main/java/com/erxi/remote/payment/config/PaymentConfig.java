package com.erxi.remote.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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

    /***************************************************************/
    @Value("${ALI_SERVICE}")
    private String ali_service;

    @Value("${ALI_PARTNER}")
    private String ali_partner;

    @Value("${INPUT_CHARSET}")
    private String input_charset;

    @Value("${SIGN_TYPE}")
    private String sign_type;

    @Value("${NOTIFY_URL}")
    private String notify_url;

    @Value("${RETURN_URL}")
    private String return_url;

    @Value("${SELLER_ID")
    private String seller_id;

    @Value("${SELLER_EMAIL}")
    private String seller_email;

    @Value("${PAY_GATEWAY_NEW}")
    private String pay_gateway_new;

    @Value("${IT_B_PAY}")
    private String it_b_pay;

    @Value("${PRIVATE_KEY}")
    private String private_key;// 商户的私钥

    @Value("${PUBLIC_KEY}")
    private String public_key;// 支付宝的公钥

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

    public String getAli_service() {
        return ali_service;
    }

    public String getAli_partner() {
        return ali_partner;
    }

    public String getInput_charset() {
        return input_charset;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public String getPay_gateway_new() {
        return pay_gateway_new;
    }

    public String getIt_b_pay() {
        return it_b_pay;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
