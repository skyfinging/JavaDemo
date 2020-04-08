package demo;

import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 测试FilenameUtils
 */
public class Main {
    public static void main(String[] args) {
        String[] filenames = new String[]{"d:\\路径1//路径 2\\abc.txt",".\\home\\..\\home\\abc.txt.log","./abc"};
        Consumer<String>[] consumers = new Consumer[7];
        consumers[0] = Main::testGetExtension;
        consumers[1] = Main::testGetBaseName;
        consumers[2] = Main::testGetName;
        consumers[3] = Main::testRemoveExtension;
        consumers[4] = Main::testNormalize;
        consumers[5] = Main::testSeperatorToUnix;
        consumers[6] = Main::testGetFullPath;

        testConcat();

        testFilenameUtils(filenames, consumers);
    }

    static void testFilenameUtils(String[] filenames, Consumer<String>[] consumers){
        List<String> list = Arrays.asList(filenames);
        Arrays.asList(consumers).forEach(consumer->doConsumer(consumer,list));
    }

    static <T> void doConsumer(Consumer<T> consumer, List<T> list){
        System.out.println();
        System.out.println("test:"+consumer.toString());
        list.forEach(consumer);
    }

    static void testGetFullPath(String filename){
        System.out.println(filename+" 获取文件路径 "+FilenameUtils.getFullPath(filename));
    }

    static void testSeperatorToUnix(String filename){
        System.out.println(filename+" 路径分隔符改成unix系统 "+FilenameUtils.separatorsToUnix(filename));
    }

    static void testNormalize(String filename){
        System.out.println(filename+" 路径正常化 "+FilenameUtils.normalize(filename));
    }

    static void testRemoveExtension(String filename){
        System.out.println(filename+" 去掉后缀名 "+FilenameUtils.removeExtension(filename));
    }

    static void testConcat(){
        System.out.println(FilenameUtils.concat("d:\\路径1\\路径 2","文件名称.txt"));
        System.out.println(FilenameUtils.concat("/foo/c.txt","bar"));
    }

    static void testGetExtension(String filename){
        System.out.println(filename+" 后缀名 "+FilenameUtils.getExtension(filename));
    }

    static void testGetBaseName(String  filename){
        System.out.println(filename+" 文件名 "+FilenameUtils.getBaseName(filename));
    }

    static void testGetName(String filename){
        System.out.println(filename+" getName "+FilenameUtils.getName(filename));
    }
}
