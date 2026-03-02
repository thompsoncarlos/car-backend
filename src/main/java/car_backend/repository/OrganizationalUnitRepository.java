package car_backend.repository;

import car_backend.model.dao.refs.OrganizationalUnitRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnitRef, String> {

    Optional<OrganizationalUnitRef> findByFrenchLabel(String uoFrenchLabel);
}
