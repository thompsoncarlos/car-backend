package car_backend.model.enums;

import lombok.Getter;

@Getter
public enum ReportFilesInformation {

    Z01("ReportZ01_", "yyyyMMdd_HHmmss", "attachment; filename=\"", ".xlsX", "application/vnd.ms-excel"),
    Z04("ReportZ04_", "yyyyMMdd_HHmmss", "attachment; filename=\"", ".xlsX", "application/vnd.ms-excel"),
    CONTRACT_REPOSITORY("ContractRepository_", "yyyyMMdd_HHmmss", "attachment; filename=\"", ".xlsx", "application/vnd.ms-excel");


    private String namePrefix;
    private String dateMask;
    private String attachment;
    private String extension;
    private String excelMediaType;

    ReportFilesInformation(String namePrefix, String dateMask, String attachment, String extension,
            String excelMediaType) {
        this.namePrefix = namePrefix;
        this.dateMask = dateMask;
        this.attachment = attachment;
        this.extension = extension;
        this.excelMediaType = excelMediaType;
    }
}
