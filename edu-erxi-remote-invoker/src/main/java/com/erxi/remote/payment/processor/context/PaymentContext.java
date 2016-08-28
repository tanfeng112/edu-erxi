package com.erxi.remote.payment.processor.context;

import com.erxi.remote.payment.config.PayChannelEnum;
import com.erxi.remote.payment.config.PaymentResponseEnum;

import java.io.Serializable;

/**
 * 支付上下文
 * @author qingyin
 * @date 2016/8/28
 */
public class PaymentContext implements Serializable{
    private static final long serialVersionUID = -7194961610487256111L;

    private PayChannelEnum payChannelEnum; //支付渠道

    private int resCode;

    private String memo;

    public void setResCode(PaymentResponseEnum paymentResponseEnum,String desc){
        this.resCode=paymentResponseEnum.getCode();
        this.memo=paymentResponseEnum.getMsg(desc);
    }

    public PayChannelEnum getPayChannelEnum() {
        return payChannelEnum;
    }

    public void setPayChannelEnum(PayChannelEnum payChannelEnum) {
        this.payChannelEnum = payChannelEnum;
    }
}
