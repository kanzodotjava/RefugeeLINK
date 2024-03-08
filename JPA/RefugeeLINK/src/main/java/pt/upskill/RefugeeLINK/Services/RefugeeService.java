package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.MentorAlreadySelectedException;
import pt.upskill.RefugeeLINK.Exceptions.MentorIdNotFound;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RefugeeService {

    @Autowired
    RefugeeRepository refugeeRepository;

    @Autowired
    MentorRepository mentorRepository;



    public RefugeeService(RefugeeRepository refugeeRepository) {
        this.refugeeRepository = refugeeRepository;
    }
    public Refugee addRefugee(Refugee refugee) {
        if (refugeeRepository.existsById(refugee.getId())) {
            throw new DataIntegrityViolationException("Refugee with ID " + refugee.getId() + " already exists.");
        }
        String username = refugee.getUserName();
        if (username.length() < 6 || username.length() > 12 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username must be between 6 and 12 characters long and contain only letters and numbers.");
        }
        String email = refugee.getEmailAddress();
        if (refugeeRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered.");
        }

        String password = refugee.getPassword();
        if (password.length() < 8 || password.length() > 50 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be between 8 and 50 characters long and contain at least one uppercase character.");
        }

        int citCard = refugee.getCitizenCard();
        if(citCard < 100000000 || citCard > 999999999){
            throw new IllegalArgumentException("Citizen card number must be a 9-digit number");
        }

        if (mentorRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a mentor.");
        }

        if (mentorRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered as a mentor.");
        }

        return refugeeRepository.save(refugee);
    }

    public Refugee getRefugeeById(Long id) {
        return refugeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refugee with id: " + id + " was not found!"));
    }

    public List<Refugee> getAllRefugees() {
        List<Refugee> refugees = refugeeRepository.findAll();
        if (refugees.isEmpty()) {
            System.out.println("No refugees found!");
        }
        return refugees;
    }

    public boolean deleteRefugeeById(Long id) {
        if (refugeeRepository.existsById(id)) {
            refugeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Refugee updateRefugee(Long id, Refugee refugee) {
        if (refugeeRepository.existsById(id)) {
            refugee.setId(id);
            return refugeeRepository.save(refugee);
        }
        throw new EntityNotFoundException("Refugee with id: " + id + " was not found!");
    }

    public Optional<Refugee> findRefugeeByUsername(String userName) {
        return refugeeRepository.findByUserName(userName);
    }

    @Transactional
    public void selectMentorForRefugee(String username, Long mentorId) throws MentorIdNotFound, MentorAlreadySelectedException {

        // Retrieve the refugee object
        Refugee refugee = getRefugeeByUsername(username);



        if (refugee.getMentor() != null) {
            throw new MentorAlreadySelectedException("Refugee " + username + " already has a mentor.");
        }
        // Retrieve the mentor object
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new MentorIdNotFound("Mentor with id " + mentorId + " not found"));

        // Update the mentor association for the refugee
        refugee.setMentor(mentor);
        mentor.setRefugee(refugee);
        // Save the updated refugee
        refugeeRepository.save(refugee);
    }

    @Transactional
    public void removeMentorFromRefugee(Long refugeeId) {

        // Retrieve the refugee object
        Refugee refugee = getRefugeeById(refugeeId);

        // Remove the mentor association from the refugee
        refugee.setMentor(null);

        // Save the updated refugee
        refugeeRepository.save(refugee);
    }


    public Refugee getRefugeeByUsername(String userName) {

        return refugeeRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("Refugee not found with username: " + userName));
    }

}
