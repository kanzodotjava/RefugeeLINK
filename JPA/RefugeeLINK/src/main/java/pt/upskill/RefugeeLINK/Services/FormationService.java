package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private RefugeeRepository refugeeRepository;

    @Autowired
    private RefugeeFormationRepository refugeeFormationRepository;

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

    public String registerFormation(Formation formation) {
        formationRepository.save(formation);

        return formation.getName();
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
            updatedFormation.setDuration(formation.getDuration());


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


    public Formation updateOrganization(String name, Long newOrganizationId) throws FormationIdNotFound {
        Optional<Formation> existingFormation = formationRepository.findByName(name);

        if (existingFormation.isPresent()) {
            Formation updatedFormation = existingFormation.get();
            updatedFormation.setOrganizationId(newOrganizationId);
            return formationRepository.save(updatedFormation);
        } else {
            throw new FormationIdNotFound("Formation " + name + " not found.");
        }
    }


    public boolean registerRefugeeToFormation(Long refugeeId, Long formationId) {
        Optional<Refugee> refugeeOpt = refugeeRepository.findById(refugeeId);
        Optional<Formation> formationOpt = formationRepository.findById(formationId);

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




    public Formation startFormation(Long formationId) throws FormationIdNotFound {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new FormationIdNotFound("Formation not found with id: " + formationId));

        // Change the status to ONGOING
        formation.setStatus(FormationStatus.ONGOING);

        // Save the updated formation
        return formationRepository.save(formation);
    }

    public Formation completeFormation(Long formationId) throws FormationIdNotFound {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new FormationIdNotFound("Formation not found with id: " + formationId));

        // Change the status to COMPLETED
        formation.setStatus(FormationStatus.COMPLETED);

        // Save the updated formation
        return formationRepository.save(formation);
    }



    public List<Formation> getFormationByStatus(FormationStatus status){
        List<Formation> formations = formationRepository.findByStatus(status);
        if(formations.isEmpty()){
            throw new IllegalArgumentException("No formations found!");
        }
        return formations;
    }
}
