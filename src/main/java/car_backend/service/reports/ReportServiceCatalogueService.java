package car_backend.service.reports;

import car_backend.model.reports.ServiceCatalogueReport;

public interface ReportServiceCatalogueService {

    byte[] generateServiceCatalogueReportExcel();

}
