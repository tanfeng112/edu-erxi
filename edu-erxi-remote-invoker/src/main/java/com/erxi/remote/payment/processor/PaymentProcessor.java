package com.erxi.remote.payment.processor;

import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.exception.PaymentException;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付处理
 * @author qingyin
 * @date 2016/8/28
 */
public interface PaymentProcessor {

    /**
     * 处理支付流程
     * @param paymentContext
     * @throws PaymentException
     */
    public void process(PaymentContext paymentContext) throws PaymentException;


    /**
     * 微信支付将request传递下来
     *
     * 处理支付回调 并更新订单状态
     * @throws PaymentException
     */
    public void paymentCallback(HttpServletRequest request) throws PaymentException;
}
