package demo;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, DocumentException, KeyManagementException, NoSuchAlgorithmException {
        HttpClient httpClient = getHttpClient();
        HttpPost httppost = new HttpPost( "https://127.0.0.1/sso?method=qryUserByTicket");

        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("ticket", "123456789"));
        formparams.add(new BasicNameValuePair("clientIp", "127.0.0.1"));
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);

        System.out.println(httppost);
        HttpResponse response = httpClient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String xml = EntityUtils.toString(entity);
            System.out.println(xml);
            Document doc = DocumentHelper.parseText(xml);
            Element ele = doc.getRootElement();
            String result = ele.elementText("RESULT");
            String result_msg = ele.elementText("RESULT_MSG");
            String result_msgcode = ele.elementText("RESULT_MSGCODE");
            String account = ele.elementText("ACCOUNT");
            String ticket_str = ele.elementText("TICKET");
            System.out.println(result);
            System.out.println(result_msg);
            System.out.println(result_msgcode);
            System.out.println(account);
            System.out.println(ticket_str);
        }

    }

    public static HttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext = getSSLContext();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        HttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        return httpClient;
    }

    public static SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = null;
        sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }
}
