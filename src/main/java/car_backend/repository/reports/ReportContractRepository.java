package car_backend.repository.reports;

import car_backend.model.reports.ContractReport;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportContractRepository extends JpaRepository<ContractReport, String> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE PHI_T_REPORT_CONTRACT_REPOSITORY_FINAL", nativeQuery = true)
    void truncateReportTable();

    @Modifying
    @Transactional
    @Query(value = "SELECT", nativeQuery = true)
    void insertReportTable();


    @Modifying
    @Transactional
    @Query(value = "CALL P_REPORT_CONTRACT_REPOSITORY.MAIN_CONTRACT_REPOSITORY()", nativeQuery = true)
    void executeContractRepositoryReport();

}
