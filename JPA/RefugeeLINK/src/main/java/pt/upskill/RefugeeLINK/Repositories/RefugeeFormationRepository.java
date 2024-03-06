package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;

import java.util.Optional;

public interface RefugeeFormationRepository extends JpaRepository<RefugeeFormation, Long> {

    Optional<RefugeeFormation> findByRefugeeIdAndFormationId(Long refugeeId, Long formationId);

}