package pt.upskill.RefugeeLINK.MessageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.RefugeeLINK.Models.Person;
import pt.upskill.RefugeeLINK.Repositories.MentorRepository;
import pt.upskill.RefugeeLINK.Repositories.RefugeeRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private RefugeeRepository refugeeRepository;

    public Message sendMessage(String senderUsername, String receiverUsername, String content) {
        // Find the sender and receiver. Assume that usernames are unique across Mentors and Refugees
        Person sender = mentorRepository.findByUserName(senderUsername)
                .map(mentor -> (Person)mentor)
                .orElseGet(() -> refugeeRepository.findByUserName(senderUsername)
                        .orElseThrow(() -> new RuntimeException("Sender not found")));

        Person receiver = mentorRepository.findByUserName(receiverUsername)
                .map(mentor -> (Person)mentor)
                .orElseGet(() -> refugeeRepository.findByUserName(receiverUsername)
                        .orElseThrow(() -> new RuntimeException("Receiver not found")));

        Message message = new Message();
        message.setSenderUsername(senderUsername);
        message.setReceiverUsername(receiverUsername);
        message.setContent(content);

        return messageRepository.save(message);
    }

    public List<Message> getConversation(String senderUsername, String receiverUsername) {
        List<Message> conversation = messageRepository.findBySenderUsernameAndReceiverUsernameOrderBySentAtAsc(senderUsername, receiverUsername);

        return conversation;
    }

}
