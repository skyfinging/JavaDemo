package demo;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 测试org.apache.commons.io.IOUtils工具类
 */
public class Main {
    public static void main(String[] args) {
        //IOUtils.toString 把输入流中的内容转换成字符串
        testToString();
        //IOUtils.copy 把输入流拷贝到输出流
        testCopy();
        //IOUtils.toByteArray  把输入流转换成byte数组
        testToByteArray();
        //IOUtils.write 把字符写入输出流
        testWrite();
        //IOUtils.toInputStream 把字符串变成输入流
        testToInputStream();
        //从输入流中读取多行数据
        testReadLines();
        //从输入流中返回一个行迭代器
        testLineIterator();
    }

    static void testLineIterator(){
        System.out.println("testLineIterator");
        try(
                InputStream inputStream = new FileInputStream("src/main/resources/tmp.txt")
        ){
            Iterator<String> iterator = IOUtils.lineIterator(inputStream,"UTF-8");
            iterator.forEachRemaining(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testReadLines(){
        System.out.println("testReadLines");
        try(
                InputStream inputStream = new FileInputStream("src/main/resources/tmp.txt")
        ){
            List<String> lines = IOUtils.readLines(inputStream,"UTF-8");
            lines.forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testToInputStream(){
        try(
                InputStream inputStream = IOUtils.toInputStream("中文","UTF-8");
                OutputStream outputStream = new FileOutputStream("dist/testToInputStream.txt")
        ){
            IOUtils.copy(inputStream, outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testWrite(){
        try(
                OutputStream outputStream = new FileOutputStream("dist/testWrite.txt")
        ){
            IOUtils.write("123456中文",outputStream,"UTF-8");
            IOUtils.write("123456中文",outputStream,"GBK");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testToByteArray(){
        try(
                InputStream inputStream = new FileInputStream("src/main/resources/tmp.txt")
        ){
           byte[] data = IOUtils.toByteArray(inputStream);
            System.out.println(new String(data,"UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testCopy(){
        try(
                InputStream inputStream = new FileInputStream("src/main/resources/tmp.txt");
                OutputStream outputStream = new FileOutputStream("dist/testCopy.txt")
        ){
            //复制内容大小限制在2G
            IOUtils.copy(inputStream,outputStream);
            //复制内容大小可以超过2G
//            IOUtils.copyLarge(inputStream,outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static void testToString(){
        System.out.println("testToString");
        try(
            InputStream inputStream = new FileInputStream("src/main/resources/tmp.txt")
        ){
            System.out.println(IOUtils.toString(inputStream,"UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
