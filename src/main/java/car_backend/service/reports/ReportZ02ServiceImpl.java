package car_backend.service.reports;

import car_backend.model.reports.ReportZ01;
import car_backend.model.reports.ReportZ02;
import car_backend.repository.reports.ReportZ01Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportZ02ServiceImpl implements ReportZ02Service {

    @Autowired
    ReportZ01Repository repositoryReportZ01;

    @Override
    public List<ReportZ02> getReportZ02() {

        List<ReportZ01> reportZ01 = repositoryReportZ01.findAll();





        return List.of();
    }
}
