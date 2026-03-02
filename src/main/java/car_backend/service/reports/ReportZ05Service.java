package car_backend.service.reports;

import car_backend.model.reports.ReportZ05;

import java.util.List;

public interface ReportZ05Service {

    List<ReportZ05> getReport();

    byte[] generateZ05ReportExcel();

}