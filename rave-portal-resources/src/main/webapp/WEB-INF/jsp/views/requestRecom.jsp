<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sec:authentication property="principal.username" var="principleUsername" scope="request"/>
<sec:authentication property="principal.displayName" var="displayName" scope="request"/>
<c:set var="currentUsername"><sec:authentication property="principal.username" htmlEscape="false" /></c:set>
<c:if test="${experience.userName==currentUsername}">
<form:form  method="POST" modelAttribute="requestRecommend" action="saveRequest.html">
<input type="hidden" name="recommendForTitle"  value="${experience.jobTitle} at ${experience.companyName}"/>
Ask the people who know you best to endorse you on LinkedIn
<br />
<br />
1.Choose what you want to be recommended for  <br />
<c:out value="${experience.jobTitle}" /> at <c:out value="${experience.companyName}" />
<%-- <form:input path="fk_key_for_exp"  value="${requestRecommend.recommendForTitle}"/> --%>
<input type="hidden" name="fk_key_for_exp"  value="${experience.pkExperienceId}" />
<%--  <form:select path="">

<form:options items="${listUserName}"/>
</form:select> --%>
 <br />
  <br />
2.Decide who you will ask <br />
<form:select path="recommendBy">
<form:options items="${listUserName}"/>

</form:select> 
<br />
 <input type="submit" value="sent"/>
</form:form>

</c:if>
<c:if test="${experience.userName!=currentUsername}">
You are not Authenticated to view this.
</c:if>
</body>
</html>