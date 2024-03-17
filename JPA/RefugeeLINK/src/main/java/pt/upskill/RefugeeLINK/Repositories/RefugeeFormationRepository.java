package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;

import java.util.List;
import java.util.Optional;

/**
 *  Repository for RefugeeFormation
 */
public interface RefugeeFormationRepository extends JpaRepository<RefugeeFormation, Long> {

    /**
     *  Get the RefugeeFormation with the given ID
     * @param refugeeId
     * @param formationId
     * @return  The Optional of the RefugeeFormation
     */
    Optional<RefugeeFormation> findByRefugeeIdAndFormationId(Long refugeeId, Long formationId);

    /**
     *  Get all the RefugeeFormation with the given ID
     * @param refugeeId
     * @param isApproved
     * @return  The List of the RefugeeFormation
     */
    List<RefugeeFormation> findAllByRefugeeIdAndIsApproved(Long refugeeId, boolean isApproved);

    /**
     *  Get all the RefugeeFormation with the given ID
     * @param refugee
     * @return  The List of the RefugeeFormation
     */
    List<RefugeeFormation> findAllByRefugee(Refugee refugee);

    /**
     *  Get all the RefugeeFormation with the given ID
     * @param formationId
     * @return  The List of the RefugeeFormation
     */
    List<RefugeeFormation> findAllByFormationId(Long formationId);

}
