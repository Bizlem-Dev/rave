<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Would you like to create New Account with following 
Email Address: 
<form method="POST" action="authCreateAccount.html">
<input type="text" name="newUserName" value="${profile.email}" />
<input type="submit" value="Create New Account"  />
</form>
OR 

Map your existing Account with Facebook Account to keep things simple
Please enter your credentials:
<form method="POST" action="mapAccount.html">
<input type="text" name="mappedUserName" value="" />
<input type="password" name="mappedPassword" value="" />
<input type="submit" value="Sign In"  />
</form>
</body>
</html>