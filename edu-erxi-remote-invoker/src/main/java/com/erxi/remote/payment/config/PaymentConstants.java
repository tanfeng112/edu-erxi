package com.erxi.remote.payment.config;

/**
 * 支付相关常量
 * @author qingyin
 * @date 2016/8/28
 */
public class PaymentConstants {

    public enum TradeTypeEnum{
        JSAPI("JSAPI","微信公众号支付"),
        NATIVE("NATIVE","原生扫码支付"),
        APP("APP","app支付"),
        MICROPAY("MICROPAY","刷卡支付");
        private String type;

        private String desc;

        TradeTypeEnum(String type,String desc){
            this.type=type;
            this.desc=desc;
        }

        public String getType() {
            return type;
        }

        public String getDesc() {
            return desc;
        }
    }
}
