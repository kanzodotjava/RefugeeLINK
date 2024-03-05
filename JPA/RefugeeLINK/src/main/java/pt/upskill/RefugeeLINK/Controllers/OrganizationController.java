package pt.upskill.RefugeeLINK.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.OrganizationNotFound;
import pt.upskill.RefugeeLINK.Models.Organization;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Repositories.OrganizationRepository;
import pt.upskill.RefugeeLINK.Services.OrganizationService;

@RestController
@RequestMapping("/organization")

public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization){
        Organization registeredOrg = organizationService.addOrganization(organization);
        return new ResponseEntity<>(registeredOrg, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable Long id,Organization organization) throws OrganizationNotFound {
        Organization updatedOrg = organizationService.updateOrganization(id,organization);
        return new ResponseEntity<>(updatedOrg, HttpStatus.OK);
    }

}
