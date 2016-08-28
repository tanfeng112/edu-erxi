package com.erxi.remote.payment.processor.core;

import com.erxi.remote.payment.processor.context.PaymentContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author qingyin
 * @date 2016/8/28
 */
public interface PayChannelProxy {

    Map<String,PayChannelProxy> payChannelProxyMap = new ConcurrentHashMap<>();

    /**
     * 执行预先校验逻辑
     * @param paymentContext
     */
    boolean preCheck(PaymentContext paymentContext);


    /**
     * 处理支付逻辑
     * @param paymentContext
     * @return
     */
    boolean processorPayment(PaymentContext paymentContext);



}
