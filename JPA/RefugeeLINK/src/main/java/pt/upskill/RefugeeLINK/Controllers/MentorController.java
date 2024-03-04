package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.RefugeeLINK.Services.MentorService;

@RestController
public class MentorController {

    @Autowired
    MentorService mentorService;

}
