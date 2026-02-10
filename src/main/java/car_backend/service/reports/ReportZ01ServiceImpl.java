package fr.bpce.car.service.reports;

import fr.bpce.car.model.reports.ReportZ01;
import fr.bpce.car.repository.reports.ReportZ01Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ReportZ01ServiceImpl implements ReportZ01Service {

    @Autowired
    ReportZ01Repository repository;

    @Override
    public List<ReportZ01> getReport() {
        return repository.generateReportData().stream().map(row -> {
            ReportZ01 data = new ReportZ01();
            data.setServiceId(Objects.isNull(row[0]) ? null : row[0].toString());
            data.setServiceType(Objects.isNull(row[1]) ? null : row[1].toString());
            data.setServiceUniqueLabel(Objects.isNull(row[2]) ? null : row[2].toString());
            data.setServiceRecipientName(Objects.isNull(row[3]) ? null : row[3].toString());
            data.setServiceRecipientCode(Objects.isNull(row[4]) ? null : row[4].toString());
            data.setServiceProviderEntityName(Objects.isNull(row[5]) ? null : row[5].toString());
            data.setServiceProviderEntityCode(Objects.isNull(row[6]) ? null : row[6].toString());
            return data;
        }).toList();
    }
}
