package pt.upskill.RefugeeLINK.Exceptions;

public class FormationIdNotFound extends Exception{

    public FormationIdNotFound(String message) {
        super(message);
    }

    public FormationIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public FormationIdNotFound(Throwable cause) {
        super(cause);
    }

    public FormationIdNotFound() {
        super();
    }
}
