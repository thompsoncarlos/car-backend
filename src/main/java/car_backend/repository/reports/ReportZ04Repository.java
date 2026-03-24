package car_backend.repository.reports;

import car_backend.model.reports.ReportZ04;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportZ04Repository extends JpaRepository<ReportZ04, String> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_Z04", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Transactional
    @Query(value = "SELECT", nativeQuery = true)
    void insertReportTable();

    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_Z04.MAIN_Z04()", nativeQuery = true)
    void executeReportZ04();

}
