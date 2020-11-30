package com.easypay.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection.Response;

public class Authentication {

	private static String channelid = "616161620011610";// 渠道编号
	private static String merid = "MER021320011610";// 商户编号
	private static String termid = "TE011610";// 终端编号
	private static String channelkey = "351x5xx69v28xr";// 渠道密钥
	private static String signKey = "x2vp67scaiawxqwwfdjsto9szjvcg936";// 签名密钥
	private static String url = "https://notify-test.eycard.cn:7443/EasypayMPSystem/";

	public static void main(String[] args) throws Exception {
//		test1();//获取签名
		method1();// 银行卡信息鉴权
// 		method2();// 运营商信息鉴权
//		method3();// 公安信息鉴权
//		method4();// 鉴权信息查询
//		method5();// 风险信息鉴权
//		method6();// 短信通知类接口
//		method7();// 企业信息查询
//		method9();// 企业信息鉴权
//		method10();//文件上传
	}

	//获取签名
	public static void test1() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("channelId", channelid);
		param.put("version" , "3.0");
		param.put("tradeTrace" , "aqaqa12231qqq");
		String sign = SignUtil.getSign(param, channelkey);
		System.out.println("sign" + sign);
		param.put("sign" , sign);
		try {
			Response result = HttpUtils.post(url + "/SignServet", param, HttpUtils.FORM_TYPE);
			System.out.println(result.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 银行卡信息鉴权
	public static void method1() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "aqaqa12231qqaaq";//商户订单号
		String accNo = "6212261717004371124";// 银行卡号
		String accName = "李永光";// 姓名
		String certNo = "412702199611270019";// 身份证
		String version = "3.0";
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("accNo", accNo);
		param.put("accName", accName);
		param.put("certNo", certNo);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);
		param.put("sign", sign);
		try {
			System.out.println("银行卡信息鉴权发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/VerifyBankServet", param, HttpUtils.FORM_TYPE);
			System.out.println("银行卡信息鉴权返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通讯运营商信息鉴权
	public static void method2() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "1qu1u1u11u111";//
		String accName = "李永光";// 姓名
		String mobileNo = "15294962105";// 手机号
		String certNo = "412702199611270019";// 身份证
		String certType = "01";//证件类型 -- 大陆居民身份证：01
		String version = "3.0";
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("accName", accName);
		param.put("mobileNo", mobileNo);
		param.put("certNo", certNo);
		param.put("certType", certType);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);
		param.put("sign", sign);
		try {
			System.out.println("通讯运营商信息鉴权发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/VerifyOperatorServet", param, HttpUtils.FORM_TYPE);
			System.out.println("通讯运营商信息鉴权返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 公安信息验证
	public static void method3() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "";//商户订单号
		String accName = "";// 姓名
		String certNo = "";// 身份证
		String certType = "";//证件类型 -- 大陆居民身份证身份证：01
		String version = "3.0";//版本号
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("accName", accName);
		param.put("certNo", certNo);
		param.put("certType", certType);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);

		param.put("sign", sign);
		try {
			System.out.println("公安信息验证发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/VerifyPoliceServet", param, HttpUtils.FORM_TYPE);
			System.out.println("公安信息验证返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 鉴权信息查询
	public static void method4() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String merorderId = "";// 原鉴权易生流水号
		String version = "3.0";//版本号
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("merorderId", merorderId);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);

		param.put("sign", sign);
		try {
			System.out.println("鉴权信息查询发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/VerifyQueryServet", param, HttpUtils.FORM_TYPE);
			System.out.println("鉴权信息查询返回数据===》" + result.body());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 风险信息鉴权
	public static void method5() {
		/**
		 * verifyRiskIdcard -- 身份证号
		 * verifyRiskBankcard -- 银行卡号
		 * verifyRiskBusinessLicense -- 营业执照号
		 */
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String opt = "verifyRiskIdcard";
		String tradeTrace = "";
		String riskNumber = "";// 身份证
		String version = "3.0";
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("opt", opt);
		param.put("tradeTrace", tradeTrace);
		param.put("riskNumber", riskNumber);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);
		param.put("sign", sign);
		try {
			System.out.println("风险信息鉴权发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/QueryRiskServet", param, HttpUtils.FORM_TYPE);
			System.out.println("风险信息鉴权返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 短信通知类接口
	public static void method6() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "s6s7s8s9s10s1s2s3s4s5";//商户订单号
		// 需提供短信模板进行配置（模板变量用#替换，content传变量值，按顺序传中间用英文分号隔开）
		String content = "测试";//短信通知内容
		String mobileNo = "15294962105";//短信通知手机号
		String version = "1.0";
		//String smsTemplateId = "";
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("content", content);
		param.put("mobileNo", mobileNo);
		param.put("version", version);
		//param.put("smsTemplateId", smsTemplateId);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);
		param.put("sign", sign);
		try {
			System.out.println("短信通知类接口发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/SendingSMSServet", param, HttpUtils.FORM_TYPE);
			System.out.println("短信通知类接口返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 企业信息查询接口
	public static String method7() throws Exception {
		/**
		 * 企业信息值类型：
		 * 		01 -- 企业注册号
		 * 		02 -- 企业社会信用代码
		 * 		03 -- 企业名称
		 */
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "";//商户订单号
		String version = "1.0";
		String optType = "queryBasic";//操作类型
		String queryValueType = "";//企业信息值类型
		String queryValue = "";//企业信息值
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("version", version);
		param.put("optType", optType);
		param.put("queryValueType", queryValueType);
		param.put("queryValue", queryValue);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);

		param.put("sign", sign);
		String result = "";
		System.out.println("企业信息查询接口发送参数===》" + param.toString());
		Response response = HttpUtils.post(url + "/QueryEntInfos", param, HttpUtils.FORM_TYPE);
		result = response.body();
		System.out.println("企业信息查询接口返回数据===》" + result);
		// 验签
		SignUtil.checksign(result, signKey);
		return result;
	}

	// 企业信息鉴权接口
	public static void method9() {
		Map<String, String> param = new HashMap<String, String>();
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String tradeTrace = "";//商户订单号
		String version = "1.0";//版本号
		String entValueType = "";//企业信息值类型
		String entValue = "";// 企业信息值
		String verifyValueType = "";//验证值类型
		String verifyValue = "";// 验证值
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("tradeTrace", tradeTrace);
		param.put("version", version);
		param.put("entValueType", entValueType);
		param.put("entValue", entValue);
		param.put("verifyValueType", verifyValueType);
		param.put("verifyValue", verifyValue);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密sign===》" + sign);

		param.put("sign", sign);
		try {
			System.out.println("企业信息鉴权接口发送参数===》" + param.toString());
			Response result = HttpUtils.post(url + "/VerifyEntInfos", param, HttpUtils.FORM_TYPE);
			System.out.println("企业信息鉴权接口返回数据===》" + result.body());
			// 验签
			SignUtil.checksign(result.body(), signKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//文件上传
	public static void method10() throws IOException {
		String channelId = channelid;
		String merId = merid;
		String termId = termid;
		String version = "1.0";
		String nonceStr = UUID.randomUUID().toString().replace("-" , "");//32位随机字符串
		Map<String, String> param = new HashMap<String, String>();
		param.put("channelId", channelId);
		param.put("merId", merId);
		param.put("termId", termId);
		param.put("nonceStr", nonceStr);
		param.put("version", version);
		String sign = SignUtil.getSign(param, signKey);
		System.out.println("MD5加密==" + sign);

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost("https://notify-test.eycard.cn:7443/EasypayMPSystem/FileUploadServet");
		FileBody fileBody = new FileBody(new File("D://img/img01.jpg"));
		MultipartEntityBuilder create = MultipartEntityBuilder.create();
		create.addPart("file" , fileBody);
		create.addTextBody("channelId" , channelId);
		create.addTextBody("merId" , merId);
		create.addTextBody("termId" , termId);
		create.addTextBody("nonceStr" , nonceStr);
		create.addTextBody("version" , "1.0");
		create.addTextBody("sign" , sign);
		httpPost.setEntity(create.build());
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000)
				.setConnectTimeout(10000).build();
		httpPost.setConfig(requestConfig);
		httpClient = HttpClients.createDefault();
		response = httpClient.execute(httpPost);
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
			System.out.println(result);
		}
		httpClient.close();
	}
}
