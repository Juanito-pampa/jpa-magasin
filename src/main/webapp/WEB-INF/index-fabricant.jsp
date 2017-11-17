<%@page import="java.util.Map"%>
<%@page import="fr.sopra.model.Produit"%>
<%@page import="fr.sopra.model.Fabricants"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>fabricants</title>
</head>
<body>
<h1>Fabricants</h1>
</br>
	<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>

<div>Accéder aux : 
<form method="get" action="produits" >
                <button>Produits</button></form>
<span><form method="get" action="categories" >
                <button>Categories</button></form></span></div>

	<%
	List<Fabricants> f = (List<Fabricants>) request.getAttribute("fabricants");
	Map<Integer, Long> nombreDeProduitParFabricant = (Map<Integer, Long>)request.getAttribute("nombreDeProduitParFabricant");
	int pagelistFabricant = (int)request.getAttribute("pagelistFabricantRetour"); 
	%>
	
	<table >
		<tr>
			<th>Nom</th>
			<th>Adress</th>
			<th>ID</th>
			<th>Nombre de produits</th>
		</tr>
		<%for (Fabricants current : f) {%>
		<tr>
			<th><%=current.getNom()%></th>
			<th><%=current.getAdresse()%></th>
			<th><%=current.getId()%></th>
			<th>
				<%=nombreDeProduitParFabricant.get(current.getId()) %>
			</th>
			<th><form method="get" action="fabricant-edition" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Editer</button>
            </form></th>
            <th><form method="post" action="fabricant-supprimer" >
                <input type="hidden" name="id" value="<%= current.getId()%>">
                <button>Supprimer</button>
            </form></th>
		</tr>
		<%}%>
	</table>
	<th><form method="get" action="fabricant-edition" >
                <button>Nouveau fabricant</button></th></form>
                
                <th><form method="get" action="fabricants" >
                <input type="hidden" name="pagelistFabricant" value="<%=pagelistFabricant + 10%>">
                <button>Fabricants suivants</button></form>


</body>
</html>