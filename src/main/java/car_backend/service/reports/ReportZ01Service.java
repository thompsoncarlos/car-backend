package car_backend.service.reports;

import car_backend.model.reports.ContractReport;
import car_backend.model.reports.ReportZ01;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportZ01Service {

    List<ReportZ01> getReport();

    byte[] generateZ01ReportExcel();

}
