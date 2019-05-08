<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Portail bibliothecaire </title>
</head>

<body>
    <section>
        <h3><%=request.getAttribute("welcome")%></h3>
    </section>

    <section>
        <h3>Enregistrer un nouveau document</h3>
        
        <form action="html/biblio_process.jsp" method="post">
        title:<input type="text" name="title">
        author:<input type="text" name="author"> <br>

     	<input type="radio" name="typeDoc" value = "CD"> CD <br>
     	<input type="radio" name="typeDoc" value = "DVD"> DVD <br>
     	<input type="radio" name="typeDoc" value = "Livre"> Livre <br><br>
     	<input type="submit" value="submit" />
     	</form>
    </section>    
</body>

</html>