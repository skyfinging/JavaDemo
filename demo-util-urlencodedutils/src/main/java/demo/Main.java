package demo;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        NameValuePair nameValuePair = new BasicNameValuePair("name","小明");
        NameValuePair nameValuePair1 = new BasicNameValuePair("age","1 0");
        List<NameValuePair> list = new ArrayList<>();
        list.add(nameValuePair);
        list.add(nameValuePair1);
        testFormat(list);

        testParse();
    }

    static void testParse(){
        String url = "https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247485460&idx=1&sn=cef982d688a5b0ef5e42a4301961f54b&chksm=eb538d22dc240434251b32f924cd44b7ac68c041dbe84d264c2ea0156902c7d2c816581b65da&scene=21#wechat_redirect";
        URI uri = URI.create(url);
        List<NameValuePair> list = URLEncodedUtils.parse(uri, Charset.defaultCharset());
        list.forEach(System.out::println);
    }

    static void testFormat(List<NameValuePair> list){
        System.out.println(" 格式化 "+ URLEncodedUtils.format(list,"UTF-8"));
    }
}
