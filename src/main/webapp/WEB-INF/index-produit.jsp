<%@page import="java.util.ArrayList"%>
<%@page import="fr.sopra.model.Produit"%>
<%@page import="fr.sopra.model.Categories"%>
<%@page import="fr.sopra.model.Fabricants"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" /> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>produits</title>

</head>
<body>
<h1>Produits</h1>
</br>
	<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>

<br/>
<div>Accéder aux : 
<form method="get" action="fabricants" >
                <button>Fabricants</button></form>
<span><form method="get" action="categories" >
                <button>Categories</button></form></span></div>

<br/>

<div> 
	<form action="recherche-produit" method="get">
		 Rechercher un produit : <input type="text" name="motClef" /><button>Rechercher</button>
	</form>
</div>

<br/>
	
	<%
	List<Produit> p= (List<Produit>)request.getAttribute("produits");
	int pagelistProduit = (int)request.getAttribute("pagelistProduitRetour");%>
	<form method="get" action="produits" >
                <input type="hidden" name="pagelistProduit" value="<%=pagelistProduit + 50%>">
                <button>Produits suivants</button></form>
	<table  >
		<tr>
			<th>Nom</th>
			<th>Reference</th>
			<th>Categorie</th>
			<th>Fabricant</th>
			<th>ID</th>
		</tr>
		<%for (Produit current : p) {%>
		<tr>
			<th><%=current.getNom()%></th>
			<th><%=current.getReference()%></th>
			<th><%=current.getCategories().getNom()%></th>
			<th><%=current.getFabriquants().getNom()%></th>
			<th><%=current.getId()%></th>
			<th><form method="get" action="produit-edition" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Editer</button>
            </form></th>
            <th><form method="post" action="produit-supprimer" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Supprimer</button>
            </form></th>
            <th><form method="post" action="commande-edition" >
                <input type="hidden" name="produit" value="<%= current.getId()%>">
                Entrez la quantité : <input type="text" name="quantite" />
                <button>Commander</button>
            </form></th>
		</tr>
		<%}%>
	</table>
	<form method="get" action="produits" >
                <input type="hidden" name="pagelistProduit" value="<%=pagelistProduit + 50%>">
                <button>Produits suivants</button></form>
	<th><form method="get" action="produit-edition" >
                <button>Nouveau produit</button></form>
                
<br/>
Accéder aux : 
<form method="get" action="categories" >
                <button>Categories</button></form>
<form method="get" action="fabricants" >
                <button>Fabricants</button></form>
                
                </form>
                
                
</body>
</html>