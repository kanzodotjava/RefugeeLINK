package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Repositories.FormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeFormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;

@Service
public class RefugeeFormationService {

    @Autowired
    RefugeeFormationRepository refugeeFormationRepository;

    @Autowired
    RefugeeRepository refugeeRepository;

    @Autowired
    FormationRepository formationRepository;

    public RefugeeFormation addRefugeeFormation;

    public RefugeeFormation addRefugeeFormation(RefugeeFormation refugeeFormation) {
        return refugeeFormationRepository.save(refugeeFormation);
    }


    public List<RefugeeFormation> getAllRefugeeFormations() {
        return refugeeFormationRepository.findAll();
    }


    public RefugeeFormation getRefugeeFormation(Long id) throws RefugeeFormationIdNotFound {
        return refugeeFormationRepository.findById(id).orElseThrow(() -> new RefugeeFormationIdNotFound("Refugee Formation with id " + id + " not found"));
    }

    public RefugeeFormation updateRefugeeFormation(RefugeeFormation refugeeFormation) throws RefugeeFormationIdNotFound {
        if (!refugeeFormationRepository.existsById(refugeeFormation.getId())) {
            throw new RefugeeFormationIdNotFound("Refugee Formation with id " + refugeeFormation.getId() + " not found");
        }
        return refugeeFormationRepository.save(refugeeFormation);
    }

    public void deleteRefugeeFormation(Long id) throws RefugeeFormationIdNotFound {
        if (!refugeeFormationRepository.existsById(id)) {
            throw new RefugeeFormationIdNotFound("Refugee Formation with id " + id + " not found");
        }
        refugeeFormationRepository.deleteById(id);
    }


//    public void approveRefugeeFormation(Long id) throws RefugeeFormationIdNotFound {
//        RefugeeFormation refugeeFormation = getRefugeeFormation(id);
//        refugeeFormation.setApproved(true);
//        updateRefugeeFormation(refugeeFormation);
//    }

    public void approveRefugeeFormation(Long refugeeId, Long formationId) throws RefugeeFormationIdNotFound, RefugeeIdNotFound, FormationIdNotFound {
        // Find the RefugeeFormation object that corresponds to the given refugeeId and formationId
        RefugeeFormation refugeeFormation = refugeeFormationRepository.findByRefugeeIdAndFormationId(refugeeId, formationId)
                .orElseThrow(() -> new RefugeeFormationIdNotFound("Refugee Formation with refugeeId " + refugeeId + " and formationId " + formationId + " not found"));

        // Check if the Refugee with the given refugeeId exists
        if (!refugeeRepository.existsById(refugeeId)) {
            throw new RefugeeIdNotFound("Refugee with id " + refugeeId + " not found");
        }

        // Check if the Formation with the given formationId exists
        if (!formationRepository.existsById(formationId)) {
            throw new FormationIdNotFound("Formation with id " + formationId + " not found");
        }

        // Set the isApproved field to true
        refugeeFormation.setApproved(true);

        // Save the changes
        updateRefugeeFormation(refugeeFormation);
    }



}
