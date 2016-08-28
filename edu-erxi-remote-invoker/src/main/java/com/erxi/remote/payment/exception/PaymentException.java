package com.erxi.remote.payment.exception;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public class PaymentException extends Exception{

    public PaymentException(String msg) {
        super(msg);
    }

    public PaymentException(String msg, Throwable e) {
        super(msg, e);
    }
}
