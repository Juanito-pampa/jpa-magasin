<%@page import="java.util.List"%>
<%@page import="fr.sopra.model.Commande"%>
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

<h1>Commandes</h1>
	</br>
	<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>
	
<div>	Accéder aux : 
<form method="get" action="fabricants" >
                <button>Fabricants</button></form>
<form method="get" action="produits" >
                <button>Produits</button></form>
<form method="get" action="categories" >
                <button>Categories</button></form></div>

	<%List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");%>
	<table >
		<tr>
			<th>ID</th>
			<th>Utilisateur</th>
			<th>Produit</th>
			<th>Quantité</th>
		</tr>
		<%for (Commande current : commandes) {%>
		<tr>
			<th><%=current.getId()%></th>
			<th><%=current.getUtilisateur().getLogin()%></th>
			<th><%=current.getProduit().getNom()%></th>
			<th><%=current.getQuantité()%></th>
		</tr>
		<%}%>
	</table>
	<form method="get" action="commande-edition" >
                <button>Nouvelle commande</button></form>
                
                

</body>
</html>