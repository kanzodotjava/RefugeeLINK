package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.RefugeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/refugee")
public class RefugeeController {

    @Autowired
    RefugeeService refugeeService;


    @GetMapping
    public ResponseEntity<List<RefugeeDTO>> getAllRefugees() {
        List<Refugee> refugees = refugeeService.getAllRefugees();
        List<RefugeeDTO> refugeeDTOs = refugees.stream()
                .map(Refugee::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(refugeeDTOs);
    }

    @PostMapping
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
}
