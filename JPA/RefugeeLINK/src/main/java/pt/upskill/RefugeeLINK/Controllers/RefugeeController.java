package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.*;
import pt.upskill.RefugeeLINK.Exceptions.MentorIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.RefugeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Refugee controller
 */
@RestController
@RequestMapping("/refugee")
public class RefugeeController {

    @Autowired
    RefugeeService refugeeService;

    /**
     *  Get all refugees
     * @return  the list of all refugees
     */
    @GetMapping("/noDto")
    public ResponseEntity<List<Refugee>> getAllRefugeesNoDto() {
        List<Refugee> refugees = refugeeService.getAllRefugees();
        return ResponseEntity.ok(refugees);
    }

    /**
     *  Get a refugee
     * @param id
     * @return  the refugee with the given id
     */
    @GetMapping("/noDto/{id}")
    public ResponseEntity<Refugee> getRefugeeByIdNoDto(@PathVariable long id) {
        Refugee refugee = refugeeService.getRefugeeById(id);
        return new ResponseEntity<>(refugee, HttpStatus.OK);
    }

    /**
     *  Get all refugees
     * @return  the list of all refugees
     */
    @GetMapping
    public ResponseEntity<List<RefugeeDTO>> getAllRefugees() {
        // Retrieve all refugees
        List<Refugee> refugees = refugeeService.getAllRefugees();

        // Convert the list of refugees to DTOs
        List<RefugeeDTO> refugeeDTOs = refugees.stream()
                .map(Refugee::toDTO)
                .collect(Collectors.toList());

        // Return the list of DTOs
        return ResponseEntity.ok(refugeeDTOs);
    }

    /**
     *  Create a new refugee
     * @param refugee
     * @return  the added refugee
     */
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<RefugeeDTO> createRefugee(@RequestBody Refugee refugee) {
        // Create the new refugee
        Refugee createdRefugee = refugeeService.addRefugee(refugee);

        // Convert the created refugee to DTO
        RefugeeDTO refugeeDTO = createdRefugee.toDTO();

        // Return the created DTO
        return new ResponseEntity<>(refugeeDTO, HttpStatus.CREATED);
    }

    /**
     *  Get a refugee
     * @param id
     * @return  the refugee with the given id
     */
    @GetMapping("{id}")
    public ResponseEntity<RefugeeDTO> getRefugeeById(@PathVariable long id) {
        // Check if the refugee exists with the given id and return it
        Refugee refugee = refugeeService.getRefugeeById(id);

        // Convert the refugee to DTO
        RefugeeDTO refugeeDTO = refugee.toDTO();

        // Return the DTO
        return new ResponseEntity<>(refugeeDTO, HttpStatus.OK);
    }

    /**
     *  Delete a refugee
     * @param id
     * @return  True if the refugee was found and deleted. False otherwise.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRefugeeById(@PathVariable long id) {
        // Delete the refugee
        boolean deleted = refugeeService.deleteRefugeeById(id);

        // Check if the refugee was deleted
        if (deleted) {
            return new ResponseEntity<>("Refugee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Refugee not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  Update a refugee
     * @param id
     * @param refugee
     * @return  The updated refugee
     */
    @PutMapping("{id}")
    public ResponseEntity<RefugeeDTO> updateRefugee(@PathVariable long id, @RequestBody Refugee refugee) {
        // Get the refugee with the given id
        Refugee existingRefugee = refugeeService.getRefugeeById(id);

        // Check if the refugee exists with the given id and updates it
        if (existingRefugee != null) {
            Refugee updatedRefugee = refugeeService.updateRefugee(id, refugee);
            RefugeeDTO updatedRefugeeDTO = updatedRefugee.toDTO();
            return ResponseEntity.ok(updatedRefugeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Validate a refugee login
     * @param refugeeLoginDto
     * @return  True if the login is valid. False otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateRefugeeLogin(@RequestBody RefugeeLoginDto refugeeLoginDto) {
        // Check if the user exists
        Optional<Refugee> refugee = refugeeService.findRefugeeByUsername(refugeeLoginDto.getUserName());

        // Check if the user exists and the password is correct
        if (refugee.isPresent() && refugee.get().getPassword().equals(refugeeLoginDto.getPassword())) {
            return ResponseEntity.ok(true);
        }

        // Return false if the user does not exist or the password is incorrect
        return ResponseEntity.ok(false);
    }

    /**
     *  Select a mentor for a refugee
     * @param username
     * @param mentorId
     * @return  The updated refugee
     */
    @PutMapping("{username}/mentor")
    public ResponseEntity<String> selectMentorForRefugee(@PathVariable String username, @RequestParam Long mentorId) {
        try {
            refugeeService.selectMentorForRefugee(username, mentorId);
            return ResponseEntity.ok("Mentor selected for refugee");
        } catch (MentorIdNotFound e) {
            return ResponseEntity.badRequest().body("Error selecting mentor: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     *  Remove a mentor from a refugee
     * @param refugeeId
     * @return  The updated refugee
     */
    @DeleteMapping("/{refugeeId}/mentor")
    public ResponseEntity<String> removeMentorFromRefugee(@PathVariable Long refugeeId) {
        try {
            refugeeService.removeMentorFromRefugee(refugeeId);
            return ResponseEntity.ok("Mentor removed from refugee");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     *  Get a refugee by username
     * @param username
     * @return  The refugee with the given username
     */
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

    /**
     *  Get the mentor of a refugee
     * @param username
     * @return  The mentor of the refugee
     */
    @GetMapping("/by-username/{username}/mentor")
    public ResponseEntity<MentorDTO> getMentorOfRefugee(@PathVariable String username) {
        try {
            Mentor mentor = refugeeService.getMentorOfRefugee(username);
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get the mentor username of a refugee
     * @param username
     * @return  The mentor username of the refugee
     */
    @GetMapping("/by-username/{username}/mentor/username")
    public ResponseEntity<MentorUsernameDTO> getMentorUsernameOfRefugee(@PathVariable String username) {
        try {
            Mentor mentor = refugeeService.getMentorOfRefugee(username);
            MentorUsernameDTO mentorUsernameDTO = mentor.toUsernameDto();
            return new ResponseEntity<>(mentorUsernameDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get all refugees with a mentor with the given username
     * @param username
     * @return  The list of all refugees with the given mentor
     */
    @GetMapping("/with-mentor/{username}")
    public ResponseEntity<List<RefugeeMsgDTO>> getRefugeesByMentorUsername(@PathVariable String username) {
        List<Refugee> refugees = refugeeService.getRefugeesByMentorUsername(username);
        List<RefugeeMsgDTO> refugeeMsgDTOs = refugees.stream()
                .map(Refugee::toMsgDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(refugeeMsgDTOs);
    }

    /**
     *  Get the current formation of a refugee
     * @param username
     * @return  The current formation of the refugee
     */
    @GetMapping("/current-formation/{username}")
    public ResponseEntity<Formation> getCurrentFormation(@PathVariable String username) {
        try {
            Formation formation = refugeeService.getCurrentFormationByRefugee(username);
            return new ResponseEntity<>(formation, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get the id of a refugee by username
     * @param username
     * @return  The id of the refugee with the given username
     */
    @GetMapping("/id/{username}")
    public ResponseEntity<Long> getRefugeeIdByUsername(@PathVariable String username) {
        try {
            Long refugeeId = refugeeService.getRefugeeIdByUsername(username);
            return new ResponseEntity<>(refugeeId, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}