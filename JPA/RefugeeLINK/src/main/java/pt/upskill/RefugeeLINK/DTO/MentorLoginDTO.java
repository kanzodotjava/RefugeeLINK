package pt.upskill.RefugeeLINK.DTO;

/**
 *  Mentor login
 */
public class MentorLoginDTO {
    /**
     *  Mentor username
     */
    private String userName;

    /**
     *  Mentor password
     */
    private String password;

    /**
     *  Get mentor username
     * @return  Mentor username
     */
    public String getUserName() {
        return userName;
    }

    /**
     *  Get mentor password
     * @return  Mentor password
     */
    public String getPassword() {
        return password;
    }

    /**
     *  Set mentor username
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *  Set mentor password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
