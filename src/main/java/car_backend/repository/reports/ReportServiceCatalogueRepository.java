cpackage car_backend.repository.reports;

import car_backend.model.reports.ReportServiceCatalogue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ReportServiceCatalogueRepository extends JpaRepository<ReportServiceCatalogue, String> {

    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_SERVICE_CATALOGUE.TRUNCATE_SERVICE_CATALOGUE()", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_SERVICE_CATALOGUE.INSERT_INFO_REPORT_SERVICE_CATALOGUE()", nativeQuery = true)
    void insertReportTable();
}
