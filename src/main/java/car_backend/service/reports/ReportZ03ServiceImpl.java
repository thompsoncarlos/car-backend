package car_backend.service.reports;

import car_backend.model.reports.ReportZ01;
import car_backend.model.reports.ReportZ03;
import car_backend.repository.relations.ActivityPersonRelRepository;
import car_backend.repository.reports.ReportZ01Repository;
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
public class ReportZ03ServiceImpl implements ReportZ03Service {

    @Autowired
    private ReportZ01Repository z01Repository;

    @Autowired
    private ActivityPersonRelRepository activityPersonRelRepository;

    @Override
    public List<ReportZ03> generateReportZ03() {

        List<ReportZ03> output = new ArrayList<>();

        List<ReportZ01> z01List = z01Repository.findAll();
        if (z01List.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<String> activities = z01List.stream().map(ReportZ01::getServiceId).toList();

        // assim vai dar erro, precisa pegar o ID da atividade > com isso pegar as roles > com as roles pegar os uos
        // caso tenha mais de um uo, para um mesma role e em uma mesma atividade, tem que colocar as dashs --
        // so adiconar uma nova linha se tiver roles diferentes, no caso da pra fazer no mapeamento das UO por person,
        // vai precisar confirmar o role do PERSON_ID, caso ja esteja associado a aquele ACTIVITIY_ID, concatena com outro
        // uo anterior... talvez seja mais simples por meio de query 



        return List.of();
    }
}
