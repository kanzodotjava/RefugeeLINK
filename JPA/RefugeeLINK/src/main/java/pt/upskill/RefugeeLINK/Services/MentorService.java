package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    public Mentor addMentor(Mentor mentor){
        if(mentorRepository.existsById(mentor.getId())){
            throw new DataIntegrityViolationException("Mentor with ID " + mentor.getId() + " already exists.");
        }
        return mentorRepository.save(mentor);
    }

    public Mentor getMentorById(Long id){
        if(mentorRepository.existsById(id)){
            return mentorRepository.findById(id).orElseThrow();
        }
        throw new EntityNotFoundException("Mentor with id: " + id + " was not found!");
    }

    public List<Mentor> getAllMentors(){
        List<Mentor> mentors = mentorRepository.findAll();
        if(mentors.isEmpty()){
            System.out.println("No mentors found!");
        }
        return mentors;
    }

    public boolean deleteMentorById(Long id){
        if(mentorRepository.existsById(id)){
            mentorRepository.deleteById(id);
        }
        return false;
    }

    public Mentor updateMentor(Long id,Mentor mentor){
        if(mentorRepository.existsById(mentor.getId())){
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new EntityNotFoundException();
    }



}
