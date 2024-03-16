package pt.upskill.RefugeeLINK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pt.upskill.RefugeeLINK.Controllers.RefugeeFormationController;
import pt.upskill.RefugeeLINK.DTO.RefugeeFormationDTO;
import pt.upskill.RefugeeLINK.Exceptions.RefugeeFormationIdNotFound;
import pt.upskill.RefugeeLINK.Models.RefugeeFormation;
import pt.upskill.RefugeeLINK.Services.RefugeeFormationService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RefugeeFormationControllerTests {


    //In these tests:
    //
    //    We use Mockito to mock the RefugeeFormationService.
    //    We then set up mock behavior for the service methods being called in the controller methods.
    //    Finally, we call the controller methods and assert the responses to ensure they match the expected behavior.


    @Mock
    private RefugeeFormationService refugeeFormationService;

    @InjectMocks
    private RefugeeFormationController refugeeFormationController;

    @Test
    public void testGetAllRefugeeFormations() {
        // Mock data
        RefugeeFormation formation1 = new RefugeeFormation();
        RefugeeFormation formation2 = new RefugeeFormation();
        List<RefugeeFormation> formations = Arrays.asList(formation1, formation2);

        // Mock the service method
        when(refugeeFormationService.getAllRefugeeFormations()).thenReturn(formations);

        // Call the controller method
        ResponseEntity<List<RefugeeFormationDTO>> response = refugeeFormationController.getAllRefugeeFormations();

        // Assert the response
        assertEquals(formations.size(), response.getBody().size());
        assertEquals(formation1.getId(), response.getBody().get(0).getId());
        assertEquals(formation2.getId(), response.getBody().get(1).getId());
    }

    @Test
    public void testGetRefugeeFormationById() throws RefugeeFormationIdNotFound {
        // Mock data
        long formationId = 1L;
        RefugeeFormation formation = new RefugeeFormation();
        formation.setId(formationId);

        // Mock the service method
        when(refugeeFormationService.getRefugeeFormation(formationId)).thenReturn(formation);

        // Call the controller method
        ResponseEntity<RefugeeFormationDTO> response = refugeeFormationController.getRefugeeFormationById(formationId);

        // Assert the response
        assertEquals(formation.getId(), response.getBody().getId());
    }
}
