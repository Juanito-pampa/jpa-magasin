<%@page import="java.util.ArrayList"%>
<%@page import="fr.sopra.model.Categories"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Modifier catégorie</h1>

<%Categories c = (Categories)request.getAttribute("categorie");%>
<table  >
		<tr>
			<th>Nom</th>
			<th>ID</th>
		</tr>
		<tr>
			<th><%=c.getNom()%></th>
			<th><%=c.getId()%></th>
		</tr>
		
	</table>
	
<form action="categorie-edition" method="post">
    <input type="hidden" name="id" value="<%=c.getId() %>" />
    Enter new name : <input type="text" name="name" />
<button type="submit">OK</button>
</form>
</body>
</html>