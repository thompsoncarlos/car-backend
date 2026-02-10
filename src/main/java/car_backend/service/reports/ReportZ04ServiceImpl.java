package fr.bpce.car.service.reports;

import fr.bpce.car.model.reports.ReportZ01;
import fr.bpce.car.model.reports.ReportZ04;
import fr.bpce.car.repository.relations.ActivityPersonRelRepository;
import fr.bpce.car.repository.reports.ReportZ01Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportZ04ServiceImpl implements ReportZ04Service {

    @Autowired
    private ReportZ01Repository z01Repository;

    @Autowired
    private ActivityPersonRelRepository activityPersonRelRepository;

    @Override
    public List<ReportZ04> generateReportZ04() {

        List<ReportZ04> output = new ArrayList<>();

        List<ReportZ01> z01List = z01Repository.findAll();
        if (z01List.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<String> activities = z01List.stream().map(ReportZ01::getServiceId).toList();

        return List.of();
    }
}
