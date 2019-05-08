package service;

import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        // Get singleton instance of mediatheque
        Mediatheque mediatheque = Mediatheque.getInstance();

        // Get params from the post request
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // Get user from mediatheque
        Utilisateur user = mediatheque.getUser(login, password);
        RequestDispatcher view;

        if (user == null) {
            view = request.getRequestDispatcher("html/error-login.html");
            view.forward(request, response);
            return;
        }
        else if (! user.isBibliothecaire())
        {
            view = request.getRequestDispatcher("html/abonne.jsp");
            String borrowedDocsString = user.toString();

            if (borrowedDocsString == null) {
                request.setAttribute("docsEmpruntes", "Vous n'avez pas de document empruntés");
            }
            else {
                List<Document> docs = new ArrayList<>();
                // Convert string borrowedDocsString to int array (docs id)
                int[] borrowedDocs = Arrays.stream(borrowedDocsString.split(", ")).mapToInt(Integer::parseInt).toArray();

                for (int id : borrowedDocs) {
                    docs.add(mediatheque.getDocument(id));
                }
                request.setAttribute("docs", docs);
            }
        }

        else {
            view = request.getRequestDispatcher("html/bibliothecaire.jsp");
        }

        session.setAttribute("welcome", "Bonjour " + login + " !");
        view.forward(request, response);

    }


}
