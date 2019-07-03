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
<c:forEach items="${education}" var="education"  >
<div class="profile-info-visible">
<h3>
<c:out value="${education.degree}" />
<em>from <c:out value="${education.school}" /></em>
<a href="education/edit.html?id=${education.pkEducationId}&referringPageId=${education1.referringPageId}"><img alt="Edit" title="Edit" src="css/images/icon_edit.png" height="14" align="middle"></a>
<a href="education/remove.html?id=${education.pkEducationId}&name=${education1.userId}&referringPageId=${education1.referringPageId}"><img alt="Remove" title="Remove" src="css/images/icon_remove.png" height="14" align="middle"></a> 
</h3>

<ul>
<li>
<span>From:</span><c:out value="${education.toDate}" /> &nbsp;|&nbsp; <span>To:</span><c:out value="${education.endDate}" />
</li>
<li><span>Branch:</span><c:out value="${education.fieldStudy}" /></li>
<li><span>Grade:</span><c:out value="${education.additionalNotes}" /> </li>
</ul>
</div>
<div class="separator"></div>
</c:forEach>

<div class="profile-info-visible">
<a href="education/add.html?name=${education1.userId}&referringPageId=${education1.referringPageId}" class="btn btn-primary">Add Education</a>
</div>
</body>
</html>