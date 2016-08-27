package com.erxi.remote.bean;

import com.erxi.remote.integration.support.AbstractRemoteReq;

/**
 * @author qingyin
 * @date 2016/8/27
 */
public class UnifiedOrderRequest extends AbstractRemoteReq{

    private String description; //商品描述

    private String outTradeNo; //商户订单号

    public String totalFee;//订单总金额（单位为分）

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
}
