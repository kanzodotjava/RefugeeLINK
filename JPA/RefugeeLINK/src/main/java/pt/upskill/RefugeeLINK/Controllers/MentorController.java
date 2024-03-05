package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Services.MentorService;

import java.util.List;
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
}
