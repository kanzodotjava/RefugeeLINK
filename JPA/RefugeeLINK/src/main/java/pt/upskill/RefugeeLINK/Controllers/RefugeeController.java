package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;
import pt.upskill.RefugeeLINK.DTO.RefugeeLoginDto;
import pt.upskill.RefugeeLINK.Exceptions.MentorIdNotFound;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.RefugeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refugee")
public class RefugeeController {

    @Autowired
    RefugeeService refugeeService;


    //Basic CRUD for Refugee no DTO

    @GetMapping("/noDto")
    public ResponseEntity<List<Refugee>> getAllRefugeesNoDto() {
        List<Refugee> refugees = refugeeService.getAllRefugees();
        return ResponseEntity.ok(refugees);
    }

    @GetMapping("/noDto/{id}")
    public ResponseEntity<Refugee> getRefugeeByIdNoDto(@PathVariable long id) {
        Refugee refugee = refugeeService.getRefugeeById(id);
        return new ResponseEntity<>(refugee, HttpStatus.OK);
    }


    //Basic CRUD
    @GetMapping
    public ResponseEntity<List<RefugeeDTO>> getAllRefugees() {
        List<Refugee> refugees = refugeeService.getAllRefugees();
        List<RefugeeDTO> refugeeDTOs = refugees.stream()
                .map(Refugee::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(refugeeDTOs);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RefugeeDTO> createRefugee(@RequestBody Refugee refugee) {
        Refugee createdRefugee = refugeeService.addRefugee(refugee);
        RefugeeDTO refugeeDTO = createdRefugee.toDTO();
        return new ResponseEntity<>(refugeeDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RefugeeDTO> getRefugeeById(@PathVariable long id) {
        Refugee refugee = refugeeService.getRefugeeById(id);
        RefugeeDTO refugeeDTO = refugee.toDTO();
        return new ResponseEntity<>(refugeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRefugeeById(@PathVariable long id) {
        boolean deleted = refugeeService.deleteRefugeeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<RefugeeDTO> updateRefugee(@PathVariable long id, @RequestBody Refugee refugee) {
        Refugee existingRefugee = refugeeService.getRefugeeById(id);
        if (existingRefugee != null) {
            Refugee updatedRefugee = refugeeService.updateRefugee(id, refugee);
            RefugeeDTO updatedRefugeeDTO = updatedRefugee.toDTO();
            return ResponseEntity.ok(updatedRefugeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //Login
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateRefugeeLogin(@RequestBody RefugeeLoginDto refugeeLoginDto) {
        Optional<Refugee> refugee = refugeeService.findRefugeeByUsername(refugeeLoginDto.getUserName());
        if (refugee.isPresent() && refugee.get().getPassword().equals(refugeeLoginDto.getPassword())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }




    //Mentor selection and Removal
    @PutMapping("{refugeeId}/mentor")
    public ResponseEntity<String> selectMentorForRefugee(@PathVariable Long refugeeId, @RequestParam Long mentorId) {
        try {
            refugeeService.selectMentorForRefugee(refugeeId, mentorId);
            return ResponseEntity.ok("Mentor selected for refugee");
        } catch (MentorIdNotFound e) {
            return ResponseEntity.badRequest().body("Error selecting mentor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/mentor")
    public ResponseEntity<String> removeMentorFromRefugee(@PathVariable Long refugeeId) {
        try {
            refugeeService.removeMentorFromRefugee(refugeeId);
            return ResponseEntity.ok("Mentor removed from refugee");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<RefugeeDTO> getRefugeeByUsername(@PathVariable String username) {
        try {
            Refugee refugee = refugeeService.getRefugeeByUsername(username);
            RefugeeDTO refugeeDTO = refugee.toDTO();
            return new ResponseEntity<>(refugeeDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}