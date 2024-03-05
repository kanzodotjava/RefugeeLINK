package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.FormationRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Formation getFormationById(Long id) throws FormationIdNotFound {
        if (id == null) {
            throw new FormationIdNotFound("Formation with ID " + id + " not found.");
        }
        Optional<Formation> formation = formationRepository.findById(id);
        return formation.orElse(null);
    }

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    public Formation registerFormation(Formation formation) {
        return this.formationRepository.save(formation);
    }

    public Formation updateFormation(Formation formation) throws FormationIdNotFound {
        Optional<Formation> existingFormation = formationRepository.findById(formation.getId());

        if (existingFormation.isPresent()) {
            Formation updatedFormation = existingFormation.get();

            // Update the attributes of the existing Formation
            updatedFormation.setName(formation.getName());
            updatedFormation.setDescription(formation.getDescription());
            updatedFormation.setNumberOfLessons(formation.getNumberOfLessons());
            updatedFormation.setStartDate(formation.getStartDate());
            updatedFormation.setEndDate(formation.getEndDate());
            updatedFormation.setStudents(formation.getStudents());
            updatedFormation.setIsApproved(formation.getIsApproved());

            // Save the updated Formation back to the database
            return formationRepository.save(updatedFormation);
        } else {
            throw new FormationIdNotFound("Formation with ID " + formation.getId() + " not found.");
        }
    }

    public void deleteFormation(Long id) throws FormationIdNotFound {
        if (id == null) {
            throw new FormationIdNotFound("Formation with ID " + id + " not found.");
        }
        formationRepository.deleteById(id);
    }




    public void approveRefugeeInFormation(Long formationId, Long refugeeId) throws FormationIdNotFound, RefugeeIdNotFound {
        Optional<Formation> formation = formationRepository.findById(formationId);
        if (formation.isPresent()) {
            Formation updatedFormation = formation.get();
            Map<Refugee, Boolean> approvals = updatedFormation.getIsApproved();

            // Find the refugee in the map
            Refugee refugeeToApprove = null;
            for (Refugee refugee : approvals.keySet()) {
                if (refugee.getId().equals(refugeeId)) {
                    refugeeToApprove = refugee;
                    break;
                }
            }

            if (refugeeToApprove == null) {
                throw new RefugeeIdNotFound("Refugee with ID " + refugeeId + " not found in formation.");
            }

            // Approve the refugee
            approvals.put(refugeeToApprove, true);
            updatedFormation.setIsApproved(approvals);

            // Save the updated Formation back to the database
            formationRepository.save(updatedFormation);
        } else {
            throw new FormationIdNotFound("Formation with ID " + formationId + " not found.");
        }
    }
}
