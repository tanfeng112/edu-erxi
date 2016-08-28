package com.erxi.remote.payment.processor.context;

import com.erxi.remote.payment.bean.UnifiedOrderBean;

/**
 * @author qingyin
 * @date 2016/8/28
 */
public class WeChatPayContext extends PaymentContext{

    private String body; //商品描述（必填）

    private String detail ; //商品详情

    private String outTradeNo; //商户订单号（必填）

    private Integer totalFee; //总金额，单位分（必填）

    private String spbillCreateIp; //终端IP

    /**
     * JSAPI--公众号支付
     * NATIVE--原生扫码支付
     * APP--app支付，统一下单接口trade_type的传参可参考这里
     * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    private String tradeType;//交易类型（必填）

    private String openid; //当交易类型为JSAPI的时候，该字段必填

    private UnifiedOrderBean unifiedOrderBean; //支付成功后返回的值

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public UnifiedOrderBean getUnifiedOrderBean() {
        return unifiedOrderBean;
    }

    public void setUnifiedOrderBean(UnifiedOrderBean unifiedOrderBean) {
        this.unifiedOrderBean = unifiedOrderBean;
    }

    @Override
    public String toString() {
        return "WeChatPayContext{" +
                "body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", totalFee=" + totalFee +
                ", spbillCreateIp='" + spbillCreateIp + '\'' +
                ", tradeType='" + tradeType + '\'' +
                '}';
    }
}
