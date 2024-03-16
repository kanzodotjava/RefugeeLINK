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
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;
import java.util.Optional;

/**
 *  Mentor service
 */
@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;
    @Autowired
    RefugeeRepository refugeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //Constructor
    public  MentorService(MentorRepository mentorRepository){
        this.mentorRepository = mentorRepository;
    }

    /**
     *  Add new mentor.
     * @param mentor
     * @return  The added mentor.
     */
    public Mentor addMentor(Mentor mentor){

        //Check if mentor already exists
        if(mentorRepository.existsById(mentor.getId())){
            throw new DataIntegrityViolationException("Mentor with ID " + mentor.getId() + " already exists.");
        }

        //Check if username is valid
        String username = mentor.getUserName();
        if (username.length() < 6 || username.length() > 12 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username must be between 6 and 12 characters long and contain only letters and numbers.");
        }

        //Check if password is valid
        String password = mentor.getPassword();
        if (password.length() < 8 || password.length() > 50 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be between 8 and 50 characters long and contain at least one uppercase character.");
        }

        // Check if the email already exists
        String email = mentor.getEmailAddress();
        if (mentorRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered.");
        }
        if (refugeeRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered as a refugee.");
        }

        // Check if the username already exists
        if (mentorRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a mentor.");
        }
        if (refugeeRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a refugee.");
        }


        //Check if citizen card is valid
        int citCard = mentor.getCitizenCard();
        if(citCard < 100000000 || citCard > 999999999){
            throw new IllegalArgumentException("Citizen card number must be a 9-digit number");
        }

        //Return saved mentor
        return mentorRepository.save(mentor);
    }

    /**
     *  Get mentor by id.
     * @param id
     * @return  The mentor with the given id.
     */
    public Mentor getMentorById(Long id){
        //Check if mentor exists
        if(mentorRepository.existsById(id)){
            return mentorRepository.findById(id).orElseThrow();
        }

        //Throw exception if not
        throw new EntityNotFoundException("Mentor with id: " + id + " was not found!");
    }

    /**
     *  Get all mentors.
     * @return  The list of all mentors.
     */
    public List<Mentor> getAllMentors(){
        //Get all mentors
        List<Mentor> mentors = mentorRepository.findAll();

        //Check if list is empty
        if(mentors.isEmpty()){
            System.out.println("No mentors found!");
        }

        //Return list
        return mentors;
    }

    /**
     *  Delete mentor by id.
     * @param id
     * @return  True if the mentor was found and deleted. False otherwise.
     */
    public boolean deleteMentorById(Long id){
        //Check if mentor exists and deletes it
        if(mentorRepository.existsById(id)){
            mentorRepository.deleteById(id);
        }

        // Return false if not deleted
        return false;
    }

    /**
     *  Update mentor.
     * @param id
     * @param mentor
     * @return  The updated mentor.
     */
    public Mentor updateMentor(Long id,Mentor mentor){

        //Check if mentor exists and updates it
        if(mentorRepository.existsById(mentor.getId())){
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }

        //Throw exception if not found
        throw new EntityNotFoundException("Mentor with id: " + id + " was not found!");
    }

    /**
     *  Get mentor by status certified.
     * @return  The list of mentors with the given status.
     */
    public List<Mentor> getMentorByStatusCertified(){
        //Get all mentors that are certified
        return mentorRepository.getMentorByStatus(Status.CERTIFIED);
    }

    /**
     *  Get mentor by status awaiting.
     * @return  The list of mentors with the given status.
     */
    public List<Mentor> getMentorByStatusAwaiting(){
        //Get all mentors that are awaiting
        return mentorRepository.getMentorByStatus(Status.AWAITING);
    }

    /**
     *  Get mentor by username.
     * @param userName
     * @return  The mentor with the given username.
     */
    public Optional<Mentor> findMentorByUsername(String userName) {
        // Return the mentor with the given username
        return mentorRepository.findByUserName(userName);
    }

    /**
     *  Update mentor status
     * @param mentorId
     * @param newStatus
     * @return  The updated mentor
     */
    public Mentor updateStatus(Long mentorId, Status newStatus) {
        // Check if mentor exists and updates it status
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new EntityNotFoundException("Mentor with id: " + mentorId + " was not found!"));
        if (mentor != null) {
            mentor.setStatus(newStatus);
            return mentorRepository.save(mentor);
        }

        // Throw exception if not found
        throw new EntityNotFoundException("Mentor with id: " + mentorId + " was not updated!");
    }

    /**
     *  Get all refugees by mentor
     * @param mentorUsername
     * @return  The list of all refugees with the given mentor username.
     */
    public List<Refugee> getRefugeesByMentor(String mentorUsername) {

        // Get the mentor with the given username
        Mentor mentor = entityManager.createQuery(
                        "SELECT m FROM Mentor m WHERE m.userName = :mentorUsername", Mentor.class)
                .setParameter("mentorUsername", mentorUsername)
                .getSingleResult();

        // Throw exception if not found
        if (mentor == null) {
            throw new EntityNotFoundException("Mentor with username: " + mentorUsername + " not found");
        }

        // Get all refugees with the given mentor
        List<Refugee> refugees = entityManager.createQuery(
                        "SELECT r FROM Refugee r WHERE r.mentor.id = :mentorId", Refugee.class)
                .setParameter("mentorId", mentor.getId())
                .getResultList();

        // Throw exception if the mentor has no refugees
        if (refugees.isEmpty()) {
            throw new IllegalArgumentException("No refugees found for mentor with username: " + mentorUsername);
        }

        // Return the list of refugees with the given mentor
        return refugees;
    }

    /**
     *  Get mentor by username
     * @param userName
     * @return  The mentor with the given username
     */
    public Mentor getMentorByUsername(String userName) {

        // Return the mentor with the given username if it exists
        return mentorRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("Mentor not found with username: " + userName));
    }

    /**
     *  Get mentor rating by username
     * @param username
     * @return  The mentor rating
     */
    public double getMentorRatingByUsername(String username) {

        // Get the mentor with the given username
        Mentor mentor = mentorRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Mentor not found with username: " + username));

        // Return the mentor rating
        return mentor.getRating();
    }

    /**
     *  Update mentor rating
     * @param username
     * @param newRating
     * @return  The updated mentor
     */
    @Transactional
    public Mentor updateMentorRatingByUsername(String username, double newRating) {
        // Find the mentor with the given username
        Mentor mentor = mentorRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Mentor not found with username: " + username));

        // Update the mentor rating
        mentor.setRating(newRating);

        // Return the updated mentor
        return mentorRepository.save(mentor);
    }

    /**
     *  Get mentor status
     * @param username
     * @return  The mentor status
     */
    public Status getMentorStatusByUsername(String username) {

        // Get the mentor with the given username
        Mentor mentor = mentorRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Mentor not found with username: " + username));

        // Return the mentor status
        return mentor.getStatus();
    }
}



