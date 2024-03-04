package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

@Service
public class RefugeeService {

    @Autowired
    RefugeeRepository refugeeRepository;


    public Refugee registerRefugee(Refugee refugee) {
        return this.refugeeRepository.save(refugee);
    }
}
