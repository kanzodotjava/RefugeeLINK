package pt.upskill.RefugeeLINK.MessageSystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderUsernameAndReceiverUsernameOrderBySentAtAsc(String senderUsername, String receiverUsername);

    List<Message> findBySenderUsernameAndReceiverUsernameOrReceiverUsernameAndSenderUsername(String user1, String user2, String user3, String user4);

}
