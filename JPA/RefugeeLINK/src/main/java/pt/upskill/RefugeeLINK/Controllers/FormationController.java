package pt.upskill.RefugeeLINK.Controllers;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pt.upskill.RefugeeLINK.DTO.FormationDTO;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Services.FormationService;


import java.util.List;

/**
 *  Controller for Formation
 */
@RestController
@RequestMapping("/formation")
public class FormationController {

    private final RestTemplate restTemplate;

    // Constructor
    @Autowired
    public FormationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private FormationService formationService;

    /**
     *  Get all formations
     * @return  List of all formations
     */
    @GetMapping("/all")
    public ResponseEntity<List<Formation>> getAllFormations() {
        // Get all formations
        List<Formation> formations = formationService.getAllFormations();

        // Convert to DTO
        List<FormationDTO> formationDTOs = formations.stream().map(Formation::toDto).toList();

        // Return DTO
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    /**
     *  Get formation by id
     * @param id
     * @return  formation
     * @throws FormationIdNotFound
     */
    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) throws FormationIdNotFound {
        // Get formation
        Formation formation = formationService.getFormationById(id);

        // Return formation
        return new ResponseEntity<>(formation, HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<FormationDTO> registerFormation(@RequestBody Formation formation) {
//        Formation createdFormation = formationService.registerFormation(formation);
//        FormationDTO formationDto = createdFormation.toDto();
//        return new ResponseEntity<>(formationDto, HttpStatus.CREATED);
//    }


    /**
     *  Register a new formation
     * @param formation
     * @return  The name of the formation
     */
    @PostMapping("/register")
    public String  registerFormation(@RequestBody Formation formation) {
        formationService.registerFormation(formation);
        return formation.getName();
    }

    /**
     *  Update an existing formation
     * @param id
     * @param formation
     * @return  updated formation
     * @throws FormationIdNotFound
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @RequestBody Formation formation) throws FormationIdNotFound {
        // Update the formation
        Formation updatedFormation = formationService.updateFormation(formation);

        // Return the updated formation
        return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
    }

    /**
     *  Delete an existing formation
     * @param id
     * @return  No content
     * @throws FormationIdNotFound
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) throws FormationIdNotFound {
        formationService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *  Update the organization id
     * @param name
     * @param orgId
     * @return  updated formation
     */
    @PutMapping("/{name}/organization/{orgId}")
    public ResponseEntity<Formation> updateOrganizationId(@PathVariable String name, @PathVariable Long orgId) {
        try {
            Formation updatedFormation = formationService.updateOrganization(name, orgId);
            return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
        } catch (FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Register a refugee to a formation
     * @param refugeeId
     * @param formationId
     * @return  If registration was successful
     */
    @PostMapping("/refugees/{refugeeId}/formations/{formationId}/register")
    public ResponseEntity<?> registerRefugeeToFormation(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        boolean registrationSuccessful = formationService.registerRefugeeToFormation(refugeeId, formationId);

        if (registrationSuccessful) {
            return ResponseEntity.ok().body("Refugee successfully registered to the formation.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are already registered to a formation. ");
        }
    }

    /**
     *  Start a formation
     * @param formationId
     * @return  updated formation
     */
    @PostMapping("/{formationId}/start")
    public ResponseEntity<Formation> startFormation(@PathVariable Long formationId) {
        try {
            Formation startedFormation = formationService.startFormation(formationId);
            return new ResponseEntity<>(startedFormation, HttpStatus.OK);
        } catch (FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get formation by status
     * @param status
     * @return  List of formations with the given status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Formation>> getFormationByStatus(@PathVariable FormationStatus status) {
        try {
            List<Formation> formations = formationService.getFormationByStatus(status);
            return new ResponseEntity<>(formations, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     *  Complete a formation
     * @param formationId
     * @return  updated formation
     */
    @PostMapping("/{formationId}/complete")
    public ResponseEntity<Formation> completeFormation(@PathVariable Long formationId) {
        try {
            Formation completedFormation = formationService.completeFormation(formationId);
            return new ResponseEntity<>(completedFormation, HttpStatus.OK);
        } catch (FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get all formations by organization
     * @param organizationId
     * @return  List of formations with the given organization
     */
    @GetMapping("/formations-by-organization/{organizationId}")
    public ResponseEntity<List<Formation>> getFormationsByOrganizationId(@PathVariable Long organizationId) {
        List<Formation> formations = formationService.getFormationsByOrganizationId(organizationId);
        return ResponseEntity.ok(formations);
    }

    /**
     *  Get all formations by organization username
     * @param username
     * @return  List of formations with the given organization
     */
    @GetMapping("/formations-by-organization-username/{username}")
    public ResponseEntity<List<Formation>> getFormationsByOrganizationUsername(@PathVariable String username) {
        List<Formation> formations = formationService.getFormationsByOrganizationUsername(username);
        return ResponseEntity.ok(formations);
    }

    /**
     *  Change formation status
     * @param formationId
     * @param newStatus
     * @return  updated formation
     */
    @PutMapping("/{formationId}/status")
    public ResponseEntity<Void> changeFormationStatus(@PathVariable Long formationId, @RequestBody FormationStatus newStatus) {
        if (newStatus == null || !EnumUtils.isValidEnum(FormationStatus.class, newStatus.name())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            formationService.changeFormationStatus(formationId, newStatus);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
