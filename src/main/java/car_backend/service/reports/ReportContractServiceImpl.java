package car_backend.service.reports;

import car_backend.model.enums.ReportFilesInformation;
import car_backend.model.reports.ContractReport;
import car_backend.repository.reports.ReportContractRepository;
import car_backend.utils.reports.ReportsExcelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class ReportContractServiceImpl implements ReportContractService {

    @Autowired
    ReportContractRepository reportContractRepository;

    @Autowired
    ReportsExcelBuilder excelBuilder;

    @Override
    // get the report content
    public List<ContractReport> getReport() {

        reportContractRepository.truncateReportTable();

        reportContractRepository.insertReportTable();

        return reportContractRepository.findAll();
    }

    @Override
    // generate the excel report following the template
    public byte[] generateContractReportExcel() {
        try {
            List<ContractReport> reports = getReport();
            return excelBuilder.buildGenericReport(reports, "templates/contract_report.xlsx", 6, this::writeContractReport);
        } catch (IOException e) {
            log.error("Error generating contract report excel", e);
            throw new RuntimeException("Failed to generate contract report", e);
        }
    }

    // map the excel report fields with its model
    private void writeContractReport(ContractReport contract, Row row) {
        int cellCount = 1;

        excelBuilder.fillCellWithString(contract.getIdentifier(), row, cellCount++); // E-0
        excelBuilder.fillCellWithString(contract.getServiceIdentifier(), row, cellCount++); // E-1
        excelBuilder.fillCellWithLocalDate(contract.getStartDateOfTheContract(), row, cellCount++); // E-2
        excelBuilder.fillCellWithLocalDate(contract.getEndDateOfTheContract(), row, cellCount++); // E-3
        excelBuilder.fillCellWithLocalDate(contract.getNextRenewalDate(), row, cellCount++); // E-4
        excelBuilder.fillCellWithString(contract.getServiceRecipientName(), row, cellCount++); // E-5.1
        excelBuilder.fillCellWithString(contract.getProviderEntityName(), row, cellCount++); // E-5.2
        excelBuilder.fillCellWithString(contract.getProviderEntityCode(), row, cellCount++); // E-5.3
        excelBuilder.fillCellWithString(contract.getProviderEntityTypeOfCode(), row, cellCount++); // E-5.4
        excelBuilder.fillCellWithString(contract.getProviderEntityRegisteredAddress(), row, cellCount++); // E-5.5
        excelBuilder.fillCellWithString(contract.getProviderParentName(), row, cellCount++); // E-5.6
        excelBuilder.fillCellWithString(contract.getProviderParentCode(), row, cellCount++); // E-5.7
        excelBuilder.fillCellWithString(contract.getProviderParentTypeOfCode(), row, cellCount++); // E-5.8
        excelBuilder.fillCellWithString(contract.getSubcontractor(), row, cellCount++); // E-6
        excelBuilder.fillCellWithString(contract.getPartOfTheGroupServiceDelivery(), row, cellCount++); // E-7
        excelBuilder.fillCellWithString(contract.getPartOfTheResolutionGroup(), row, cellCount++); // E-8
        excelBuilder.fillCellWithString(contract.getGroupDepartmentResponsible(), row, cellCount++); // E-9
        excelBuilder.fillCellWithString(contract.getBriefDescriptionOfTheService(), row, cellCount++); // E-10
        excelBuilder.fillCellWithString(contract.getPricingStructurePredictable(), row, cellCount++); // E-11
        excelBuilder.fillCellWithString(contract.getEstimatedTotalAnnualBudgetCost(), row, cellCount++); // E-12
        excelBuilder.fillCellWithString(contract.getDegreeOfCriticality(), row, cellCount++); // E-13
        excelBuilder.fillCellWithString(contract.getCriticalFunctionForService(), row, cellCount++); // E-14
        excelBuilder.fillCellWithString(contract.getCoreBusinessLinesForService(), row, cellCount++); // E-15
        excelBuilder.fillCellWithString(contract.getResolutionGroupsForService(), row, cellCount++); // E-16
        excelBuilder.fillCellWithString(contract.getNameOfAlternativeServiceProvider(), row, cellCount++); // E-17
        excelBuilder.fillCellWithString(contract.getJurisdictionOfContract(), row, cellCount++); // E-18
        excelBuilder.fillCellWithString(contract.getGoverningLaw(), row, cellCount++); // E-19
        excelBuilder.fillCellWithString(contract.getCountriesWhereServicesProvided(), row, cellCount++); // E-20
        excelBuilder.fillCellWithString(contract.getResolutionResilientContract(), row, cellCount++); // E-21
        excelBuilder.fillCellWithString(contract.getPenaltiesForSuspension(), row, cellCount++); // E-22
        excelBuilder.fillCellWithString(contract.getTriggersForEarlyTermination(), row, cellCount++); // E-23
        excelBuilder.fillCellWithInteger(contract.getTerminationNoticePeriodProvider(), row, cellCount++); // E-24
        excelBuilder.fillCellWithInteger(contract.getDurationPostTerminationAssistance(), row, cellCount++); // E-25

        // Additional fields (A-1 to A-7)
        excelBuilder.fillCellWithString(contract.getRelationshipsBetweenContracts(), row, cellCount++); // A-1
        excelBuilder.fillCellWithString(contract.getConditionsOfPayment(), row, cellCount++); // A-2
        excelBuilder.fillCellWithString(contract.getExistenceAutomaticRenewalClauses(), row, cellCount++); // A-3
        excelBuilder.fillCellWithString(contract.getQuantitativePerformanceTargets(), row, cellCount++); // A-4
        excelBuilder.fillCellWithString(contract.getQualitativePerformanceTargets(), row, cellCount++); // A-5
        excelBuilder.fillCellWithString(contract.getPartiesAllowedToTerminate(), row, cellCount++); // A-6
        excelBuilder.fillCellWithString(contract.getEstimatedTimeForSubstitutability(), row, cellCount++); // A-7
    }
}
