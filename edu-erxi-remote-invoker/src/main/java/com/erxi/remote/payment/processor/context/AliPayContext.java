package com.erxi.remote.payment.processor.context;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public class AliPayContext extends PaymentContext{

    private String outTradeNo; //商户订单号（必填）

    private String subject;//商品名称（必填）

    private Double totalFee; //交易金额（单位为元）必填


    /**
     * 返回参数
     */
    private String htmlStr; //构建html表单
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getHtmlStr() {
        return htmlStr;
    }

    public void setHtmlStr(String htmlStr) {
        this.htmlStr = htmlStr;
    }

    @Override
    public String toString() {
        return "AliPayContext{" +
                "outTradeNo='" + outTradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", totalFee=" + totalFee +
                "} " + super.toString();
    }
}
