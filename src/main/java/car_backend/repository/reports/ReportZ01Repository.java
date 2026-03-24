package car_backend.repository.reports;

import car_backend.model.reports.ReportZ01;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReportZ01Repository extends JpaRepository<ReportZ01, String> {

    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_Z01.MAIN_Z01()", nativeQuery = true)
    void executeReportZ01();
}
