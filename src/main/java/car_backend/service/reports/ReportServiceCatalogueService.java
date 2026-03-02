package car_backend.service.reports;

import car_backend.model.reports.ReportServiceCatalogue;

import java.util.List;

public interface ReportServiceCatalogueService {

    List<ReportServiceCatalogue> getReport();

    byte[] generateServiceCatalogueReportExcel();

}
