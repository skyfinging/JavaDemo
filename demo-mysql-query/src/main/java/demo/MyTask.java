package demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MyTask {

    @Autowired
    private ExcelHeadRepository excelHeadRepository;

    @Scheduled(fixedDelay = 10000)
    public void query(){
        log.info("doQuery");
        List<?> result = excelHeadRepository.findDirSizeInByte();
        log.info(result.get(0));
    }
}
