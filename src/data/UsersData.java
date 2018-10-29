package data;

public class UsersData {

    String userName;
    String userPassword;

    @Override
    public String toString() {
        return "UsersData{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UsersData(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
