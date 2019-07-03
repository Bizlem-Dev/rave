 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="show" class="profile-info-visible">
<h3>Additional Info
<c:url var="editUrl" value="/info/add.html?name=${info1.userId}&referringPageId=${info1.referringPageId}" />
<a href="${editUrl}"><img alt="Edit" title="Edit" src="css/images/icon_edit.png" height="14" align="middle"></a>
</h3>
<ul>
<li>
<span>Websites:</span><c:out value="${info.websites}" />
</li>
<li>
<span>Groups:</span><c:out value="${info.groupsAssociations}" /> 
</li>
<li>
<span>Interests:</span><c:out value="${info.interests}" />
</li>
<li>
<span>Awards:</span><c:out value="${info.honorsAwards}" />
</li>

</div>

</body>
</html>