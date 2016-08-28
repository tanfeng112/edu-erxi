package com.erxi.remote.payment.processor.core.alipay;

import com.erxi.remote.payment.config.PaymentConfig;
import com.erxi.remote.payment.config.PaymentResponseEnum;
import com.erxi.remote.payment.processor.context.AliPayContext;
import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.config.PayChannelEnum;
import com.erxi.remote.payment.processor.core.BasePayChannelProxy;
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
public class AliPayChannelProxy extends BasePayChannelProxy {

    Logger LOG= LoggerFactory.getLogger(AliPayChannelProxy.class);

    @Autowired
    PaymentConfig paymentConfig;

    /**
     * 支付宝只需要将参数拼接成一个html表单。 在前段页面展示出来即可。用户在该表单中支付完成后有个回调。
     * @param paymentContext
     * @return
     */

    @Override
    public boolean process(PaymentContext paymentContext) {
        AliPayContext context=(AliPayContext)paymentContext;
        try{
            //把请求参数打包成数组
            SortedMap<String, Object> sParaTemp =new TreeMap<>();
            sParaTemp.put("service", paymentConfig.getAli_service());
            sParaTemp.put("partner", paymentConfig.getAli_partner());
            sParaTemp.put("seller_email", paymentConfig.getSeller_email());
            sParaTemp.put("seller_id", paymentConfig.getSeller_id());
            sParaTemp.put("_input_charset", paymentConfig.getInput_charset());
            sParaTemp.put("payment_type", 1);
            sParaTemp.put("it_b_pay", paymentConfig.getIt_b_pay());
            sParaTemp.put("notify_url", paymentConfig.getNotify_url());
            sParaTemp.put("return_url",paymentConfig.getReturn_url());
            sParaTemp.put("out_trade_no",context.getOutTradeNo());
            sParaTemp.put("subject", context.getSubject());
            sParaTemp.put("total_fee", context.getTotalFee().doubleValue());
            Map<String, Object> sPara = AlipayRequest.buildRequestParam(sParaTemp,paymentConfig);
            String strPara = AlipayRequest.buildRequest(sPara, "get", "确认",paymentConfig);
            //返回html
            context.setHtmlStr(strPara);
            context.setResCode(PaymentResponseEnum.SUCCESS,"");
            return true;
        }catch(Exception e){
            LOG.error("支付宝下单异常：");
            context.setResCode(PaymentResponseEnum.PAYMENT_PROCESSOR_FAILED,e.getMessage());
            return false;
        }
    }

    @Override
    public boolean preCheck(PaymentContext paymentContext) {
        return false;
    }

    @Override
    public String getPayChannel() {
        return PayChannelEnum.ALI_PAY.getCode();
    }
}
