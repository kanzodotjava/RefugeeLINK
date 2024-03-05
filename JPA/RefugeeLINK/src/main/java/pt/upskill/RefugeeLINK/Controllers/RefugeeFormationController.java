package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Services.RefugeeFormationService;

import java.util.List;

@RestController
@RequestMapping("/RefugeeFormation")
public class RefugeeFormationController {

    @Autowired
    private RefugeeFormationService refugeeFormationService;

    @GetMapping("/all")
    public ResponseEntity<List<RefugeeFormation>> getAllRefugeeFormations() {
        List<RefugeeFormation> refugeeFormations = refugeeFormationService.getAllRefugeeFormations();
        return new ResponseEntity<>(refugeeFormations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefugeeFormation> getRefugeeFormationById(@PathVariable Long id) throws RefugeeFormationIdNotFound {
        RefugeeFormation refugeeFormation = refugeeFormationService.getRefugeeFormation(id);
        return new ResponseEntity<>(refugeeFormation, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RefugeeFormation> registerRefugeeFormation(@RequestBody RefugeeFormation refugeeFormation) {
        RefugeeFormation registeredRefugeeFormation = refugeeFormationService.addRefugeeFormation(refugeeFormation);
        return new ResponseEntity<>(registeredRefugeeFormation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<RefugeeFormation> updateRefugeeFormation(@RequestBody RefugeeFormation refugeeFormation) throws RefugeeFormationIdNotFound {
        RefugeeFormation updatedRefugeeFormation = refugeeFormationService.updateRefugeeFormation(refugeeFormation);
        return new ResponseEntity<>(updatedRefugeeFormation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRefugeeFormation(@PathVariable Long id) throws RefugeeFormationIdNotFound {
        refugeeFormationService.deleteRefugeeFormation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/approve/{refugeeId}/{formationId}")
    public ResponseEntity<Void> approveRefugeeFormation(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        try {
            refugeeFormationService.approveRefugeeFormation(refugeeId, formationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RefugeeFormationIdNotFound | RefugeeIdNotFound | FormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}