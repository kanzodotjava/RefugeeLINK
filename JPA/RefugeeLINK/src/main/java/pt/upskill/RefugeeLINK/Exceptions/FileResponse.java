package pt.upskill.RefugeeLINK.Exceptions;

public class FileResponse {

    private String fileName;
    private String message;

    public FileResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
