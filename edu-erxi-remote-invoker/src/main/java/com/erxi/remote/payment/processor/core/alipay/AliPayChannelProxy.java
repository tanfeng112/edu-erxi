package com.erxi.remote.payment.processor.core.alipay;

import com.erxi.remote.payment.config.PaymentConfig;
import com.erxi.remote.payment.config.PaymentResponseEnum;
import com.erxi.remote.payment.exception.PaymentException;
import com.erxi.remote.payment.processor.context.AliPayContext;
import com.erxi.remote.payment.processor.context.PaymentContext;
import com.erxi.remote.payment.config.PayChannelEnum;
import com.erxi.remote.payment.processor.core.BasePayChannelProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public void processorPaymentNotify(HttpServletRequest request)  throws PaymentException{
        try {
            Map<String, Object> params = new HashMap<>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            /**
             * WAIT_BUYER_PAY

             交易创建，等待买家付款。

             TRADE_CLOSED

             在指定时间段内未支付时关闭的交易；
             在交易完成全额退款成功时关闭的交易。
             TRADE_SUCCESS

             交易成功，且可对该交易做操作，如：多级分润、退款等。

             TRADE_PENDING

             等待卖家收款（买家付款后，如果卖家账号被冻结）。

             TRADE_FINISHED

             交易成功且结束，即不可再做任何操作
             */
            if (AlipayNotify.verify(params,paymentConfig)) {//验证成功
                if(trade_status.equals("TRADE_FINISHED")){

                } else if (trade_status.equals("TRADE_SUCCESS")){

                }
            }
        }catch(Exception e){
            throw new PaymentException("支付宝回调处理异常："+e.getMessage());
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
