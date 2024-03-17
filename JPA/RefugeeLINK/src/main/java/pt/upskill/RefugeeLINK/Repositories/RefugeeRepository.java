package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;

import java.util.Optional;

/**
 *  Repository class for the class Refugee
 */
@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
    /**
     *  Checks if the email exists
     * @param email
     * @return  true if the email exists
     */
    boolean existsByEmailAddress(String email);

    /**
     *  Checks if the username exists
     * @param username
     * @return  true if the username exists
     */
    boolean existsByUserName(String username);

    /**
     *  Finds the refugee by the username
     * @param userName
     * @return  the refugee
     */
    Optional<Refugee> findByUserName(String userName);
}
