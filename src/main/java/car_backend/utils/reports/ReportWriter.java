package car_backend.utils.reports;

import org.apache.poi.ss.usermodel.Row;

@FunctionalInterface
public interface ReportWriter<T> {
    void writeReport(T report, Row row);
}
