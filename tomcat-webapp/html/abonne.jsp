<%@ page import="mediatheque.Document" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Portail abonn&eacute</title>
</head>

<body>
    <section>
        <h3><%=request.getAttribute("welcome")%></h3>
        <button href="emprunter">Emprunter</button>
    </section>

    <section>
        <h3>Vos documents emprunt&eacutes</h3>
        <% List<Document> docs = (List<Document>) request.getAttribute("docs"); %>

        <% for (Document doc : docs) { %>
            <tr>
                <td><%=doc.affiche()[0]%></td>
            </tr >
        <%} %>
    </section>

</body>

</html>