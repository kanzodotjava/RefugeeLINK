package pt.upskill.RefugeeLINK.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.RefugeeLINK.Models.Refugee;

public interface RefugeeRepository extends JpaRepository<Refugee, Long> {
}
