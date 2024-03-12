package pt.upskill.RefugeeLINK.MessageSystem;

public class MessageDTO {
    private String senderUsername;
    private String receiverUsername;
    private String content;

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public String getContent() {
        return content;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
