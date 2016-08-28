package com.erxi.remote.payment.config;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public enum PayChannelEnum {

    ALI_PAY("alipay","微信支付渠道"),
    WECHAT_PAY("wechat_pay","微信支付渠道");

    private String code;

    private String desc;

    PayChannelEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
