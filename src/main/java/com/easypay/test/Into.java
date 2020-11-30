package com.easypay.test;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Package: com.easypay.test
 * @ClassName: Into
 * @Datetime: 2020/1/16   10:21
 * @Description:
 * @Author: liyongguang
 */
public class Into {

    private static String channelid = "616161620011610";// 渠道编号
    private static String merid = "MER021320011610";// 商户编号
    private static String termid = "TE011610";// 终端编号
    private static String channelkey = "351x5xx69v28xr";// 渠道密钥
    private static String signKey = "veeua8wqtqcuwrxsg3dabrgmsgtdzn5i";// 签名密钥
    private static String url = "https://notify-test.eycard.cn:7443/EasypayMPSystem/";

    public static void main(String[] args) throws IOException {
        method1();
    }

    public static void method1() throws IOException {
        String channelId = channelid;
        String merId = merid;
        String termId = termid;
        String version = "1.0";
        Map<String , String> paramMap = new HashMap<String, String>();
        paramMap.put("channelId" , channelId);
        paramMap.put("termId" , termId);
        paramMap.put("merId" , merId);
        paramMap.put("version" , "1.0");

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost("https://notify-test.eycard.cn:7443/EasypayMPSystem/FileUploadServet");
        FileBody fileBody = new FileBody(new File("D://img/img01.jpg"));
        MultipartEntityBuilder create = MultipartEntityBuilder.create();
        create.addPart("file" , fileBody);
        create.addTextBody("channelId" , channelId);
        create.addTextBody("merId" , merId);
        create.addTextBody("termId" , termId);
        create.addTextBody("version" , "1.0");
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
