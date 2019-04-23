import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    static private ArrayList<User> users;

    User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    static void add(User user) {
        users.add(user);
    }
}
