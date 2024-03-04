package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.RefugeeService;

@RestController("Refugee")
public class RefugeeController {

    @Autowired
    RefugeeService refugeeService;


    @PostMapping("/register")
    public ResponseEntity<Refugee> registerRefugee(@RequestBody Refugee refugee) {
        Refugee registeredRefugee = refugeeService.registerRefugee(refugee);
        return new ResponseEntity<>(registeredRefugee, HttpStatus.CREATED);
    }
}
