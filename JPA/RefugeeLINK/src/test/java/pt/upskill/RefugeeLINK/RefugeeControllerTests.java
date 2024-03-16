package pt.upskill.RefugeeLINK;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.upskill.RefugeeLINK.Controllers.RefugeeController;
import pt.upskill.RefugeeLINK.DTO.RefugeeDTO;
import pt.upskill.RefugeeLINK.Exceptions.MentorAlreadySelectedException;
import pt.upskill.RefugeeLINK.Exceptions.MentorIdNotFound;
import pt.upskill.RefugeeLINK.Models.Refugee;
import pt.upskill.RefugeeLINK.Services.RefugeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RefugeeControllerTests {

    @Mock
    private RefugeeService refugeeService;

    @InjectMocks
    private RefugeeController refugeeController;

    @Test
    public void testCreateRefugee() {
        // Mock data
        Refugee refugee = new Refugee();
        refugee.setId(1L);
        refugee.setUserName("testuser");
        refugee.setPassword("testpassword");

        // Mock the service method
        when(refugeeService.addRefugee(any(Refugee.class))).thenReturn(refugee);

        // Call the controller method
        RefugeeDTO refugeeDTO = new RefugeeDTO();
        refugeeDTO.setUserName("testuser");
        refugeeDTO.setPassword("testpassword");
        ResponseEntity<RefugeeDTO> response = refugeeController.createRefugee(refugee);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(refugee.getId(), response.getBody().getId());
        assertEquals(refugee.getUserName(), response.getBody().getUserName());
    }

    @Test
    public void testSelectMentorForRefugee() throws MentorIdNotFound, MentorAlreadySelectedException {
        // Mock data
        long mentorId = 1L;

        // Mock the service method
        doNothing().when(refugeeService).selectMentorForRefugee(anyString(), anyLong());

        // Call the controller method
        ResponseEntity<String> response = refugeeController.selectMentorForRefugee("testuser", mentorId);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mentor selected for refugee", response.getBody());
    }

    @Test
    public void testSelectMentorForRefugee_withInvalidMentorId() throws MentorIdNotFound, MentorAlreadySelectedException {
        // Mock data
        long invalidMentorId = 999L;

        // Mock the service method to throw MentorIdNotFound
        doThrow(new MentorIdNotFound("Invalid Mentor ID")).when(refugeeService).selectMentorForRefugee(anyString(), eq(invalidMentorId));

        // Call the controller method
        ResponseEntity<String> response = refugeeController.selectMentorForRefugee("testuser", invalidMentorId);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error selecting mentor: Invalid Mentor ID", response.getBody());
    }
}
