package pt.upskill.RefugeeLINK.MessageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDto) {
        Message message = messageService.sendMessage(
                messageDto.getSenderUsername(),
                messageDto.getReceiverUsername(),
                messageDto.getContent()
        );
        return ResponseEntity.ok(message);
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(
            @RequestParam String senderUsername,
            @RequestParam String receiverUsername) {

        List<Message> conversation = messageService.getConversation(senderUsername, receiverUsername);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversation-between-users")
    public ResponseEntity<List<Message>> getConversationBetweenUsers(@RequestParam String user1, @RequestParam String user2) {
        List<Message> conversation = messageService.getConversationBetweenUsers(user1, user2);
        return ResponseEntity.ok(conversation);
    }
}
