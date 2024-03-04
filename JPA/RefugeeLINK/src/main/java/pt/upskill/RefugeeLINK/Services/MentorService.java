package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;


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
        return null;
    }


}
