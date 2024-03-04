package fake.IdApi.fakeId.Service;

import fake.IdApi.fakeId.Models.RefugeeId;
import fake.IdApi.fakeId.Repository.RefugeeIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefugeeIdService {

    private final RefugeeIdRepository refugeeIdRepository;

    @Autowired
    public RefugeeIdService(RefugeeIdRepository idRepository) {
        this.refugeeIdRepository = idRepository;
    }

    public RefugeeId getID(int id) {
        return refugeeIdRepository.findById(id).orElse(null);
    }

    public RefugeeId putID(RefugeeId id) {
        return refugeeIdRepository.save(id);
    }

    public void deleteID(int id) {
        refugeeIdRepository.deleteById(id);
    }
}