package fr.bpce.car.repository.relations;

import fr.bpce.car.model.dao.relations.ActivityPersonRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityPersonRelRepository extends JpaRepository<ActivityPersonRel, Long> {

    //List<ActivityPersonRel> findByActivityIdIn(List<String> activityIds);
}