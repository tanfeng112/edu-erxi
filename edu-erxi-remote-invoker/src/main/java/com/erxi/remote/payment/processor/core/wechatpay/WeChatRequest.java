package com.erxi.remote.payment.processor.core.wechatpay;

import com.erxi.common.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.*;

/**
 * 微信相关工具类
 * @author qingyin
 * @date 2016/8/27
 */
public class WeChatRequest {

    static Logger LOG= LoggerFactory.getLogger(WeChatRequest.class);


    /**
     * 随机字符串，不长于32位。
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Utils.GetMD5Code(String.valueOf(random.nextInt(10000)));
    }

    /**
     * 时间戳
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 微信支付签名算法sign
     * @param key 商户的appsecret
     * @param parameters
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String createSign(SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Utils.GetMD5Code(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 解析xml字符串转成map集合
     * @param xml
     * @return
     */
    public static Map<String, String> doXMLParse(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        // 将编码改为UTF-8,并去掉换行符\空格等
        xml = xml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        //去掉空白 换行符
        final StringBuilder sb = new StringBuilder(xml.length());
        char c;
        for(int i = 0; i < xml.length(); i++){
            c = xml.charAt(i);
            if(c != '\n' && c != '\r' && c != ' '){
                sb.append(c);
            }
        }
        xml = sb.toString();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            StringReader reader = new StringReader(xml);
            InputSource inputSource = new InputSource(reader);
            Document document = documentBuilder.parse(inputSource);
            // 1.获取xml文件的根元素
            Element element = document.getDocumentElement();
            // 2.获取根元素下的所有子标签
            NodeList nodeList = element.getChildNodes();
            // 3.遍历子标签集合
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                map.put(node.getNodeName(), node.getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            LOG.error("xml解析异常：" + e);
        }

        return map;
    }

    /**
     * @Description：返回给微信的参数
     * @param return_code
     *            返回编码
     * @param return_msg
     *            返回信息
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    /**
     * @Description：将请求参数转换为xml格式的string
     * @param parameters
     *            请求参数
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<?> es = parameters.entrySet();
        Iterator<?> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
                    || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
            LOG.error("map转化成xml异常："+e);
        }
        return "";
    }
}
