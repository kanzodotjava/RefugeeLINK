package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByUserName(String userName);
    List<Mentor> getMentorByStatus(Status status);
    boolean existsByUserName(String username);
    boolean existsByEmailAddress(String email);

}
