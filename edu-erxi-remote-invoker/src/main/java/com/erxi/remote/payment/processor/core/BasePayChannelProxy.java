package com.erxi.remote.payment.processor.core;

import com.erxi.remote.payment.processor.context.PaymentContext;

import javax.annotation.PostConstruct;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public abstract class BasePayChannelProxy implements PayChannelProxy{


    @PostConstruct
    public void init(){
        payChannelProxyMap.put(getPayChannel(),this);
    }

    @Override
    public boolean processorPayment(PaymentContext paymentContext) {
        //执行支付
        boolean result=process(paymentContext);

        return result;
    }

    /**
     * 具体支付逻辑抽象
     * @param paymentContext
     * @return
     */
    public abstract boolean process(PaymentContext paymentContext);

    /**
     * 获取支付渠道
     * @return
     */
    public abstract String getPayChannel();


}
