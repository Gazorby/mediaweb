package service;

import mediatheque.Mediatheque;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Ajouter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view;

        ArrayList<String> documentDetails = new ArrayList<String>(2);
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        documentDetails.add(author);
        documentDetails.add(title);
        String typeS = request.getParameter("typeDoc");
        int type;
        if (typeS.equals("DVD"))
            type = 1;
        else if (typeS.equals("CD"))
            type = 2;
        else
            type = 3;

        Mediatheque mediatheque = Mediatheque.getInstance();
        mediatheque.nouveauDocument(type, documentDetails);

        request.setAttribute("added", "Document ajouté !");

        view = request.getRequestDispatcher("html/bibliothecaire.jsp");
        view.forward(request, response);
    }

}
