package service;

import mediatheque.Mediatheque;
import persistance.Document.ADocument;
import persistance.Document.DocumentFactory;
import persistance.Document.Type;

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

        DocumentFactory factory = new DocumentFactory();
        ADocument aDocument = factory.getDocument(title, Type.valueOf(request.getParameter("typeDoc")), -1, null);

        Mediatheque mediatheque = Mediatheque.getInstance();
        mediatheque.nouveauDocument(aDocument.getId(), aDocument);

        request.setAttribute("added", "Document ajouté !");

        view = request.getRequestDispatcher("html/bibliothecaire.jsp");
        view.forward(request, response);
    }

}
