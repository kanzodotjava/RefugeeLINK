package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Repositories.FormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeFormationRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefugeeFormationService {

    @Autowired
    RefugeeFormationRepository refugeeFormationRepository;

    @Autowired
    RefugeeRepository refugeeRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    RefugeeService refugeeService;

    public RefugeeFormation addRefugeeFormation;


    public List<RefugeeFormation> getAllRefugeeFormations() {
        return refugeeFormationRepository.findAll();
    }


    public RefugeeFormation getRefugeeFormation(Long id) throws RefugeeFormationIdNotFound {
        return refugeeFormationRepository.findById(id).orElseThrow(() -> new RefugeeFormationIdNotFound("Refugee Formation with id " + id + " not found"));
    }

    public void deleteRefugeeFormation(Long id) throws RefugeeFormationIdNotFound {
        if (!refugeeFormationRepository.existsById(id)) {
            throw new RefugeeFormationIdNotFound("Refugee Formation with id " + id + " not found");
        }
        refugeeFormationRepository.deleteById(id);
    }


    public void toggleApprovalStatus(Long refugeeId, Long formationId) throws RefugeeFormationIdNotFound, RefugeeIdNotFound, FormationIdNotFound {
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

        // Toggle the isApproved field
        refugeeFormation.setApproved(!refugeeFormation.isApproved());

        // Save the changes
        refugeeFormationRepository.save(refugeeFormation);
    }

    public void failRefugeeFormation(Long refugeeId, Long formationId) throws RefugeeFormationIdNotFound, RefugeeIdNotFound, FormationIdNotFound {
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
        refugeeFormation.setApproved(false);

        // Save the changes
        refugeeFormationRepository.save(refugeeFormation);
    }


    public List<RefugeeFormation> getCompletedFormationsByRefugee(Long refugeeId) {
        return refugeeFormationRepository.findAllByRefugeeIdAndIsApproved(refugeeId, true);
    }


    public List<Refugee> getRefugeesByFormationId(Long formationId) {
        // Retrieve the list of RefugeeFormation objects by formation id
        List<RefugeeFormation> refugeeFormations = refugeeFormationRepository.findAllByFormationId(formationId);

        // Extract the refugee ids from the list of RefugeeFormation objects
        List<Long> refugeeIds = refugeeFormations.stream()
                .map(refugeeFormation -> refugeeFormation.getRefugee().getId())
                .collect(Collectors.toList());

        // Return the refugees associated with the retrieved refugee IDsr
        return refugeeRepository.findAllById(refugeeIds);
    }

    public boolean isRefugeeFormationApproved(Long refugeeId, Long formationId) throws RefugeeFormationIdNotFound {
        // Find the RefugeeFormation object that corresponds to the given refugeeId and formationId
        RefugeeFormation refugeeFormation = refugeeFormationRepository.findByRefugeeIdAndFormationId(refugeeId, formationId)
                .orElseThrow(() -> new RefugeeFormationIdNotFound("Refugee Formation with refugeeId " + refugeeId + " and formationId " + formationId + " not found"));

        // Return the isApproved status
        return refugeeFormation.isApproved();
    }

    public void deleteRefugeeFormation(Long refugeeId, Long formationId) throws RefugeeFormationIdNotFound {
        // Find the RefugeeFormation object that corresponds to the given refugeeId and formationId
        RefugeeFormation refugeeFormation = refugeeFormationRepository.findByRefugeeIdAndFormationId(refugeeId, formationId)
                .orElseThrow(() -> new RefugeeFormationIdNotFound("Refugee Formation with refugeeId " + refugeeId + " and formationId " + formationId + " not found"));

        // Check if the Formation being deleted is the current Formation of the Refugee
        if (refugeeFormation.getRefugee().getFormation().getId().equals(formationId)) {
            // If so, remove the Formation from the Refugee
            refugeeService.removeFormationFromRefugee(refugeeFormation.getRefugee().getUserName());
        }

        // Delete the RefugeeFormation
        refugeeFormationRepository.delete(refugeeFormation);
    }




}
