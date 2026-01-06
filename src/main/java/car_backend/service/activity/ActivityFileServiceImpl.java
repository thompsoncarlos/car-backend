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

}
