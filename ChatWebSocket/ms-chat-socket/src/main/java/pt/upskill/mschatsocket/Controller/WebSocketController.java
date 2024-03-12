package pt.upskill.mschatsocket.Controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pt.upskill.mschatsocket.DTO.ChatMessage;

@Controller
public class WebSocketController {

    @MessageMapping("/chat/{mentorUsername}/{refugeeUsername}")
    @SendTo("/topic/chat/{mentorUsername}/{refugeeUsername}")
    public ChatMessage chat(
            @DestinationVariable String mentorUsername,
            @DestinationVariable String refugeeUsername,
            ChatMessage message) {

        return message;
    }

}
