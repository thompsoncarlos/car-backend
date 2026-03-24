package car_backend.repository.reports;

import car_backend.model.reports.ServiceCatalogueReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportServiceCatalogueRepository extends JpaRepository<ServiceCatalogueReport, String> {

        @Modifying
        @Transactional
        @Query(value = "TRUNCATE TABLE PHI_T_REPORT_SERVICE_CATALOGUE", nativeQuery = true)
        void truncateReportTable();

        @Modifying
        @Transactional
        @Query(value = "INSERT", nativeQuery = true)
        void insertReportTable();

        @Modifying
        @Transactional
        @Query(value = "CALL P_REPORT_SERVICE_CATALOGUE.MAIN_SERVICE_CATALOGUE()", nativeQuery = true)
        void executeServiceCatalogueReport();
}
