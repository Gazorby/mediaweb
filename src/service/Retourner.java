package service;

import mediatheque.Mediatheque;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Retourner extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher view;

        String[] docs= request.getParameterValues("doc");
        System.out.println(docs[0]);

        Mediatheque mediatheque = Mediatheque.getInstance();

        for (int i = 0; i < docs.length; i++) {
            mediatheque.retour(mediatheque.getDocument(Integer.parseInt(docs[i])));
        }


        request.setAttribute("added", "Document(s) retourné(s) !");
        view = request.getRequestDispatcher("index.html");
        view.forward(request, response);
    }
}
