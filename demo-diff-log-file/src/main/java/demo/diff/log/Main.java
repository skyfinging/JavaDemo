package demo.diff.log;

import lombok.extern.log4j.Log4j2;

/**
 * 直接使用 gradle shadowJar 打包即可打包出可运行的jar
 */
@Log4j2
public class Main {
    public static void main(String[] args) {
        log.info("I will print to log 1", 1);
        log.info("I will print to log 2", 2);
    }
}
