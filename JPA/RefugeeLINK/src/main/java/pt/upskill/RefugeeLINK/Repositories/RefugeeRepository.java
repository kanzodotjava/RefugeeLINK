package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.RefugeeLINK.Models.Refugee;

@Repository
public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
}
