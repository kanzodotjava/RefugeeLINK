package pt.upskill.RefugeeLINK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.upskill.RefugeeLINK.Controllers.MentorController;
import pt.upskill.RefugeeLINK.DTO.MentorDTO;
import pt.upskill.RefugeeLINK.Enums.Status;
import pt.upskill.RefugeeLINK.Models.Mentor;
import pt.upskill.RefugeeLINK.Services.MentorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MentorControllerTests {

    @Mock
    private MentorService mentorService;

    @InjectMocks
    private MentorController mentorController;

    @Test
    public void testCreateMentor() {
        // Mock data
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setUserName("testuser");

        // Mock the service method
        when(mentorService.addMentor(any(Mentor.class))).thenReturn(mentor);

        // Call the controller method
        ResponseEntity<MentorDTO> response = mentorController.createMentor(mentor);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mentor.getId(), response.getBody().getId());
        assertEquals(mentor.getUserName(), response.getBody().getUserName());
    }

    @Test
    public void testCertifyMentor() {
        // Mock data
        long mentorId = 1L;
        Mentor mentor = new Mentor();
        mentor.setId(mentorId);
        mentor.setUserName("testuser");
        mentor.setStatus(Status.CERTIFIED);

        // Mock the service method
        when(mentorService.updateStatus(mentorId, Status.CERTIFIED)).thenReturn(mentor);

        // Call the controller method
        ResponseEntity<MentorDTO> response = mentorController.certifyMentor(mentorId);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mentor.getId(), response.getBody().getId());
        assertEquals(mentor.getUserName(), response.getBody().getUserName());
        assertEquals(Status.CERTIFIED, response.getBody().getStatus());
    }

    @Test
    public void testCertifyMentor_withInvalidId() {
        // Mock data
        long invalidMentorId = 999L;

        // Mock the service method to return null (mentor not found)
        when(mentorService.updateStatus(invalidMentorId, Status.CERTIFIED)).thenReturn(null);

        // Call the controller method
        ResponseEntity<MentorDTO> response = mentorController.certifyMentor(invalidMentorId);

        // Assert the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
