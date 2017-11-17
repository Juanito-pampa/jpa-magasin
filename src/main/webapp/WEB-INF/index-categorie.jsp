<%@page import="java.util.Map"%>
<%@page import="fr.sopra.model.Produit"%>
<%@page import="fr.sopra.model.Categories"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>catégorie</title>
</head>
<body>
	<h1>Catégorie</h1>
	</br>
	<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>
	
<div>	Accéder aux : 
<form method="get" action="fabricants" >
                <button>Fabricants</button></form>
<span><form method="get" action="produits" >
                <button>Produits</button></form></span></div>

	<%List<Categories> c = (List<Categories>) request.getAttribute("categories");%>
	<%Map<Integer, Long> nombreDeProduitParCategorie = (Map<Integer, Long>)request.getAttribute("nombreDeProduitParCategorie");%>
	<%int pagelistCategorie = (int)request.getAttribute("pagelistCategorieRetour"); %>
	<table >
		<tr>
			<th>Nom</th>
			<th>ID</th>
			<th>Nombre de produit</th>
		</tr>
		<%for (Categories current : c) {%>
		<tr>
			<th><%=current.getNom()%></th>
			<th><%=current.getId()%></th>
			<th>
				<%=nombreDeProduitParCategorie.get(current.getId()) %>
			</th>
			<th><form method="get" action="categorie-edition" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Editer</button>
            </form></th>
            <th><form method="post" action="categorie-supprimer" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Supprimer</button>
            </form></th>
		</tr>
		<%}%>
	</table>
	<form method="get" action="categorie-edition" >
                <button>Nouvelle categorie</button></form>
                
                <th><form method="get" action="categories" >
                <input type="hidden" name="pagelistCategorie" value="<%=pagelistCategorie + 10%>">
                <button>Categories suivantes</button></form>




</body>
</html>