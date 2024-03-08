package pt.upskill.RefugeeLINK.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Exceptions.FormationIdNotFound;
import pt.upskill.RefugeeLINK.Exceptions.OrganizationNotFound;
import pt.upskill.RefugeeLINK.Models.Organization;
import pt.upskill.RefugeeLINK.Repositories.OrganizationRepository;


@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization addOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(Long id, Organization updateOrg) throws OrganizationNotFound {
            Organization org = organizationRepository.findById(id).orElse(null);
            if (org != null) {
                if (updateOrg.getAddress() != null) {
                    org.setAddress(updateOrg.getAddress());
                }
                if (updateOrg.getName() != null) {
                    org.setName(updateOrg.getName());
                }
                if (updateOrg.getEmail() != null) {
                    org.setEmail(updateOrg.getEmail());
                }
                if (updateOrg.getPhoneNumber() != null) {
                    org.setPhoneNumber(updateOrg.getPhoneNumber());
                }
                return organizationRepository.save(org);
            }
        throw new OrganizationNotFound("Formation with ID " + updateOrg.getId() + " not found.");
        }


    public Organization getOrganizationById(Long id) throws OrganizationNotFound {
        return organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFound("Organization with ID " + id + " not found."));
    }

    public void deleteOrganization(Long id) throws OrganizationNotFound {
        if (organizationRepository.existsById(id)) {
            organizationRepository.deleteById(id);
        } else {
            throw new OrganizationNotFound("Organization with ID " + id + " not found.");
        }
    }



}


