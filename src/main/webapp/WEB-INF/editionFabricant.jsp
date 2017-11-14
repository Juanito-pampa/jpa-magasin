<%@page import="fr.sopra.model.Fabricants"%>
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
<h1>Modifier fabricant<h1>

<%Fabricants f = (Fabricants)request.getAttribute("fabricant");%>
<table >
		<tr>
			<th>Nom</th>
			<th>Adress</th>
			<th>ID</th>
		</tr>
		<tr>
			<th><%=f.getNom()%></th>
			<th><%=f.getAdresse()%></th>
			<th><%=f.getId()%></th>
		</tr>
		
	</table>
	
<form action="fabricant-edition" method="post">
    <input type="hidden" name="id" value="<%=f.getId() %>" />
    Enter new name : <input type="text" name="name" />
    Enter new adress : <input type="text" name="adress" />
<button type="submit">OK</button>

</body>
</html>