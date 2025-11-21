package car_backend.service.activity;

import car_backend.model.excel.RelevantServicesReport;

import java.util.List;

public interface ActivityFileService {

    List<RelevantServicesReport> getReport(String sqlLoadId);
}
