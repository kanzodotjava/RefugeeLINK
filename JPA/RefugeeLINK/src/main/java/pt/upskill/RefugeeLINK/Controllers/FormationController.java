package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.FormationDTO;
import pt.upskill.RefugeeLINK.Enums.FormationStatus;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Services.FormationService;


import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/all")
    public ResponseEntity<List<Formation>> getAllFormations() {
        List<Formation> formations = formationService.getAllFormations();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) throws FormationIdNotFound {
        Formation formation = formationService.getFormationById(id);
        return new ResponseEntity<>(formation, HttpStatus.OK);
    }

//    @PostMapping("/register")
//    public ResponseEntity<FormationDTO> registerFormation(@RequestBody Formation formation) {
//        Formation createdFormation = formationService.registerFormation(formation);
//        FormationDTO formationDto = createdFormation.toDto();
//        return new ResponseEntity<>(formationDto, HttpStatus.CREATED);
//    }


    @PostMapping("/register")
    public String  registerFormation(@RequestBody Formation formation) {
        formationService.registerFormation(formation);
        return formation.getName();
    }
    @PutMapping("/update")
    public ResponseEntity<Formation> updateFormation(@RequestBody Formation formation) throws FormationIdNotFound {
        Formation updatedFormation = formationService.updateFormation(formation);
        return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) throws FormationIdNotFound {
        formationService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{name}/organization/{orgId}")
    public ResponseEntity<Formation> updateOrganizationId(@PathVariable String name, @PathVariable Long orgId) {
        try {
            Formation updatedFormation = formationService.updateOrganization(name, orgId);
            return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
        } catch (FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/refugees/{refugeeId}/formations/{formationId}/register")
    public ResponseEntity<?> registerRefugeeToFormation(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        boolean registrationSuccessful = formationService.registerRefugeeToFormation(refugeeId, formationId);

        if (registrationSuccessful) {
            return ResponseEntity.ok().body("Refugee successfully registered to the formation.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You register to this formation. Please check the formation status and if you are already registered in an active formation.");
        }
    }

    @PostMapping("/{formationId}/start")
    public ResponseEntity<Formation> startFormation(@PathVariable Long formationId) {
        try {
            Formation startedFormation = formationService.startFormation(formationId);
            return ResponseEntity.ok(startedFormation);
        } catch (FormationIdNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<Formation>> getFormationByStatus(@PathVariable FormationStatus status) {
        try {
            List<Formation> formations = formationService.getFormationByStatus(status);
            return new ResponseEntity<>(formations, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @PostMapping("/{formationId}/complete")
    public ResponseEntity<Formation> completeFormation(@PathVariable Long formationId) {
        try {
            Formation completedFormation = formationService.completeFormation(formationId);
            return ResponseEntity.ok(completedFormation);
        } catch (FormationIdNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

}
