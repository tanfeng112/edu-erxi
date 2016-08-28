package com.erxi.remote.payment.processor;

import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.exception.PaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public abstract class AbstractPaymentProcessor implements PaymentProcessor{

    Logger LOG= LoggerFactory.getLogger(AbstractPaymentProcessor.class);

    /**
     * 支付处理
     * @param paymentContext
     * @throws PaymentException
     */
    @Override
    public void process(PaymentContext paymentContext) throws PaymentException {
        try{
            //TODO 此处创建交易订单
            // 初始化支付渠道
            initPayChannel(paymentContext);
            //检查请求参数
            if(!preCheckContext(paymentContext)){
                return;
            }
            //处理支付请求
            boolean result=processTransaction(paymentContext);

            //TODO 根据下单结果更新交易订单状态
            LOG.info("支付处理结果：{},支付订单号{}",result);
        }finally {
            clean(paymentContext);
        }
    }

    /**
     * 处理支付回调
     * @param object
     * @throws PaymentException
     */
    @Override
    public void paymentCallback(Object object) throws PaymentException {
        //TODO
    }

    /**
     * 参数检查
     * @param transactionContext
     * @return
     * @throws PaymentException
     */
    public abstract boolean preCheckContext(PaymentContext transactionContext) throws PaymentException;

    /**
     * 初始化支付渠道
     * @param transactionContext
     * @throws PaymentException
     */
    protected abstract void initPayChannel(PaymentContext transactionContext) throws PaymentException;


    /**
     * 执行实际支付逻辑
     *
     * @param transactionContext
     * @return
     * @throws PaymentException
     */
    protected abstract boolean processTransaction(PaymentContext transactionContext) throws PaymentException;


    /**
     * clean threadlocal
     * @param transactionContext
     */
    protected abstract void clean(PaymentContext transactionContext);
}
