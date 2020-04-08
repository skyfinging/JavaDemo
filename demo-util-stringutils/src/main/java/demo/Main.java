package demo;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 测试StringUtils工具类
 */
public class Main {
    public static void main(String[] args) {
        String str = "  ";
        String str1 = "";
        //isBlank 不需要trim即可判断
        System.out.println("isBlank:"+StringUtils.isBlank(str));
        //isEmpty 需要trim才能判断
        System.out.println("isEmpty:"+StringUtils.isEmpty(str));
        System.out.println("isEmpty:"+StringUtils.isEmpty(str.trim()));

        //字符串相等判断
        System.out.println();
        String str2 = "abc";
        String str3 = new StringBuffer("a").append("b").append("c").toString();
        System.out.println("str2==str3:"+(str2==str3));
        System.out.println("str2 equals str3:"+(str2.equals(str3)));
        System.out.println("str2 equals str3:"+StringUtils.equals(str2,str3));
        System.out.println("str2 equals null:"+StringUtils.equals(str2,null));
        System.out.println("null equals null:"+StringUtils.equals(null,null));

        //字符串拼接
        System.out.println();
        String line = StringUtils.join(new String[]{str2,str3},",",0,2);
        System.out.println(line);

        //字符串分割
        String[] splits = StringUtils.split(line,",");
        List<String> list = Arrays.asList(splits);
        list.forEach(System.out::println);

        //替换字符串，不支持正则表达式
        String newLine = StringUtils.replace(line,"a","1",2);
        System.out.println(newLine);

        //首字母大写
        String cLine = org.apache.commons.lang3.StringUtils.capitalize("abc def");
        System.out.println(cLine);

        //是否包含非空白字符
        System.out.println(org.springframework.util.StringUtils.hasText("abc"));

        Collection<String> collection = new HashSet<>();
        collection.add("中文");
        collection.add("abc,cde");
        System.out.println(org.springframework.util.StringUtils.collectionToDelimitedString(collection,","));
    }
}
