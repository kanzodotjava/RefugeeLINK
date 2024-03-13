package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.DTO.MentorRatingDTO;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;
    @Autowired
    RefugeeRepository refugeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public  MentorService(MentorRepository mentorRepository){
        this.mentorRepository = mentorRepository;
    }

    public Mentor addMentor(Mentor mentor){
        if(mentorRepository.existsById(mentor.getId())){
            throw new DataIntegrityViolationException("Mentor with ID " + mentor.getId() + " already exists.");
        }
        String username = mentor.getUserName();
        if (username.length() < 6 || username.length() > 12 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username must be between 6 and 12 characters long and contain only letters and numbers.");
        }

        String password = mentor.getPassword();
        if (password.length() < 8 || password.length() > 50 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be between 8 and 50 characters long and contain at least one uppercase character.");
        }

        String email = mentor.getEmailAddress();
        if (mentorRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered.");
        }

        int citCard = mentor.getCitizenCard();
        if(citCard < 100000000 || citCard > 999999999){
            throw new IllegalArgumentException("Citizen card number must be a 9-digit number");
        }

        if (refugeeRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a refugee.");
        }

        if (refugeeRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered as a refugee.");
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

    public List<Mentor> getMentorByStatusCertified(){
        return mentorRepository.getMentorByStatus(Status.CERTIFIED);
    }

    public List<Mentor> getMentorByStatusAwaiting(){
        return mentorRepository.getMentorByStatus(Status.AWAITING);
    }

    public Optional<Mentor> findMentorByUsername(String userName) {
        return mentorRepository.findByUserName(userName);
    }


    public Mentor updateStatus(Long mentorId, Status newStatus) {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        if (mentor != null) {
            mentor.setStatus(newStatus);
            return mentorRepository.save(mentor);
        }
        return null;
    }

    public List<Refugee> getRefugeesByMentor(String mentorUsername) {
        Mentor mentor = entityManager.createQuery(
                        "SELECT m FROM Mentor m WHERE m.userName = :mentorUsername", Mentor.class)
                .setParameter("mentorUsername", mentorUsername)
                .getSingleResult();

        if (mentor == null) {
            throw new EntityNotFoundException("Mentor with username: " + mentorUsername + " not found");
        }

        List<Refugee> refugees = entityManager.createQuery(
                        "SELECT r FROM Refugee r WHERE r.mentor.id = :mentorId", Refugee.class)
                .setParameter("mentorId", mentor.getId())
                .getResultList();

        if (refugees.isEmpty()) {
            throw new IllegalArgumentException("No refugees found for mentor with username: " + mentorUsername);
        }

        return refugees;
    }

    public Mentor getMentorByUsername(String userName) {

        return mentorRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("Mentor not found with username: " + userName));
    }

    public MentorRatingDTO getMentorRatingByUsername(String username) {
        Mentor mentor = mentorRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Mentor not found with username: " + username));
        MentorRatingDTO mentorRatingDTO = new MentorRatingDTO();
        mentorRatingDTO.setRating(mentor.getRating());
        return mentorRatingDTO;
    }

    @Transactional
    public Mentor updateMentorRatingByUsername(String username, double newRating) {
        Mentor mentor = mentorRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Mentor not found with username: " + username));
        mentor.setRating(newRating);
        return mentorRepository.save(mentor);
    }


}



