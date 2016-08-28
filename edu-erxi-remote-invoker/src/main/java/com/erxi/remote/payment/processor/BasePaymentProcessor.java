package com.erxi.remote.payment.processor;

import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.exception.PaymentException;
import com.erxi.remote.payment.processor.core.PayChannelProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public abstract class BasePaymentProcessor extends AbstractPaymentProcessor{

    Logger LOG= LoggerFactory.getLogger(BasePaymentProcessor.class);

    ThreadLocal<PayChannelProxy> payProxyThreadLocal = new ThreadLocal<>();


    /**
     * 返回payChannelProxy实例
     * @return
     */
    protected PayChannelProxy getPayProxy() {
        return payProxyThreadLocal.get();
    }

    @Override
    public boolean preCheckContext(PaymentContext transactionContext) throws PaymentException {
        return getPayProxy().preCheck(transactionContext);
    }

    @Override
    protected void initPayChannel(PaymentContext transactionContext) throws PaymentException {
        payProxyThreadLocal.set(PayChannelProxy.payChannelProxyMap.get(transactionContext.getPayChannelEnum()));
    }

    @Override
    protected boolean processTransaction(PaymentContext transactionContext) throws PaymentException {
        return getPayProxy().processorPayment(transactionContext);
    }

    @Override
    protected void clean(PaymentContext transactionContext) {
        payProxyThreadLocal.remove();
    }
}
