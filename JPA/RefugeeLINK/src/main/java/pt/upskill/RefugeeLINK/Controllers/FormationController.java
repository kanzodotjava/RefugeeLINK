package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
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

    @PostMapping("/register")
    public ResponseEntity<Formation> registerFormation(@RequestBody Formation formation) {
        Formation registeredFormation = formationService.registerFormation(formation);
        return new ResponseEntity<>(registeredFormation, HttpStatus.CREATED);
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

}
