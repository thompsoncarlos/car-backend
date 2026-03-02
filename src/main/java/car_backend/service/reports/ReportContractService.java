package car_backend.service.reports;

import car_backend.model.reports.ContractReport;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportContractService {

    List<ContractReport> getReport();

    byte[] generateContractReportExcel();

}