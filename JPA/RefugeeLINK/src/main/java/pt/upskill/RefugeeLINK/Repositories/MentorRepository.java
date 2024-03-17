package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Enums.Country;
import pt.upskill.RefugeeLINK.Enums.Language;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;

import java.util.List;
import java.util.Optional;

/**
 *  Represents a repository for {@link Mentor} entities.
 */
@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    /**
     *  Returns the mentor with the given username.
     * @param userName
     * @return  The mentor with the given username.
     */
    Optional<Mentor> findByUserName(String userName);

    /**
     *  Returns the list of mentors with the given status.
     * @param status
     * @return  The list of mentors with the given status.
     */
    List<Mentor> getMentorByStatus(Status status);

    /**
     *  Checks if a mentor with the given username exists.
     * @param username
     * @return  True if a mentor with the given username exists, false otherwise.
     */
    boolean existsByUserName(String username);

    /**
     *  Checks if a mentor with the given email exists.
     * @param email
     * @return  True if a mentor with the given email exists, false otherwise.
     */
    boolean existsByEmailAddress(String email);


}
