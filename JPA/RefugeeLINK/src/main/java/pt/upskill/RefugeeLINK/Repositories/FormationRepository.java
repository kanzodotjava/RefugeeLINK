package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Refugee;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{
    Optional<Formation> findByName(String name);
    List<Formation> findByStatus(FormationStatus status);

}
