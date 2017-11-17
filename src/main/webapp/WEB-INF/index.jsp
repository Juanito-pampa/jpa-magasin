<%@page import="fr.sopra.model.Utilisateur"%>
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
<h1>Bienvenue dans l'inventaire</h1>
<% String userConnected = (String) request.getAttribute("userConnected");%>
<div style="color: red"><%=userConnected%></div>
</br>
<div>
Accéder aux : 
<form method="get" action="categories" >
                <button>Categories</button></form>
<form method="get" action="fabricants" >
                <button>Fabricants</button></form>
<form method="get" action="produits" >
                <button>Produits</button></form>
                
<form method="get" action="export" >
                <button>Export</button></form>
<form method="get" action="commandes" >
                <button>Commandes</button></form>
                
<form method="get" action="acceuil-user" >
                <button>login</button></form>
                
                

</div>
</body>
</html>