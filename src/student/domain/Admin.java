package student.domain;

/**
 * 管理员账户
 */
public class Admin {
    private String username;
    private String password;
    private String verifyCode;

    public Admin(String username, String password, String verifyCode) {
        this.username = username;
        this.password = password;
        this.verifyCode = verifyCode;
    }

    public Admin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
