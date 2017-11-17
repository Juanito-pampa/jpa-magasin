<%@page import="java.util.List"%>
<%@page import="fr.sopra.model.Produit"%>
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
<h1>Passer une nouvelle commande</h1>
<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>

<%List<Produit> produits =(List<Produit>)request.getAttribute("produits");%>
<form action="commande-edition" method="get">
		 Rechercher un produit : <input type="text" name="motClef" /><button>Rechercher</button>
	</form>

<form action="commande-edition" method="post">
    Entrez la quantité : <input type="text" name="quantite" />
    <select name="produit">
    <%for(Produit current : produits){ %>
    <option value="<%=current.getId()%>"><%=current.getNom() %></option>
    <%} %>
    </select>
<button type="submit">OK</button></form>

</body>
</html>