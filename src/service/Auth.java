package service;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

import javax.servlet.ServletConfig;
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

    @Override
    public void init(ServletConfig arg0) throws ServletException {
        super.init(arg0);
        // Big line to be visible from output
        System.out.println("**************************************   INIT   **************************************");

        try {
            // Initialize MediathequeData
            // (static block is executed and the instance is registered to mediatheque class)
            Class.forName("persistance.MediathequeData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        // Get singleton instance of mediatheque
        Mediatheque mediatheque = Mediatheque.getInstance();
        PrintWriter out = response.getWriter();

        // Get params from request
        // TODO : use post method
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // Get user from mediatheque
        Utilisateur user = mediatheque.getUser(login, password);

        if (user == null) {
            out.println("Incorrect login or password");
        }
        else
        {
            out.println("Good ! Your are connected :)");
        }
    }
}
