/**  
 * All rights Reserved, Designed By www.tydic.com
 * @Title: SignUtil.java   
 * @Package: com.example.down   
 * @Description: 
 * @author: wangtao 
 * @date: 2019年10月15日 下午3:06:54
 */
package com.easypay.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;


/**
 * @ClassName: SignUtil
 * @Description:
 * @author: wangtao
 * @date: 2019年10月15日 下午3:06:54
 */
public class SignUtil {
	// 获取sign
	public static String getSign(Map<String, String> map, String key) {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!StringUtil.isBlank(entry.getValue())) {
				list.add(entry.getKey() + "=" + entry.getValue());// key=value
			}
		}
		Collections.sort(list);
		String str2 = "";
		for (int i = 0; i < list.size(); i++) {
			str2 += list.get(i) + "&";
		}
		str2 += "key=" + key;
		System.out.println("MD5前====>>"+str2);
		str2 = MD5(str2);
		return str2;
	}

	// md5加密
	public static String MD5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result.toUpperCase();
	}

	//验签
	public static void checksign(String str, String key) {
		JSONObject json = JSON.parseObject(str);
		if (json.containsKey("sign")) {
			String returnsign = json.getString("sign");
			json.remove("sign");
			String checksign = getSign((Map) json, key);
			if (StringUtils.isNotBlank(returnsign) && returnsign.equals(checksign)) {
				System.out.println("验签成功");
			} else {
				System.out.println("验签失败");
			}
		}
	}

	/*public static void main(String[] args) {
		String str = "/C:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WorthTech_PosStockMgmt/WEB-INF/classes/cn/com/worthtech/posstockmgmt/servlet/posstockmgmt.cfg.xml";
		System.out.println(str.substring(1));
	}*/

}
