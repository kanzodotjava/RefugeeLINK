package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.DTO.MentorLoginDTO;
import pt.upskill.RefugeeLINK.DTO.MentorTierDTO;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Person;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.MentorService;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;



    @GetMapping
    public ResponseEntity<List<MentorDTO>> getAllMentors(){
        List<Mentor> mentors = mentorService.getAllMentors();
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mentorDTOs);
    }

    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@RequestBody Mentor mentor){
        Mentor createdMentor = mentorService.addMentor(mentor);
        MentorDTO mentorDTO = createdMentor.toDTO();
        return new ResponseEntity<>(mentorDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<MentorDTO> getMentorById(@PathVariable long id){
        Mentor mentor = mentorService.getMentorById(id);
        MentorDTO mentorDTO = mentor.toDTO();
        return new ResponseEntity<>(mentorDTO,HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMentorById(@PathVariable long id) {
        boolean deleted = mentorService.deleteMentorById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<MentorDTO> updateMentor(@PathVariable long id, @RequestBody Mentor mentor) {
        Mentor existingMentor = mentorService.getMentorById(id);
        if (existingMentor != null) {
            Mentor updatedMentor = mentorService.updateMentor(id, mentor);
            MentorDTO updatedMentorDTO = updatedMentor.toDTO();
            return ResponseEntity.ok(updatedMentorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> validateMentorLogin(@RequestBody MentorLoginDTO mentorLoginDTO) {
        Optional<Mentor> mentor = mentorService.findMentorByUsername(mentorLoginDTO.getUserName());
        if (mentor.isPresent() && mentor.get().getPassword().equals(mentorLoginDTO.getPassword())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }


    @GetMapping("/certified")
    public ResponseEntity<List<MentorDTO>> getMentorByStatusCertified() {
        List<Mentor> mentors = mentorService.getMentorByStatusCertified();
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mentorDTOs, HttpStatus.OK);
    }


    @GetMapping("/awaiting")
    public ResponseEntity<List<MentorDTO>> getMentorByStatusAwaiting() {
        List<Mentor> mentors = mentorService.getMentorByStatusAwaiting();
        List<MentorDTO> mentorDTOs = mentors.stream()
                .map(Mentor::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(mentorDTOs, HttpStatus.OK);
    }


    @PutMapping("/{mentorId}/certify")
    public ResponseEntity<MentorDTO> certifyMentor(@PathVariable Long mentorId) {
        Mentor mentor = mentorService.updateStatus(mentorId, Status.CERTIFIED);
        if (mentor != null) {
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{mentorId}/reject")
    public ResponseEntity<MentorDTO> rejectMentor(@PathVariable Long mentorId) {
        Mentor mentor = mentorService.updateStatus(mentorId, Status.REJECTED);
        if (mentor != null) {
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/by-username/{username}")
    public ResponseEntity<MentorDTO> getMentorByUsername(@PathVariable String username) {
        try {
            Mentor mentor = mentorService.getMentorByUsername(username);
            MentorDTO mentorDTO = mentor.toDTO();
            return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}/refugees")
    public ResponseEntity<List<Refugee>> getRefugeesByMentor(@PathVariable String username) {
        List<Refugee> refugees = mentorService.getRefugeesByMentor(username);
        return new ResponseEntity<>(refugees, HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> validateLogin(@RequestBody MentorLoginDTO mentorLoginDTO) {
//        Optional<Mentor> mentor = mentorService.findMentorByUsername(mentorLoginDTO.getUserName());
//        if (mentor.isPresent()) {
//
//            String hashedInputPassword = mentorService.hashPassword(mentorLoginDTO.getPassword());
//
//            if (hashedInputPassword.equals(mentor.get().getPassword())) {
//                return ResponseEntity.ok(true);
//            }
//        }
//        return ResponseEntity.ok(false);
//    }

    //gets MenTor and returns MentoTierDto
    @GetMapping("/tier/{username}")
    public ResponseEntity<MentorTierDTO> getMentorTier(@PathVariable String username){
        Mentor mentor = mentorService.getMentorByUsername(username);
        MentorTierDTO mentorTierDTO = mentor.toTierDto();
        return new ResponseEntity<>(mentorTierDTO,HttpStatus.OK);
    }
}
