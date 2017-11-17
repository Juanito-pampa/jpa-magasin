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
	<h1>Login</h1>
	</br>
<%String errorMessage=(String) request.getAttribute("errorMessage");
if(errorMessage==null){
	errorMessage="Completez les champs du formulaire";
}%>
<form action="login" method="post">
    Login : <input type="text" name="login" />
    Mot de passe : <input type="text" name="password" />
</br><div style="color: red"><%=errorMessage %></div>
<button type="submit">Se connecter</button></form>

</br>

<form method="get" action="inscription" >
                <button>s'inscrire</button></form>
                
                

</body>
</html>