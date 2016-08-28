package com.erxi.remote.payment.processor.core.wechatpay;

import com.erxi.remote.payment.bean.UnifiedOrderBean;
import com.erxi.remote.payment.config.PaymentConfig;
import com.erxi.remote.payment.config.PaymentConstants;
import com.erxi.remote.payment.config.PaymentResponseEnum;
import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.config.PayChannelEnum;
import com.erxi.remote.payment.processor.context.WeChatPayContext;
import com.erxi.remote.payment.processor.core.BasePayChannelProxy;
import com.erxi.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author qingyin
 * @date 2016/8/28
 */
@Service
public class WeChatPayChannelProxy extends BasePayChannelProxy{

    Logger LOG= LoggerFactory.getLogger(WeChatPayChannelProxy.class);

    @Autowired
    PaymentConfig paymentConfig;

    /**
     * 微信支付先调用商户下单接口生成商户订单，下单成功后会返回一个url地址，前段将url地址转成二维码让用户扫描，扫描支付完成后有一个回调
     * @param paymentContext
     * @return
     */

    @Override
    public boolean process(PaymentContext paymentContext) {
        WeChatPayContext context = (WeChatPayContext) paymentContext;
        try {
            //step1 调用微信商户统一下单接口
            SortedMap<Object, Object> paraMap = new TreeMap<>();
            paraMap.put("body", context.getBody());
            paraMap.put("out_trade_no", context.getOutTradeNo());
            paraMap.put("total_fee", context.getTotalFee().intValue());//单位分
            paraMap.put("spbill_create_ip", context.getSpbillCreateIp());
            paraMap.put("openid", context.getOpenid());
            paraMap.put("appid", paymentConfig.getWechatAppid());
            paraMap.put("mch_id", paymentConfig.getWechatMch_id());
            paraMap.put("nonce_str", WeChatRequest.getNonceStr());
            paraMap.put("trade_type", context.getTradeType());
            paraMap.put("notify_url", paymentConfig.getWechatNotifyurl());// 此路径是微信服务器调用支付结果通知路径
            String sign = WeChatRequest.createSign(paraMap, paymentConfig.getWechatMchsecret());
            paraMap.put("sign", sign);
            String xml = WeChatRequest.getRequestXml(paraMap);
            String xmlStr = HttpClientUtil.httpPost(paymentConfig.getWechatUnifiedOrder(), xml);
            LOG.info("微信下单返回结果："+xmlStr);
            Map<String,String> resultMap=WeChatRequest.doXMLParse(xmlStr);
            if("SUCCESS".equals(resultMap.get("return_code"))){
                if("SUCCESS".equals(resultMap.get("result_code"))){
                    //表示订单处理成功
                    UnifiedOrderBean unifiedOrderBean=new UnifiedOrderBean();
                    unifiedOrderBean.setPrepay_id(resultMap.get("prepay_id"));
                    unifiedOrderBean.setCode_url(resultMap.get("code_url"));
                    context.setUnifiedOrderBean(unifiedOrderBean);
                    context.setResCode(PaymentResponseEnum.SUCCESS,"");
                    return true;
                }else{
                    String errMsg=resultMap.get("err_code")+":"+resultMap.get("err_code_des");
                    context.setResCode(PaymentResponseEnum.PAYMENT_PROCESSOR_FAILED,errMsg);
                    return false;
                }
            }else{
                context.setResCode(PaymentResponseEnum.PAYMENT_PROCESSOR_FAILED,resultMap.get("return_msg"));
                return false;
            }
        }catch(Exception e){
            LOG.error("微信支付下单异常{}",context.getOutTradeNo());
            return false;
        }
    }

    @Override
    public boolean preCheck(PaymentContext paymentContext) {
        WeChatPayContext weChatPayContext=(WeChatPayContext)paymentContext;
        if(StringUtils.isBlank(weChatPayContext.getBody())){
            paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT, "商品描述不能为空");
            return false;
        }
        if(StringUtils.isBlank(weChatPayContext.getOutTradeNo())){
            paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT,"商户订单号不能为空");
            return false;
        }
        if(StringUtils.isBlank(weChatPayContext.getTradeType())){
            paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT,"交易类型不能为空");
            return false;
        }
        if(weChatPayContext.getTotalFee()==null){
            paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT,"支付总金额不能为空");
            return false;
        }
        if(StringUtils.isBlank(weChatPayContext.getSpbillCreateIp())){
            paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT,"终端ip不能为空");
            return false;
        }
        if(PaymentConstants.TradeTypeEnum.JSAPI.getType().equals(weChatPayContext.getTradeType())){
            if(StringUtils.isBlank(weChatPayContext.getOpenid())){
                paymentContext.setResCode(PaymentResponseEnum.SYS_PARAM_NOT_RIGHT,"交易类型不能为空");
                return false;
            }
        }
        return true;
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.WECHAT_PAY.getCode();
    }
}
