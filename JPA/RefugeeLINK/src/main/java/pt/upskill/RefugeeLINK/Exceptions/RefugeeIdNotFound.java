package pt.upskill.RefugeeLINK.Exceptions;

public class RefugeeIdNotFound extends Exception{
    public RefugeeIdNotFound(String message) {
        super(message);
    }

    public RefugeeIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public RefugeeIdNotFound(Throwable cause) {
        super(cause);
    }

    public RefugeeIdNotFound() {
        super();
    }

}
