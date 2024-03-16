package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;

import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;

import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Repositories.FormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeFormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *  Formation service
 */
@Service
public class FormationService {

    private final RestTemplate restTemplate;

    @Autowired
    public FormationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private RefugeeRepository refugeeRepository;

    @Autowired
    private RefugeeFormationRepository refugeeFormationRepository;

    /**
     *  Get formation by id
     * @param id
     * @return  formation
     * @throws FormationIdNotFound
     */
    public Formation getFormationById(Long id) throws FormationIdNotFound {

        // Check if the formation exists
        if (id == null) {
            throw new FormationIdNotFound("Formation with ID " + id + " not found.");
        }

        // Get the formation
        Optional<Formation> formation = formationRepository.findById(id);

        // Return the formation
        return formation.orElse(null);
    }

    /**
     *  Get all formations.
     * @return  List of all formations.
     */
    public List<Formation> getAllFormations() {
        // Get all formations
        List<Formation> formations = formationRepository.findAll();

        // Check if there are any formations
        if (formations.isEmpty()) {
            throw new IllegalArgumentException("No formations found!");
        }

        // Return the list of formations
        return formations;
    }

    /**
     *  Register a new formation
     * @param formation
     * @return  name
     */
    public String registerFormation(Formation formation) {
        formationRepository.save(formation);

        return formation.getName();
    }

    /**
     *  Update an existing formation.
     * @param formation
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    public Formation updateFormation(Formation formation) throws FormationIdNotFound {
        Optional<Formation> existingFormation = formationRepository.findById(formation.getId());

        if (existingFormation.isPresent()) {
            Formation updatedFormation = existingFormation.get();

            // Update the attributes of the existing Formation
            updatedFormation.setName(formation.getName());
            updatedFormation.setDescription(formation.getDescription());
            updatedFormation.setNumberOfLessons(formation.getNumberOfLessons());
            updatedFormation.setStartDate(formation.getStartDate());
            updatedFormation.setDuration(formation.getDuration());


            // Save the updated Formation back to the database
            return formationRepository.save(updatedFormation);
        } else {
            throw new FormationIdNotFound("Formation with ID " + formation.getId() + " not found.");
        }
    }

    /**
     *  Delete an existing formation.
     * @param id
     * @throws FormationIdNotFound
     */
    public void deleteFormation(Long id) throws FormationIdNotFound {

        // Check if the formation exists
        Optional<Formation> formation = formationRepository.findById(id);
        if (formation.isEmpty()) {
            throw new FormationIdNotFound("Formation with ID " + id + " not found.");
        }

        // Delete the formation
        formationRepository.deleteById(id);
    }

    /**
     *  Update the organization of an existing formation.
     * @param name
     * @param newOrganizationId
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    public Formation updateOrganization(String name, Long newOrganizationId) throws FormationIdNotFound {
        // Check if the formation exists
        Optional<Formation> existingFormation = formationRepository.findByName(name);

        // Update the organization of the formation
        if (existingFormation.isPresent()) {
            Formation updatedFormation = existingFormation.get();
            updatedFormation.setOrganizationId(newOrganizationId);
            return formationRepository.save(updatedFormation);
        } else {
            throw new FormationIdNotFound("Formation " + name + " not found.");
        }
    }

    /**
     *  Register a refugee to a formation
     * @param refugeeId
     * @param formationId
     * @return  true if the registration was successful, false otherwise
     */
    public boolean registerRefugeeToFormation(Long refugeeId, Long formationId) {
        // Check if the refugee exists
        Optional<Refugee> refugeeOpt = refugeeRepository.findById(refugeeId);

        // Check if the formation exists
        Optional<Formation> formationOpt = formationRepository.findById(formationId);

        // Register the refugee to the formation
        if (refugeeOpt.isPresent() && formationOpt.isPresent()) {
            Refugee refugee = refugeeOpt.get();
            Formation formation = formationOpt.get();

            // Verifica se a formação está com o status AWAITING_START
            if (formation.getStatus() != FormationStatus.AWAITING_START) {
                return false; // A formação não está aberta para inscrições
            }

            // Verifica se o refugiado já está inscrito em uma formação com status AWAITING_START ou ONGOING
            boolean hasActiveFormation = refugeeFormationRepository.findAllByRefugee(refugee).stream()
                    .anyMatch(rf -> rf.getFormation().getStatus() == FormationStatus.AWAITING_START ||
                            rf.getFormation().getStatus() == FormationStatus.ONGOING);

            if (!hasActiveFormation) {
                refugee.setFormation(formation);
                RefugeeFormation newRefugeeFormation = new RefugeeFormation();
                newRefugeeFormation.setRefugee(refugee);
                newRefugeeFormation.setFormation(formation);
                newRefugeeFormation.setApproved(false);
                refugeeFormationRepository.save(newRefugeeFormation);
                return true;
            }
        }
        return false; // O refugiado não pode se registrar nesta formação
    }

    /**
     *  Start a formation
     * @param formationId
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    public Formation startFormation(Long formationId) throws FormationIdNotFound {
        // Check if the formation exists
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new FormationIdNotFound("Formation not found with id: " + formationId));

        // Change the status to ONGOING
        formation.setStatus(FormationStatus.ONGOING);

        // Save the updated formation
        return formationRepository.save(formation);
    }

    /**
     *  Complete a formation
     * @param formationId
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    public Formation completeFormation(Long formationId) throws FormationIdNotFound {
        // Check if the formation exists
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new FormationIdNotFound("Formation not found with id: " + formationId));

        // Change the status to COMPLETED
        formation.setStatus(FormationStatus.COMPLETED);

        // Save the updated formation
        return formationRepository.save(formation);
    }

    /**
     *  Get formations by status
     * @param status
     * @return  List of formations with the given status
     */
    public List<Formation> getFormationByStatus(FormationStatus status){
        // Get all formations with the given status
        List<Formation> formations = formationRepository.findByStatus(status);

        // Check if there are any formations with the given status
        if(formations.isEmpty()){
            throw new IllegalArgumentException("No formations found!");
        }

        // Return the list of formations
        return formations;
    }

    /**
     *  Get formations by organization id
     * @param organizationId
     * @return  List of formations with the given organization id
     */
    public List<Formation> getFormationsByOrganizationId(Long organizationId) {

        // Get all formations by organization id
        return formationRepository.findAllByOrganizationId(organizationId);
    }

    /**
     *  Get formations by organization username
     * @param username
     * @return  List of formations with the given organization username
     */
    public List<Formation> getFormationsByOrganizationUsername(String username) {

        //Get the organization id
        String url = "https://localhost:7165/organization/formations/" + username;
        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        Long organizationId = response.getBody();

        // Get the formations by organization id
        List<Formation> formations = getFormationsByOrganizationId(organizationId);

        // Return the list of formations
        return formations;
    }

    /**
     *  Change formation status
     * @param formationId
     * @param newStatus
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    public Formation changeFormationStatus(Long formationId, FormationStatus newStatus) throws FormationIdNotFound {
        // Find the Formation object that corresponds to the given formationId
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new FormationIdNotFound("Formation with id " + formationId + " not found"));

        // Change the status of the Formation
        formation.setStatus(newStatus);

        // Save the updated Formation
        return formationRepository.save(formation);
    }

}
