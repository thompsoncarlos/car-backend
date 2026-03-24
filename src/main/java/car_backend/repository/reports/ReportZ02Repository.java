package car_backend.repository.reports;

import car_backend.model.reports.ReportZ02;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReportZ02Repository extends JpaRepository<ReportZ02, String> {

    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_Z02.MAIN_Z02()", nativeQuery = true)
    void executeReportZ02();
}
