package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Models.Refugee;

import java.util.Optional;

@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
    boolean existsByEmailAddress(String email);

    Optional<Refugee> findByUserName(String userName);
}
