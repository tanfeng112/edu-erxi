package com.erxi.remote.payment.config;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public enum PaymentResponseEnum {

    SUCCESS(0,"请求处理成功"),
    SYS_PARAM_NOT_RIGHT(1001, "请求参数校验失败"),
    PAYMENT_PROCESSOR_FAILED(1002,"支付失败");

    private final int code;
    private final String msg;
    private PaymentResponseEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
    private PaymentResponseEnum(int code,String msg,String detailDesc){
        this.code=code;
        this.msg=msg;
    }

    public int getCode(){return code;}
    public String getMsg(){return msg;}
    public String getMsg(String detailDesc){return msg+" : "+detailDesc;}
}
