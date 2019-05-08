<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mediatheque.*"%>
<%@ page import="persistance.MediathequeData" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Enregistrement du document </title>
</head>

<body>
    <section>
    	<h3> Enregistrement du document en cours .. </h3>
    	<%
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
    		response.sendRedirect("bibliothecaire.jsp");

    	%>
    </section>  
</body>

</html>