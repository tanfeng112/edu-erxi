package com.erxi.remote.integration.impl;

import com.erxi.common.utils.HttpClientUtil;
import com.erxi.common.utils.WeChatUtils;
import com.erxi.common.utils.XmlUtils;
import com.erxi.remote.bean.GetPayOrderStatusRequest;
import com.erxi.remote.bean.GetPayOrderStatusResponse;
import com.erxi.remote.bean.UnifiedOrderRequest;
import com.erxi.remote.bean.UnifiedOrderResponse;
import com.erxi.remote.config.PaymentConfig;
import com.erxi.remote.integration.WeChatPaymentClient;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author qingyin
 * @date 2016/8/27
 */
public class WeChatPaymentClientImpl implements WeChatPaymentClient{

    @Override
    public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request) {

        return null;
    }

    @Override
    public void generatorQRCode() {

    }

    @Override
    public GetPayOrderStatusResponse getPayOrderStatus(GetPayOrderStatusRequest request) {
        String nonce_str = WeChatUtils.getNonceStr();// 随机数
        // 将参数构成map
        SortedMap<Object,Object> paramsMap=new TreeMap<>();
        PaymentConfig config=new PaymentConfig();
        paramsMap.put("appid", config.getWechatAppid());
        paramsMap.put("mch_id", config.getWechatMch_id());
        paramsMap.put("nonce_str", WeChatUtils.getNonceStr());
        paramsMap.put("transaction_id", request.getTransactionId());
        String sign = WeChatUtils.createSign(paramsMap, config.getWechatMchsecret());
        paramsMap.put("sign", sign);
        String xmlParams = XmlUtils.getRequestXml(paramsMap);
        // 发起POST请求
        Map<String, String> mapFromXML = null;
        mapFromXML = XmlUtils.doXMLParse(HttpClientUtil.httpPost(config.getCheckOrderUrl(), xmlParams));

        if (null != mapFromXML) {
            String return_code = mapFromXML.get("return_code");
            if (return_code == null) {
               //查询失败
            }
        }
        return null;
    }
}
