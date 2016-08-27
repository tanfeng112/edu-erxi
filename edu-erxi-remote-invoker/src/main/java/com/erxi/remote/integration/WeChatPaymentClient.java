package com.erxi.remote.integration;

import com.erxi.remote.bean.GetPayOrderStatusRequest;
import com.erxi.remote.bean.GetPayOrderStatusResponse;
import com.erxi.remote.bean.UnifiedOrderRequest;
import com.erxi.remote.bean.UnifiedOrderResponse;

/**
 * 微信支付client
 * @author qingyin
 * @date 2016/8/27
 */
public interface WeChatPaymentClient {

    /**
     * 微信商户统一下单接口
     * @param request
     * @return
     */
    UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest request);


    /**
     * 生成支付二维码
     */
    void generatorQRCode();


    GetPayOrderStatusResponse getPayOrderStatus(GetPayOrderStatusRequest request);
}
