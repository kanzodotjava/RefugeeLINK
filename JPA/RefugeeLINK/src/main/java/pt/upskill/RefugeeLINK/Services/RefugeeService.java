package pt.upskill.RefugeeLINK.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class RefugeeService {

    @Autowired
    RefugeeRepository refugeeRepository;
    private  BCryptPasswordEncoder passwordEncoder;

    public RefugeeService(RefugeeRepository refugeeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.refugeeRepository = refugeeRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Refugee addRefugee(Refugee refugee) {
        if (refugeeRepository.existsById(refugee.getId())) {
            throw new DataIntegrityViolationException("Refugee with ID " + refugee.getId() + " already exists.");
        }
        String username = refugee.getUserName();
        if (username.length() < 6 || username.length() > 12 || !username.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Username must be between 6 and 12 characters long and contain only letters and numbers.");
        }

        String password = refugee.getPassword();
        if (password.length() < 8 || password.length() > 50 || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must be between 8 and 50 characters long and contain at least one uppercase character.");
        }
        String hashedPassword = passwordEncoder.encode(password);
        refugee.setPassword(hashedPassword);

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
}
