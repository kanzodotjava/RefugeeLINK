package pt.upskill.RefugeeLINK.Exceptions;

public class RefugeeFormationIdNotFound extends Exception{

    public RefugeeFormationIdNotFound(String message) {
        super(message);
    }

    public RefugeeFormationIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RefugeeFormationIdNotFound(Throwable cause) {
        super(cause);
    }

    public RefugeeFormationIdNotFound() {
        super();
    }

}
