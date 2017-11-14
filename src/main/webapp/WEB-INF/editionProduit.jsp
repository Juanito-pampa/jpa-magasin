<%@page import="java.util.List"%>
<%@page import="fr.sopra.model.Fabricants"%>
<%@page import="fr.sopra.model.Categories"%>
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
<h1>Modifier produit<h1>

<%Produit p = (Produit)request.getAttribute("produit");%>
<%List<Categories> c =(List<Categories>)request.getAttribute("categories"); %>
<%List<Fabricants> f =(List<Fabricants>)request.getAttribute("fabricants");%>

<table  >
		<tr>
			<th>Nom</th>
			<th>Reference</th>
			<th>ID</th>
		</tr>
		
		<tr>
			<th><%=p.getNom()%></th>
			<th><%=p.getReference()%></th>
			<th><%=p.getId()%></th>
		</tr>
	</table>
	
	 
	<table  >
		<tr>
			<th></th>
			<th>Nom</th>
			<th>Adresse</th>
		</tr>
		<tr>
			<th>Fabricant :</th>
			<th><%=p.getFabriquants().getNom()%></th>
			<th><%=p.getFabriquants().getAdresse()%></th>
		</tr>
		<tr>
			<th>Creer un nouveau Fabricant :</th>
			<form action="produit-edition" method="get">
			<input type="hidden" name="id" value="<%= p.getId()%>">
   			<th><input type="text" placeholder="Entrer le nom " name="newNameFab" /></th>
   			<th><input type="text" placeholder="Entrer l'adresse " name="newAdressFab" /></th>
			
		</tr>
		
	</table>
	
		<table  border="2">
		<tr>
			<th></th>
			<th>Nom</th>
		</tr>
		<tr>
			<th>Categorie :</th>
			<th><%=p.getCategories().getNom()%></th>
		</tr>
		<tr>
			<th>Creer une nouvelle categorie :</th>
			<th><form action="produit-edition" method="get">
			<input type="hidden" name="id" value="<%= p.getId()%>">
   			<input type="text" placeholder="Entrer le nom de la nouvelle categorie" name="newNameCat" />
			<button type="submit">OK</button></form></th>
		</tr>
		
	</table>
	

	
<form action="produit-edition" method="post">
    <input type="hidden" name="id" value="<%=p.getId() %>" />
    Enter new name : <input type="text" name="name" />
    Enter new reference : <input type="text" name="reference" />
    <select name="categorie" >
 
    <%for(Categories current : c){ %>
    <option value="<%=current.getId()%>"><%=current.getNom() %></option>
    <%} %>
    </select>
    <select name="fabricant">
    <%for(Fabricants current : f){ %>
    <option value="<%=current.getId()%>"><%=current.getNom() %></option>
    <%} %>
    </select>
<button type="submit">OK</button></form>

</body>
</html>