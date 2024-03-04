package fake.IdApi.fakeId.Repository;

import fake.IdApi.fakeId.Models.RefugeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefugeeIdRepository extends JpaRepository<RefugeeId, Integer> {

}
