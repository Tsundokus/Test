package com.easypay.test;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Connection.Response;

public class Pay {
    private static String channelid = "D01000000010001";// 渠道编号
    private static String merid = "880210010215072";// 商户编号
    private static String termid = "10005998";// 终端编号
    private static String channelkey = "d5riddkald4k4did";// 渠道密钥
    private static String signkey = "d8fkld5d3kirs5k1d85df2508lo2k40d";// 签名密钥
    private static String url = "https://notify-test.eycard.cn:7443/WorthTech_Access_AppPaySystemV2/apppayacc";// 请求地址


    public static void main(String[] args) {
//        method1();// 获取签名密钥
//		method2();// 主扫
//		method3();// jsapi
//		method4();// 被扫
//		method5();// 订单查询
//		method6();// 提交被扫支付接口
//		method7();// 单笔交易查询接口
    }

    // 请求签名参数接口测试
    public static void method1() {
        String opt = "getSign";//操作类型
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("opt", opt);
        String sign = SignUtil.getSign(param, channelkey);
        System.out.println("加密后====>>"+sign);
        param.put("sign", sign);
        System.out.println("param====>>"+param);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 统一下单接口(NATIVE,主扫)
    public static void method2() {
        String opt = "apPreOrder";// 支付宝
//		String opt = "upPreOrder";// 银联
        String tradetype = "NATIVE";//支付方式
        String tradetrace = "";//商户订单号
        String tradeamt = "";// 金额--分
        String body = "";//商品/支付简要描述
        String notifyurl = "";//客户端支付成功通知的地址
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", signkey);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetype", tradetype);
        param.put("tradetrace", tradetrace);
        param.put("tradeamt", tradeamt);
        param.put("body", body);
        param.put("notifyurl", notifyurl);
        String sign = SignUtil.getSign(param, signkey);
        param.put("sign", sign);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 统一下单接口(JSAPI)
    public static void method3() {
//		String opt = "wxPreOrder";// 微信
      String opt = "apPreOrder";// 支付宝
//		String opt = "upPreOrder";// 银联
        String tradetype = "JSAPI";//操作类型
        String tradetrace = UUID.randomUUID().toString().replace("-", "");
        String tradeamt = "1";//交易金额
        String body = "JSAPI统一单接口";//商品/支付简要描述
        String notifyurl = "https://www.baidu.com";//客户端支付成功通知的地址
        String returnurl = "https://www.baidu.com";// 银联必填，微信支付宝不填
        String customerip = "127.0.0.1";// 银联必填，微信支付宝不填

        String openid = "2088912121113355";// 用户标识--支付宝
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetype", tradetype);
        param.put("tradetrace", tradetrace);
        param.put("tradeamt", tradeamt);
        param.put("body", body);
        param.put("notifyurl", notifyurl);
        param.put("openid", openid);
        if ("upPreOrder".equals(opt)) {
            param.put("returnurl", returnurl);
            param.put("customerip", customerip);
        }
        String sign = SignUtil.getSign(param, signkey);
        System.out.println("MD5后====>>"+sign);
        param.put("sign", sign);
        System.out.println("param====>>"+param);
        if (opt.equals("upPreOrder")) {
            param.put("returnurl", returnurl);
            param.put("customerip", customerip);
        }
        System.out.println(param);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println("====>>"+result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 提交被扫支付接口
    public static void method4() {
        String opt = "scanPay";//操作类型
        String tradetrace = "";//商户订单号
        String tradeamt = "";//交易金额
        String authcode = "";// 付款码
        String body = "";//商品/支付简要描述
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetrace", tradetrace);
        param.put("tradeamt", tradeamt);
        param.put("authcode", authcode);
        param.put("body", body);
        String sign = SignUtil.getSign(param, signkey);
        System.out.println(sign);
        param.put("sign", sign);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 单笔交易查询接口
    public static void method5() {
        String opt = "tradeQuery";//操作类型
        String tradetrace = "";// 原交易订单号
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetrace", tradetrace);
        String sign = SignUtil.getSign(param, signkey);
        System.out.println(sign);
        param.put("sign", sign);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 交易撤销接口
    public static void method6() {
        String opt = "cancel";//操作类型
        String tradetrace = "";//商户订单号
        String oritradetrace = "";// 原交易订单号
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetrace", tradetrace);
        param.put("oritradetrace", oritradetrace);
        String sign = SignUtil.getSign(param, signkey);
        System.out.println(sign);
        param.put("sign", sign);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 交易退货接口
    public static void method7() {
        String opt = "zwrefund";//操作类型
        String tradetrace = "";//商户订单号
        String oriwtorderid = "";// 原交易易生订单号wtorderid
        String tradeamt = "1";//交易金额
        Map<String, String> param = new HashMap<String, String>();
        param.put("channelid", channelid);
        param.put("merid", merid);
        param.put("termid", termid);
        param.put("opt", opt);
        param.put("tradetrace", tradetrace);
        param.put("oriwtorderid", oriwtorderid);
        param.put("tradeamt", tradeamt);
        String sign = SignUtil.getSign(param, signkey);
        System.out.println(sign);
        param.put("sign", sign);
        try {
            Response result = HttpUtils.post(url, param, HttpUtils.FORM_TYPE);
            System.out.println(result.body());
            // 验签
            SignUtil.checksign(result.body(), signkey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
