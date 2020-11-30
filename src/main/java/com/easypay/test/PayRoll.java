package com.easypay.test;

import java.util.HashMap;
import java.util.Map;


import org.jsoup.Connection.Response;
/**
 * @Package: com.easypay.test
 * @ClassName: PayRoll
 * @Datetime: 2020/1/16   9:34
 * @Description:
 * @Author: liyongguang
 */
public class PayRoll {

    private static final String channelid = "";//渠道编号
    private static final String channelkey = "";//渠道密钥
    private static final String merid = "";//商户编号
    private static final String termid = "";//终端编号
    private static final String signKey = "";//签名密钥
    private static final String url = "";//请求地址

    public static void main(String[] args) {
        //TODO
    }

    //获取签名
    public static void test1(){
        Map<String , String> param = new HashMap<String, String>();
        param.put("channelid" , channelid);
        param.put("opt" , "getSign");
        String sign = SignUtil.getSign(param , channelkey);
        param.put("sign" , sign);
        try {
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println(result.body());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //代付提现
    public static void method1(){
        Map<String , String> param = new HashMap<String, String>();
        param.put("channelid" , channelid);
        param.put("merid" , merid);
        param.put("termid" , termid);
        param.put("opt" , "remitPay");//操作类型
        param.put("tOflag" , "0");//是否T0到账标志位，0 = 实时到账 ， 1 = T1到账；空值默认T1
        param.put("tradetrace" , "");//交易订单号
        param.put("tradeame" , "");//交易金额
        param.put("account" , "");//入账账户
        param.put("accountname" , "");//账户名称
        String sign = SignUtil.getSign(param , signKey);
        System.out.println(sign);
        param.put("sign" , sign);
        try {
            System.out.println("请求报文==" + param.toString());
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println("返回数据==" + result.body());
            //验签
            SignUtil.checksign(result.body() , signKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //代付确认接口
    public static void method2(){
        Map<String , String> param = new HashMap<String, String>();
        param.put("channelid" , channelid);
        param.put("merid" , merid);
        param.put("termid" , termid);
        param.put("opt" , "remitQuery");
        param.put("tradetrace" , "");//查询订单号
        param.put("orgtradetrace" , "");//需要确认的原交易订单号
        param.put("tradeamt" , "1");// --分
        param.put("account" , "");//入账账户
        String sign = SignUtil.getSign(param , signKey);
        param.put("sign" , sign);
        try {
            System.out.println("请求参数==" + param.toString());
            Response result = HttpUtils.post(url , param , HttpUtils.FORM_TYPE);
            System.out.println("返回数据==" + result.body());

            //验签
            SignUtil.checksign(result.body() , signKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //TODO

}
