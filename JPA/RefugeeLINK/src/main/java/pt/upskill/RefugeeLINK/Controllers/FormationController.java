package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeIdNotFound;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Services.FormationService;


import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/all")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @GetMapping("{id}")
    public Formation getFormationById(Long id) throws FormationIdNotFound {
        return formationService.getFormationById(id);
    }

    @PostMapping("/register")
    public Formation registerFormation(Formation formation) {
        return formationService.registerFormation(formation);
    }

    @PutMapping("/update")
    public Formation updateFormation(Formation formation) throws FormationIdNotFound {
        return formationService.updateFormation(formation);
    }

    @DeleteMapping("/delete")
    public void deleteFormation(Long id) throws FormationIdNotFound {
        formationService.deleteFormation(id);
    }


    @PutMapping("/approveRefugee/{formationId}/{refugeeId}")
    public void approveRefugeeInFormation(@PathVariable Long formationId, @PathVariable Long refugeeId) throws FormationIdNotFound, RefugeeIdNotFound {
        formationService.approveRefugeeInFormation(formationId, refugeeId);
    }







}
