package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Models.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}