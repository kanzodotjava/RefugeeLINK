package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Organization;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByUserName(String userName);

}
