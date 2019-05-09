package service;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;
import persistance.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Emprunter extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Mediatheque mediatheque = Mediatheque.getInstance();
        int id = Integer.parseInt(request.getParameter("id"));
        Utilisateur user = (User)session.getAttribute("user");
        try {
            mediatheque.emprunt(mediatheque.getDocument(id), user);
            request.setAttribute("empruntSuccess", "Document emprunté !");
        } catch (EmpruntException e) {
            request.setAttribute("empruntSuccess", "Erreur lors de l'emprunt");
            e.printStackTrace();
        }

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

        request.setAttribute("docsAvailable", mediatheque.tousLesDocuments());

        RequestDispatcher view;
        view = request.getRequestDispatcher("html/abonne.jsp");
        view.forward(request, response);
    }
}