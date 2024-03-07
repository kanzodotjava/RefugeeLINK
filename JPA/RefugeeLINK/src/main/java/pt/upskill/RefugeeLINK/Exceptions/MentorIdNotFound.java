package pt.upskill.RefugeeLINK.Exceptions;

public class MentorIdNotFound extends Exception{

    public MentorIdNotFound(String message) {
        super(message);
    }

    public MentorIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public MentorIdNotFound(Throwable cause) {
        super(cause);
    }

    public MentorIdNotFound() {
        super();
    }
}
