package com.easypay.test;



import org.jsoup.Connection.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.easypay.test
 * @ClassName: test
 * @Datetime: 2020/1/10   16:02
 * @Description:
 * @Author: liyongguang
 */
public class test {
    private static String channelid = "616161620011406";//渠道编号
    private static String merid = "MER021320011406";//商户编号
    private static String termid = "TE011406";//终端编号
    private static String channelkey = "i4an64ghzf33yp";//渠道密钥
    private static String signkey = "140FE18599D9248DF9622742281E40CC";//签名密钥
    private static String url = "https://notify-test.eycard.cn:7443/WorthTech_Access_AppPaySystemV2/apppayacc";//请求地址

    public static void main(String[] args) {
//     test1();
     test2();
    }

    //请求签名密钥接口
    public static void test1(){
        String opt = "getSign";//类型
        Map<String , String> param = new HashMap<String , String>();
        param.put("channelid" , channelid);
        param.put("opt" , opt);
        String sign = SignUtil.getSign(param , channelkey);
        System.out.println("签名值:"+sign);
        param.put("sign" , sign);
        try {
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println(result.body());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   ///统一单接口(NATIVE,根据实际情况，支付宝、银联二选一)
    public static void test2(){
        String tradetrace = "1G22J2K2V78SGJ0KJ";//商户订单号
        String opt = "apPerOrder";//操作类型,支付宝
        //String opt = "upPerOrder";//操作类型,银联
        String tradetype = "NATIVE";//支付方式,线下/原生，不支持微信
        String tradeamt = "1";//交易金额--分
        String body = "商品";//商品描述
        String notifyurl = "www.baidu.com";//客户端接收微信支付成功通知的地址
        Map<String , String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("tradetrace" , tradetrace);
        param.put("opt" , opt);
        param.put("tradetype" , tradetype);
        param.put("tradeamt" , tradeamt);
        param.put("body" , body);
        param.put("notifyurl" , notifyurl);
        String sign = SignUtil.getSign(param , signkey);
        param.put("sign" , sign);
        try {
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            //验签
            SignUtil.checksign(result.body() , signkey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*//统一单接口(JSAPI)
    public static void test3(){
        String tradetrace = "";//商户订单号
        String opt = "apPreOrder";//操作类型
        String tradetype = "JSAPI";//支付方式--网页内支付
        String tradeamt = "1";//交易金额--分
        String body = "商品";//商品描述
        String notifyurl = "www.baidu.com";//客户端吧支付成功通知的地址
        String returnurl = "";//银联必填
        String custrmerip = "";//银联必填
        Map<String , String> param = new HashMap<String, String>();
        param.put("merid" , merid);
        param.put("channelid" , channelid);
        param.put("termid" , termid);
        param.put("tradetrace" , tradetrace);
        param.put("opt" , opt);
        param.put("tradetype" , tradetype);
        param.put("tradeamt" , tradeamt);
        param.put("body" , body);
        param.put("notifyurl" , notifyurl);
        param.put("returnurl" , returnurl);
        param.put("custrmrrip" , custrmerip);
        String sign = SignUtil.getSign(param , signkey);
        param.put("sign" , sign);
        try {
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            //验签
            SignUtil.checksign(result.body() , signkey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/

}
