package car_backend.repository;

import car_backend.model.dao.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, String> {

    Optional<OrganizationalUnit> findByFrenchLabel(String uoFrenchLabel);
}
