package demo;

import lombok.extern.log4j.Log4j2;
import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import net.percederberg.mibble.MibLoaderLog;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        String fileName = "E:\\JAVA\\workspace\\svn\\omcsrc\\网管业务中心项目配置库\\无线覆盖综合网管\\工程\\公共模块\\北向接口\\实现\\SourceCodes\\project\\NorthAlarmForward\\resources\\CombaOmsSMI_Indonesia_snmpV1.mib";
        try {
            Mib mib = new MibLoader().load(new File(fileName));
            System.out.println("mib库文件 "+fileName+" 没有错误");
        } catch (IOException e) {
            System.out.println("无法读取文件 "+fileName);
        } catch (MibLoaderException e) {
            System.out.println(e.getMessage());
            Iterator iterator = e.getLog().entries();
            while(iterator.hasNext()){
                MibLoaderLog.LogEntry logEntry = (MibLoaderLog.LogEntry) iterator.next();
                System.out.println(logEntry.getMessage());
            }
        }
    }

}
