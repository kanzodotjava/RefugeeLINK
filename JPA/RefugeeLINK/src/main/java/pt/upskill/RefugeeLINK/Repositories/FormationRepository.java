package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Refugee;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

/**
 *  Repository for Formation
 */
@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{

    /**
     *  Find Formation by name
     * @param name
     * @return  Formation
     */
    Optional<Formation> findByName(String name);

    /**
     *  Find Formation by status
     * @param status
     * @return  List of Formations with the given status
     */
    List<Formation> findByStatus(FormationStatus status);

    /**
     *  Find list of Formations by organization id
     * @param organizationId
     * @return  List of Formations with the given ORF
     */
    List<Formation> findAllByOrganizationId(Long organizationId);


}
