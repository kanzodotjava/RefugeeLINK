package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.OrganizationNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Organization;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.FormationRepository;
import pt.upskill.RefugeeLINK.Repositories.OrganizationRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private OrganizationService organizationService;

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


    //add formation with the organization id
    public Formation registerFormation(Formation formation, Long organizationId) throws OrganizationNotFound {
        Organization organization = organizationService.getOrganizationById(organizationId);
        formation.setOrganization(organization);
        return this.formationRepository.save(formation);
    }
}
