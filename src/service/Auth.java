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
import java.util.LinkedList;
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

            List<String> tmp = new LinkedList<>(Arrays.asList(user.toString().split(", ")));
            tmp.remove(tmp.size() - 1);

            if (tmp == null) {
                request.setAttribute("docsEmpruntes", "Vous n'avez pas de document empruntés");
            }
            else {
                List<Document> docs = new ArrayList<>();

                  for (String s : tmp) {
                      docs.add(mediatheque.getDocument(Integer.parseInt(s)));

                  }

                request.setAttribute("docsEmpruntes", docs);
            }

            request.setAttribute("docsAvailable", mediatheque.tousLesDocuments());
        }

        else {
            view = request.getRequestDispatcher("html/bibliothecaire.jsp");
        }

        session.setAttribute("user", user);
        session.setAttribute("welcome", "Bonjour " + login + " !");
        view.forward(request, response);

    }


}
