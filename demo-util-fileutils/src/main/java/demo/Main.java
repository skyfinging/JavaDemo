package demo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * 测试FileUtils工具类
 */
public class Main {
    public static void main(String[] args) {
        //FileUtils.writeStringToFile 把字符串写入到文件中，如果文件不存在，则创建
        testWriteStringToFile();
        //FileUtils.deleteDirectory 删除文件夹
        testDeleteDirectory();
        //FileUtils.copyFileToDirectory 拷贝文件到另一个路径
        testCopyFile();
        //创建目录
        testForceMkdir();
        //列出目录下的文件
        testListFiles();
        //拷贝文件夹，包括文件夹中的文件
        testCopyDirectory();
        //删除文件或文件夹
        testForceDelete();
    }

    static void testForceDelete(){
        try {
            FileUtils.forceDelete(new File("copyDist"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testListFiles(){
        //最后一个参数代表要不要搜索子文件夹
        System.out.println("搜索文件夹中的txt和log文件，包含子文件夹：");
        Collection<File> fileList = FileUtils.listFiles(new File("dist/dist4"),new String[]{"txt","log"},true);
        fileList.stream().forEach(System.out::println);

        System.out.println("搜索文件夹中的txt和log文件，不包含子文件夹：");
        fileList = FileUtils.listFiles(new File("dist/dist4"),new String[]{"txt","log"},false);
        fileList.stream().forEach(System.out::println);
    }

    static void testCopyDirectory(){
        try {
            FileUtils.copyDirectory(new File("dist"),new File("copyDist"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testForceMkdir(){
        try {
            FileUtils.forceMkdir(new File("dist"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testCopyFile(){
        try {
            FileUtils.copyFileToDirectory(new File("dist/dist2/tmp.txt"),new File("dist/dist4"));
            FileUtils.copyFile(new File("dist/dist2/tmp.txt"),new File("dist/dist4/dst/otherName.log"));
            FileUtils.copyFile(new File("dist/dist2/tmp.txt"),new File("dist/dist4/dst/otherName.txt.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testWriteStringToFile(){
        try {
            FileUtils.writeStringToFile(new File("dist/dist2/tmp.txt"),"测试内容","UTF-8");
            FileUtils.write(new File("dist/dist2/tmp.txt"),"追加内容","UTF-8",true);
            FileUtils.writeStringToFile(new File("dist/dist3/tmp.txt"),"测试内容","UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void testDeleteDirectory(){
        try {
            FileUtils.deleteDirectory(new File("dist/dist3"));
            FileUtils.deleteQuietly(new File("dist/dist3"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
