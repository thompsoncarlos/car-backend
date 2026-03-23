package car_backend.service.reports;

import car_backend.model.reports.ReportServiceCatalogue;
import car_backend.repository.reports.ReportServiceCatalogueRepository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ReportServiceCatalogueServiceImpl implements ReportServiceCatalogueService {

    @Autowired
    private ReportServiceCatalogueRepository reportServiceCatalogueRepository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    public List<ReportServiceCatalogue> getReport() {

        reportServiceCatalogueRepository.truncateReportTable();

        reportServiceCatalogueRepository.insertReportTable();

        List<ReportServiceCatalogue> reports = reportServiceCatalogueRepository.findAll();
        log.info("Service Catalogue report generated with {} records", reports.size());
        return reports;
    }

    @Override
    public byte[] generateServiceCatalogueReportExcel() {
        try {
            List<ReportServiceCatalogue> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/report_service_catalogue.xlsx", 24,
                    this::writeServiceCatalogueReport);
        } catch (IOException e) {
            log.error("Error generating Service Catalogue excel", e);
            throw new RuntimeException("Failed to generate Service Catalogue report", e);
        }
    }

    private void writeServiceCatalogueReport(ReportServiceCatalogue report, Row row) {
        int cellCount = 1;

        excelBuilder.fillCellWithString(report.getServiceIdentifier(), row, cellCount++); // SERVICE_IDENTIFIER_0005
        excelBuilder.fillCellWithString(report.getServiceType(), row, cellCount++); // SERVICE_TYPE_0010
        excelBuilder.fillCellWithString(report.getServiceTitleBkTaxo(), row, cellCount++); // UNIQUE_SERVICE_TITLE_BK_TAXO_0015
        excelBuilder.fillCellWithString(report.getServiceDescription(), row, cellCount++); // SERVICE_DESCRIPTION_0020
        excelBuilder.fillCellWithString(report.getServiceProviderEntityName(), row, cellCount++); // SERVICE_PROVIDER_ENTITY_NAME_0030
        excelBuilder.fillCellWithString(report.getServiceProviderEntityDeptName(), row, cellCount++); // SERVICE_PROVIDER_ENTITY_DEPARTMENT_NAME_0040
        excelBuilder.fillCellWithString(report.getServiceProviderEntityCode(), row, cellCount++); // SERVICE_PROVIDER_ENTITY_CODE_0050
        excelBuilder.fillCellWithString(report.getServiceRecipientEntityName(), row, cellCount++); // SERVICE_RECIPIENT_ENTITY_NAME_0060
        excelBuilder.fillCellWithString(report.getServiceRecipientEntityCode(), row, cellCount++); // SERVICE_RECIPIENT_ENTITY_CODE_0070
        excelBuilder.fillCellWithString(report.getDeliveryModel(), row, cellCount++); // DELIVERY_MODEL_0080
        excelBuilder.fillCellWithString(report.getCriticalFunctionId(), row, cellCount++); // CRITICAL_FUNCTION_ID_0090
        excelBuilder.fillCellWithString(report.getCoreBusinessLineId(), row, cellCount++); // CORE_BUSINESS_LINE_0100
        excelBuilder.fillCellWithString(report.getSubstitutability(), row, cellCount++); // SUBSTITUTABILITY_0110
        excelBuilder.fillCellWithString(report.getCost(), row, cellCount++); // COST_0120
        excelBuilder.fillCellWithString(report.getContactId(), row, cellCount++); // CONTACT_ID_0130
        excelBuilder.fillCellWithString(report.getInclusionOfResolution(), row, cellCount++); // INCLUSION_OF_RESOLUTION_0140
        excelBuilder.fillCellWithString(report.getCountry(), row, cellCount++); // COUNTRY_0150
        excelBuilder.fillCellWithString(report.getMacroBusinessLine(), row, cellCount++); // MACRO_BUSINESS_LINE_0160
        excelBuilder.fillCellWithString(report.getMacroProcess(), row, cellCount++); // MACRO_PROCESS_0170
        excelBuilder.fillCellWithString(report.getProcess(), row, cellCount++); // PROCESS_0180
        excelBuilder.fillCellWithString(report.getSubProcess(), row, cellCount++); // SUB_PROCESS_0190
        excelBuilder.fillCellWithString(report.getActivityCategory(), row, cellCount++); // ACTIVITY_CATEGORY_0200
        excelBuilder.fillCellWithString(report.getIsBlocking(), row, cellCount); // IS_BLOKING_0210
    }
}
