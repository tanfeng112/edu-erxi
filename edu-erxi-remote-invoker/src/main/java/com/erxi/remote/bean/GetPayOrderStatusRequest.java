package com.erxi.remote.bean;

import java.io.Serializable;

/**
 * @author qingyin
 * @date 2016/8/27
 */
public class GetPayOrderStatusRequest implements Serializable{
    private static final long serialVersionUID = 5469656593809177586L;

    /**
     *微信订单号
     */
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
