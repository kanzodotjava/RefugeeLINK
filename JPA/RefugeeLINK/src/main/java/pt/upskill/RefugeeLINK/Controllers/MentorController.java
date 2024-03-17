package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;

import pt.upskill.RefugeeLINK.DTO.MentorRatingDTO;
import pt.upskill.RefugeeLINK.DTO.MentorUsernameDTO;
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;

import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.MentorService;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

/**
 * Mentor controller
 */
@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;

    /**
     *  Get all mentors.
     * @return  The list of all mentors.
     */
    @GetMapping
    public ResponseEntity<List<MentorDTO>> getAllMentors(){
        //Get all mentors
        List<Mentor> mentors = mentorService.getAllMentors();

        // Converts all mentors to DTOs
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());

        // Returns the DTOs
        return ResponseEntity.ok(mentorDTOs);
    }

    /**
     *  Create a new mentor
     * @param mentor
     * @return  The created mentor
     */
    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@RequestBody Mentor mentor){
        try {
            // Create the mentor
            Mentor createdMentor = mentorService.addMentor(mentor);

            // Converts the created mentor to DTO
            MentorDTO mentorDTO = createdMentor.toDTO();

            // Returns the DTO
            return new ResponseEntity<>(mentorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *  Get mentor by id.
     * @param id
     * @return  The mentor with the given id.
     */
    @GetMapping("{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable long id){
        try {
            //Get mentor by id
            Mentor mentor = mentorService.getMentorById(id);

            // Converts mentor to DTO
            MentorDTO mentorDTO = mentor.toDTO();

            // Returns the DTO
            return new ResponseEntity<>(mentorDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Delete mentor by id.
     * @param id
     * @return  True if the mentor was found and deleted. False otherwise.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMentorById(@PathVariable long id) {
        // Delete mentor
        boolean deleted = mentorService.deleteMentorById(id);

        // Check if mentor was deleted
        if (deleted) {
            return new ResponseEntity<>("Mentor deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mentor not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  Update mentor.
     * @param id
     * @param mentor
     * @return  The updated mentor.
     */
    @PutMapping("{id}")
    public ResponseEntity<MentorDTO> updateMentor(@PathVariable long id, @RequestBody Mentor mentor) {
        // Check if mentor exists
        Mentor existingMentor = mentorService.getMentorById(id);

        // Update mentor
        if (existingMentor != null) {
            Mentor updatedMentor = mentorService.updateMentor(id, mentor);
            MentorDTO updatedMentorDTO = updatedMentor.toDTO();
            return ResponseEntity.ok(updatedMentorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Validate mentor login
     * @param mentorLoginDTO
     * @return  True if the mentor exists and the password is correct. False otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateMentorLogin(@RequestBody MentorLoginDTO mentorLoginDTO) {
        // Check if mentor exists
        Optional<Mentor> mentor = mentorService.findMentorByUsername(mentorLoginDTO.getUserName());

        // Check if password is correct
        if (mentor.isPresent() && mentor.get().getPassword().equals(mentorLoginDTO.getPassword())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    /**
     *  Get all certified mentors
     * @return  The list of all certified mentors
     */
    @GetMapping("/certified")
    public ResponseEntity<List<MentorDTO>> getMentorByStatusCertified() {
        //Get all certified mentors
        List<Mentor> mentors = mentorService.getMentorByStatusCertified();

        // Converts all mentors to DTOs
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());

        // Returns the DTOs
        return new ResponseEntity<>(mentorDTOs, HttpStatus.OK);
    }

    /**
     *  Get all awaiting mentors
     * @return  The list of all awaiting mentors
     */
    @GetMapping("/awaiting")
    public ResponseEntity<List<MentorDTO>> getMentorByStatusAwaiting() {
        //Get all awaiting mentors
        List<Mentor> mentors = mentorService.getMentorByStatusAwaiting();

        // Converts all mentors to DTOs
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());

        // Returns the DTOs
        return new ResponseEntity<>(mentorDTOs, HttpStatus.OK);
    }

    /**
     *  Certify a mentor
     * @param mentorId
     * @return  The updated mentor
     */
    @PutMapping("/{mentorId}/certify")
    public ResponseEntity<MentorDTO> certifyMentor(@PathVariable Long mentorId) {
        // Check if mentor exists and updates it status
        Mentor mentor = mentorService.updateStatus(mentorId, Status.CERTIFIED);

        // Returns the updated mentor or 404 if not found
        if (mentor != null) {
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Reject a mentor
     * @param mentorId
     * @return  The updated mentor
     */
    @PutMapping("/{mentorId}/reject")
    public ResponseEntity<MentorDTO> rejectMentor(@PathVariable Long mentorId) {
        // Check if mentor exists and updates it status
        Mentor mentor = mentorService.updateStatus(mentorId, Status.REJECTED);

        // Returns the updated mentor or 404 if not found
        if (mentor != null) {
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get mentor by username
     * @param username
     * @return  The mentor with the given username
     */
    @GetMapping("/by-username/{username}")
    public ResponseEntity<MentorDTO> getMentorByUsername(@PathVariable String username) {
        // Get mentor by username
        try {
            // Check if mentor exists
            Mentor mentor = mentorService.getMentorByUsername(username);

            // Converts mentor to DTO
            MentorDTO mentorDTO = mentor.toDTO();

            // Returns the DTO
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Get all refugees by mentor
     * @param username
     * @return  The list of all refugees with the given mentor
     */
    @GetMapping("/{username}/refugees")
    public ResponseEntity<List<Refugee>> getRefugeesByMentor(@PathVariable String username) {
        // Get all refugees with the given mentor
        List<Refugee> refugees = mentorService.getRefugeesByMentor(username);

        // Returns the list
        return new ResponseEntity<>(refugees, HttpStatus.OK);
    }


    /**
     *  Get all mentor tiers
     * @return  The list of all mentor tiers
     */
    @GetMapping("/only-usernames")
    public ResponseEntity<List<MentorUsernameDTO>> getAllMentorTiers() {
        //Get all mentors
        List<Mentor> mentors = mentorService.getAllMentors();

        // Converts all mentors to DTOs
        List<MentorUsernameDTO> mentorUsername = mentors.stream()
                .map(Mentor::toUsernameDto)
                .collect(Collectors.toList());

        // Returns the DTOs
        return new ResponseEntity<>(mentorUsername, HttpStatus.OK);
    }


    /**
     *  Get mentor rating by username
     * @param username
     * @return  The mentor rating
     */
    @GetMapping("/get-rating/{username}")
    public ResponseEntity<Double> getMentorRatingByUsername(@PathVariable String username) {
        // Get the mentor rating with the given username
        double mentorRating = mentorService.getMentorRatingByUsername(username);

        // Returns the rating
        return ResponseEntity.ok(mentorRating);
    }

    /**
     *  Update mentor rating
     * @param username
     * @param newRating
     * @return  The updated mentor
     */
    @PutMapping("/update-rating/{username}/{newRating}")
    public ResponseEntity<Mentor> updateMentorRatingByUsername(@PathVariable String username, @PathVariable double newRating) {
        Mentor updatedMentor = mentorService.updateMentorRatingByUsername(username, newRating);
        return ResponseEntity.ok(updatedMentor);
    }

    /**
     *  Get mentor status
     * @param username
     * @return  The mentor status
     */
    @GetMapping("/status/{username}")
    public ResponseEntity<Status> getMentorStatusByUsername(@PathVariable String username) {
        // Get the mentor status
        Status status = mentorService.getMentorStatusByUsername(username);

        // Returns the status
        return ResponseEntity.ok(status);
    }

}
