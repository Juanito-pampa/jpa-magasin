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
<h1>Inscription</h1>
<%
String errorMessage = (String) request.getAttribute("errorMessage");
if(errorMessage==null){
	errorMessage="Completez les champs du formulaire";
}
	
%>
<form action="inscription" method="post">
    login : <input type="text" name="login" />
    mot de passe : <input type="text" name="password" />
   	prenom : <input type="text" name="prenom" />
    nom : <input type="text" name="nom" />
    email : <input type="text" name="email" />
<button type="submit">S'inscrire</button></form>


<div style="color: red"><%=errorMessage %></div>
</br>
<form method="get" action="login" >
                <button>retour login</button></form>

</body>
</html>