<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${fn:length(showRecommend)==0}">
No such request
</c:if>

 <c:forEach items="${showRecommend}" var="show">

 <c:out value="${show.name }" />&nbsp;<c:out value="${show.lastName }" /> has requested a recommendation as 
 <c:out value="${show.recommendForTitle }" />

<%-- <c:out value="${show.recommendBy}"></c:out>  --%>
<a href="recommendation/pendingRecommend.html?id=${show.recommendId}" >Write Recommendation</a>  <a href="recommendation/removeRecommend.html?id=${show.recommendId}" >Delete Recommendation</a>
</c:forEach> 
</body>
</html>