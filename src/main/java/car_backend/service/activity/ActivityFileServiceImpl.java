package car_backend.service.activity;

import car_backend.model.excel.RelevantServicesReport;
import car_backend.repository.ActivityFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ActivityFileServiceImpl implements ActivityFileService {

    @Autowired
    private ActivityFileRepository repository;

    @Override
    public List<RelevantServicesReport> getReport(String sqlLoadId) {

            return repository.findActivitiesRelevantServices("20251013_47843")
                    .stream().map(row -> {
                        RelevantServicesReport data = new RelevantServicesReport();
                        data.setServiceId(row[0].toString());
                        data.setServiceUniqueLabel(row[2].toString());
                        return data;
                    }).toList();

    }
}
