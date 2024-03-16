package pt.upskill.RefugeeLINK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.upskill.RefugeeLINK.Controllers.FormationController;
import pt.upskill.RefugeeLINK.Models.Formation;
import pt.upskill.RefugeeLINK.Services.FormationService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormationControllerTests {

    @Mock
    private FormationService formationService;

    @InjectMocks
    private FormationController formationController;

    @Test
    public void testRegisterFormation() {
        // Mock data
        Formation formation = new Formation();
        formation.setId(1L);
        formation.setName("Test Formation");

        // Mock the service method
        when(formationService.registerFormation(any(Formation.class))).thenReturn("Test Formation");

        // Call the controller method
        String response = formationController.registerFormation(formation);

        // Assert the response
        assertEquals("Test Formation", response);
    }

    @Test
    public void testGetFormationsByOrganizationId() {
        // Mock data
        long organizationId = 1L;
        Formation formation = new Formation();
        formation.setId(1L);
        formation.setName("Test Formation");

        // Mock the service method
        when(formationService.getFormationsByOrganizationId(organizationId)).thenReturn(Collections.singletonList(formation));

        // Call the controller method
        ResponseEntity<List<Formation>> response = formationController.getFormationsByOrganizationId(organizationId);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Formation", response.getBody().get(0).getName());
    }
}
