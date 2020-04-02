import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static Logger logger = LogManager.getLogger(Main.class);
    static Logger logger2 = LogManager.getLogger("COMBAUSERINFO");
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while(true){
            Thread.sleep(2);
            logger.debug("DEBUG Message "+i);   //不会打印
            logger.info("INFO Message "+i);     //打印在 INFO.log
            logger.warn("WARN Message "+i);     //打印在 WARN.log
            logger.error("ERROR Message "+i);     //打印在 ERROR.log
            if((i++)%100==0) {
                System.out.println("i="+i);
                // 以下日志全部打印在 USERINFOFILE.log，同时每个等级会打印在对应的INFO.log 或 WARN.log 或 ERROR.log，并在控制台中打印
                logger2.debug("User debug Message "+i);
                logger2.info("User Info Message "+i);
                logger2.warn("User warn Message "+i);
                logger2.error("User error Message "+i);
            }
        }
    }
}
