package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.MentorAlreadySelectedException;
import pt.upskill.RefugeeLINK.Exceptions.MentorIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Refugee service
 */
@Service
public class RefugeeService {

    @Autowired
    RefugeeRepository refugeeRepository;

    @Autowired
    MentorRepository mentorRepository;


    /**
     *  Constructor
     * @param refugeeRepository
     */
    public RefugeeService(RefugeeRepository refugeeRepository) {
        this.refugeeRepository = refugeeRepository;
    }

    /**
     *  Add a new refugee
     * @param refugee
     * @return  the added refugee
     */
    public Refugee addRefugee(Refugee refugee) {

        // Check if the refugee already exists
        if (refugeeRepository.existsById(refugee.getId())) {
            throw new DataIntegrityViolationException("Refugee with ID " + refugee.getId() + " already exists.");
        }

        // Check if the username is valid
        String username = refugee.getUserName();
        if (username.length() < 6 || username.length() > 12 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username must be between 6 and 12 characters long and contain only letters and numbers.");
        }

        // Check if the email already exists
        String email = refugee.getEmailAddress();
        if (refugeeRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered.");
        }
        if (mentorRepository.existsByEmailAddress(email)) {
            throw new DataIntegrityViolationException("Email address " + email + " is already registered as a mentor.");
        }

        // Check if the username already exists
        if (refugeeRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a refugee.");
        }
        if (mentorRepository.existsByUserName(username)) {
            throw new DataIntegrityViolationException("Username " + username + " is already registered as a mentor.");
        }

        // Check if the password is valid
        String password = refugee.getPassword();
        if (password.length() < 8 || password.length() > 50 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be between 8 and 50 characters long and contain at least one uppercase character.");
        }

        // Check if the citizen card is valid
        int citCard = refugee.getCitizenCard();
        if(citCard < 100000000 || citCard > 999999999){
            throw new IllegalArgumentException("Citizen card number must be a 9-digit number");
        }

        // Return the added refugee after saving it
        return refugeeRepository.save(refugee);
    }

    /**
     *  Get a refugee.
     * @param id
     * @return  the refugee with the given id.
     */
    public Refugee getRefugeeById(Long id) {
        // Check if the refugee exists with the given id and return it
        return refugeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Refugee with id: " + id + " was not found!"));
    }

    /**
     *  Get all refugees.
     * @return  the list of all refugees.
     */
    public List<Refugee> getAllRefugees() {
        // Retrieve all refugees
        List<Refugee> refugees = refugeeRepository.findAll();

        // Check if the list is empty
        if (refugees.isEmpty()) {
            System.out.println("No refugees found!");
        }

        // Return the list of all refugees
        return refugees;
    }

    /**
     *  Delete a refugee
     * @param id
     * @return  True if the refugee was found and deleted. False otherwise.
     */
    public boolean deleteRefugeeById(Long id) {

        // Check if the refugee exists with the given id
        if (refugeeRepository.existsById(id)) {
            refugeeRepository.deleteById(id);
            return true;
        }

        // Return false if the refugee was not deleted
        return false;
    }

    /**
     *  Update a refugee.
     * @param id The id of the refugee we want to update.
     * @param refugee The new data of the refugee.
     * @return  The updated refugee.
     */
    public Refugee updateRefugee(Long id, Refugee refugee) {

        // Check if the refugee exists with the given id and updates it
        if (refugeeRepository.existsById(id)) {
            refugee.setId(id);
            return refugeeRepository.save(refugee);
        }

        throw new EntityNotFoundException("Refugee with id: " + id + " was not found!");
    }

    /**
     *  Find a refugee by username.
     * @param userName  The username of the refugee we want to find.
     * @return  The refugee with the given username.
     */
    public Optional<Refugee> findRefugeeByUsername(String userName) {
        // Return the refugee with the given username
        return refugeeRepository.findByUserName(userName);
    }

    /**
     *  Select a mentor for a refugee.
     * @param username  The username of the refugee.
     * @param mentorId  The id of the mentor.
     * @throws MentorIdNotFound Thrown if the mentor with the given id is not found.
     * @throws MentorAlreadySelectedException Thrown if the refugee already has a mentor.
     */
    @Transactional
    public void selectMentorForRefugee(String username, Long mentorId) throws MentorIdNotFound, MentorAlreadySelectedException {

        // Retrieve the refugee object
        Refugee refugee = getRefugeeByUsername(username);

        // Check if the refugee already has a mentor
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

    /**
     *  Remove the mentor association from a refugee.
     *
     * @param refugeeId The id of the refugee.
     */
    @Transactional
    public void removeMentorFromRefugee(Long refugeeId) {

        // Retrieve the refugee object
        Refugee refugee = getRefugeeById(refugeeId);

        // Remove the mentor association from the refugee
        refugee.setMentor(null);

        // Save the updated refugee
        refugeeRepository.save(refugee);
    }

    /**
     *  Get a refugee by username.
     * @param userName  The username of the refugee.
     * @return  The refugee with the given username.
     */
    public Refugee getRefugeeByUsername(String userName) {
        // Check if the refugee exists
        if (!refugeeRepository.existsByUserName(userName)) {
            throw new RuntimeException("Refugee not found with username: " + userName);
        }
        // Retrieve the refugee object
        return refugeeRepository.findByUserName(userName).orElseThrow(() -> new RuntimeException("Refugee not found with username: " + userName));
    }

    /**
     *  Get the mentor of a refugee
     * @param username  The username of the refugee.
     * @return  The mentor of the refugee.
     */
    public Mentor getMentorOfRefugee(String username) {
        // Check if the refugee exists
        if (!refugeeRepository.existsByUserName(username)) {
            throw new RuntimeException("Refugee not found with username: " + username);
        }

        // Retrieve the refugee object
        Refugee refugee = getRefugeeByUsername(username);

        // Return the mentor of the refugee
        return refugee.getMentor();
    }

    /**
     *  Get all refugees by mentor username.
     * @param mentorUsername
     * @return  The list of all refugees with the given mentor username.
     */
    public List<Refugee> getRefugeesByMentorUsername(String mentorUsername) {
        // Retrieve all refugees
        List<Refugee> refugees = getAllRefugees();

        // Filter the refugees by mentor username
        List<Refugee> matchedRefugees = new ArrayList<>();
        for (Refugee refugee : refugees) {
            Mentor mentor = refugee.getMentor();
            if (mentor != null && mentor.getUserName().equals(mentorUsername)) {
                matchedRefugees.add(refugee);
            }
        }

        // Return the filtered list
        return matchedRefugees;
    }

    /**
     *  Get the formation of a refugee.
     * @param username
     * @return  The formation of the refugee.
     */
    public Formation getRefugeeFormationByUsername(String username) {
        // Retrieve the refugee object
        Refugee refugee = refugeeRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Refugee not found with username: " + username));

        // Return the formation of the refugee
        return refugee.getFormation();
    }

    /**
     *  Get the current formation of a refugee.
     * @param username
     * @return  The current formation of the refugee.
     */
    public Formation getCurrentFormationByRefugee(String username) {
        // Retrieve the refugee object by username
        Refugee refugee = refugeeRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Refugee not found with username: " + username));

        // Return the current formation of the refugee
        return refugee.getFormation();
    }

    /**
     *  Get the list of refugees by ids
     * @param refugeeIds
     * @return  The list of refugees with the given ids.
     */
    public List<Refugee> getRefugeesByIds(List<Long> refugeeIds) {

        // Check if the ids are valid
        if (refugeeIds == null || refugeeIds.isEmpty()) {
            throw new RuntimeException("Refugee ids cannot be null or empty");
        }

        // Retrieve all refugees
        return refugeeRepository.findAllById(refugeeIds);
    }

    /**
     *  Remove the formation association from a refugee.
     * @param username
     */
    @Transactional
    public void removeFormationFromRefugee(String username) {
        // Retrieve the refugee object
        Refugee refugee = getRefugeeByUsername(username);

        // Remove the formation association from the refugee
        refugee.setFormation(null);

        // Save the updated refugee
        refugeeRepository.save(refugee);
    }

    /**
     *  Get the id of a refugee by username.
     * @param username
     * @return  The id of the refugee with the given username.
     */
    public Long getRefugeeIdByUsername(String username) {
        // Check if the refugee exists with the given username and return its id
        return refugeeRepository.findByUserName(username)
                .map(Refugee::getId)
                .orElseThrow(() -> new RuntimeException("Refugee not found with username: " + username));
    }
}
