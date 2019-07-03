<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %><html>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Map your account</title>
<link href="/portal/static/css/rave.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="section-internal">
<div>
<form method="POST" action="authCreateAccount.html">
<div><label>Create Account</label>
<label class="note">Would you like to create new account with following</label>

  <label class="note">Email ID:</label>
  <input class="input-large" autofocus="autofocus" type="text" name="newUserName" value="${profile.email}"  style="width:350px; height:auto !important;" />
</div>
<div><input type="submit" value="Create New Account" class="btn btn-primary"  /></div>
</form></div>
<div>
<span class="bullet">or</span>
<label>Please enter your credentials</label>
<label class="note">Map your existing Account with ${profile.providerId} account to keep things simple .</label>
<form method="POST" action="mapAccount.html">
<div>
  	<label class="note">Username:</label>
 	<input class="input-large" autofocus="autofocus" type="text" name="mappedUserName" value=""  style="width:350px; height:auto !important;" />
</div>
<div>
  	<label class="note">Password:</label>
 	<input class="input-large" autofocus="autofocus" type="password" name="mappedPassword" value=""  style="width:350px; height:auto !important;" />
</div>

<div><input type="submit" value="Sign In" class="btn btn-primary" /></div>
</form>

</div>


</body>
</html>