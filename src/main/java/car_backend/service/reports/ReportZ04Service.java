package car_backend.service.reports;

import car_backend.model.excel.ReportZ04;

import java.util.List;

public interface ReportZ04Service {

    List<ReportZ04> getReport();

    void generateReportData();
}
