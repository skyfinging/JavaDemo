package demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExcelHeadRepository extends JpaRepository<ExcelHead, Integer>, JpaSpecificationExecutor<ExcelHead> {

    @Query("select max(id) from ExcelHead")
    Integer getMaxId();

    @Query(value="SELECT CONCAT(ROUND(SUM(data_length),2)) AS DATA FROM information_schema.TABLES",nativeQuery=true)
    List<?> findDirSizeInByte();
}
