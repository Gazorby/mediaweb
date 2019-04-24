import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "mediaweb";

    public static void main(String[] args) {
        try {
            String statement = "insert into "user" (login, passwd) values ('matthieu', '1234')";
            Connection connection = null;
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }
}
