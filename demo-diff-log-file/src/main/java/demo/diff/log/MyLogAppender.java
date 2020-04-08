package demo.diff.log;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;


@Plugin(name = "MyLogAppender", category = "Core", elementType = "appender", printObject = true)
public class MyLogAppender extends AbstractAppender {

    private String fileName;
    private Set<String> fileCache;

    public MyLogAppender(Appender appender){
        super(null,null,null);
    }


    public MyLogAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, String fileName) {
        super(name, filter, layout, ignoreExceptions);
        this.fileName = fileName;
        fileCache = new HashSet<>();
    }

    @Override
    public void append(LogEvent event) {
        //控制我们的输出，从输出中获取参数来判断往哪个文件中写日志
        if (event.getSource().getClassName().equals("demo.diff.log.Main")) {
            Object[] parameters = event.getMessage().getParameters();
            if (parameters != null && parameters.length > 0) {
                String index = parameters[0] + "";
                final byte[] bytes = getLayout().toByteArray(event);
                writerFile(index, bytes);
            }
        }

    }
    /* 接收配置文件中的参数 */
    @PluginFactory
    public static MyLogAppender createAppender(@PluginAttribute("name") String name,
                                               @PluginAttribute("fileName") String fileName,
                                               @PluginElement("Filter") final Filter filter,
                                               @PluginElement("Layout") Layout<? extends Serializable> layout,
                                               @PluginAttribute("ignoreExceptions") boolean ignoreExceptions) {
        if (name == null) {
            LOGGER.error("no name defined in conf.");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        if (!createFile(fileName)) {
            return null;
        }
        return new MyLogAppender(name, filter, layout, ignoreExceptions, fileName);
    }

    private static boolean createFile(String fileName) {
        Path filePath = Paths.get(fileName);
        Path parentPath = filePath.getParent();
        try {
            if (Files.exists(filePath)) {
                return true;
            }else {
                Files.createDirectories(parentPath);
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            LOGGER.error("create file exception", e);
            return false;
        }
        return true;
    }

    //根据index，将日志写入到不同的文件
    private void writerFile(String index, byte[] log) {
        String newName = fileName.replace("${index}",index);
        if(fileCache.contains(newName)==false){
            //第一次往newName文件中写日志，需要先生成这个文件
            createFile(newName);
            fileCache.add(newName);
        }
        try {
            Files.write(Paths.get(newName), log, StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.error("write file exception", e);
        }
    }

}
