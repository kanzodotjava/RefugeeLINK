package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeFormationDTO;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Services.RefugeeFormationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/RefugeeFormation")
public class RefugeeFormationController {

    @Autowired
    private RefugeeFormationService refugeeFormationService;

    @GetMapping("/all")
    public ResponseEntity<List<RefugeeFormationDTO>> getAllRefugeeFormations() {
        List<RefugeeFormation> refugeeFormations = refugeeFormationService.getAllRefugeeFormations();
        List<RefugeeFormationDTO> refugeeFormationDTOs = refugeeFormations.stream()
                .map(RefugeeFormation::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(refugeeFormationDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefugeeFormationDTO> getRefugeeFormationById(@PathVariable Long id) throws RefugeeFormationIdNotFound {
        RefugeeFormation refugeeFormation = refugeeFormationService.getRefugeeFormation(id);
        RefugeeFormationDTO refugeeFormationDTO = refugeeFormation.toDto();
        return new ResponseEntity<>(refugeeFormationDTO, HttpStatus.OK);
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

    @GetMapping("/completed/{refugeeId}")
    public ResponseEntity<List<RefugeeFormation>> getCompletedFormationsByRefugee(@PathVariable Long refugeeId) {
        List<RefugeeFormation> completedFormations = refugeeFormationService.getCompletedFormationsByRefugee(refugeeId);
        return new ResponseEntity<>(completedFormations, HttpStatus.OK);
    }
}