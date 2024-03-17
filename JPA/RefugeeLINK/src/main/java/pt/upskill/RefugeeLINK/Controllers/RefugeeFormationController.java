package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeFormationDTO;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Services.RefugeeFormationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Controller for RefugeeFormation
 */
@RestController
@RequestMapping("/RefugeeFormation")
public class RefugeeFormationController {

    @Autowired
    private RefugeeFormationService refugeeFormationService;

    /**
     *  Get all formations for all refugees
     * @return  List of all refugees formations
     */
    @GetMapping("/all")
    public ResponseEntity<List<RefugeeFormationDTO>> getAllRefugeeFormations() {
        List<RefugeeFormation> refugeeFormations = refugeeFormationService.getAllRefugeeFormations();
        List<RefugeeFormationDTO> refugeeFormationDTOs = refugeeFormations.stream()
                .map(RefugeeFormation::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(refugeeFormationDTOs, HttpStatus.OK);
    }

    /**
     *  Get a formation by id
     * @param id
     * @return
     * @throws RefugeeFormationIdNotFound
     */
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

    @PutMapping("/toggleApproval/{refugeeId}/{formationId}")
    public ResponseEntity<Void> toggleApprovalStatus(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        try {
            refugeeFormationService.toggleApprovalStatus(refugeeId, formationId);
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


    @GetMapping("/refugees-by-formation/{formationId}")
    public ResponseEntity<List<Refugee>> getRefugeesByFormationId(@PathVariable Long formationId) {
        List<Refugee> refugees = refugeeFormationService.getRefugeesByFormationId(formationId);
        return ResponseEntity.ok(refugees);
    }

    @GetMapping("/isApproved/{refugeeId}/{formationId}")
    public ResponseEntity<Boolean> isRefugeeFormationApproved(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        try {
            boolean isApproved = refugeeFormationService.isRefugeeFormationApproved(refugeeId, formationId);
            return new ResponseEntity<>(isApproved, HttpStatus.OK);
        } catch (RefugeeFormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/expel/{refugeeId}/{formationId}")
    public ResponseEntity<Void> deleteRefugeeFormation(@PathVariable Long refugeeId, @PathVariable Long formationId) {
        try {
            refugeeFormationService.deleteRefugeeFormation(refugeeId, formationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RefugeeFormationIdNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}