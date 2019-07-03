<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
ACCEPTED
<br />
<c:forEach items="${manage}" var="manage" >
<c:if test="${manage.status=='ACCEPT'}">
<form:form modelAttribute="showHideRecommend" action="saveRecommend.html">
<form:checkbox path="hide" checked="false" value="dd"/>

<c:out value="${manage.name }"/>
<c:out value="${manage.lastName }"/><br />
<c:out value="${manage.recommendType }"/><br /> 
<c:out value="${manage.description}"/><br />
<c:out value="${manage.recommendByTitle }"/><br />
</form:form>

</c:if>


</c:forEach>
<br />
PENDING
<br />
<c:forEach items="${manage}" var="manage" >
<c:if test="${manage.status=='PENDING'}">

<c:out value="${manage.name }"/>
<c:out value="${manage.lastName }"/><br />
<%-- <c:out value="${manage.recommendType }"/><br /> 
<c:out value="${manage.description}"/><br />
<c:out value="${manage.recommendByTitle }"/><br /> --%>

</c:if>
</c:forEach>
</body>
</html>