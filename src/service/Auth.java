package service;

import mediatheque.Mediatheque;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Read requests params and
 */
public class Auth extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "";
    private static final String PASS = "";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        Mediatheque mediatheque = Mediatheque.getInstance();
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println(mediatheque.getUser(login, password).toString());
    }
}
