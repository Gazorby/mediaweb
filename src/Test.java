import mediatheque.Mediatheque;
import mediatheque.Utilisateur;
import persistance.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "";
    private Connection connection = null;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            out.println(testMethod(login, password));
        } catch (SQLException | ClassNotFoundException e) {
            out.println(e);
        }

    }

    private String testMethod(String login, String password) {
        String statement = "select isbibliothecaire from "user" where login=? and passwd=?";
        Utilisateur user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                user = new User(res.getBoolean("isbibliothecaire"));
            }
        } catch (SQLException e) {
            return e.toString();
        }
        return user == null ? "ko" : "ok";
    }
}
