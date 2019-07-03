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

<c:forEach items="${experience}" var="experience"  >
<div class="profile-info-visible">
<h3>
 
<c:out value="${experience.jobTitle}" />
<em>at
<c:out value="${experience.companyName}"></c:out></em>
<a href="experience/edit.html?id=${experience.pkExperienceId}&referringPageId=${experience1.referringPageId}"><img alt="Edit" title="Edit" src="css/images/icon_edit.png" height="14" align="middle"></a>
<a href="experience/remove.html?id=${experience.pkExperienceId}&name=${experience1.userName}&referringPageId=${experience1.referringPageId}"><img alt="Remove" title="Remove" src="css/images/icon_remove.png" height="14" align="middle"></a>
</h3>
<ul>
<li>
<span>From:</span><c:out value="${experience.startDate}" /> &nbsp;|&nbsp; <span>To:</span><c:out value="${experience.endDate}" /><c:out value="${experience.currentlyWork}" />
</li>
<li><span>Work Location:</span><c:out value="${experience.companyLocation}" /></li>
<li><span>Working as:</span><c:out value="${experience.jobDesc}"></c:out></li>
</ul>
<c:forEach items="${recommendations}" var="recommend">
<c:if test="${recommend.fk_key_for_exp==experience.pkExperienceId && recommend.status=='ACCEPT'}">
<c:out value="${recommend.name }"/> <c:out value="${recommend.lastName }"/> <c:out value="${recommend.recommendByTitle }"/>

</c:if>
</c:forEach>
</div>


<div class="separator"></div>
</c:forEach>

<%-- <c:forEach items="${recommendations}" var="recommend">
<c:out value="${recommend.recommendBy }"></c:out>




</c:forEach> --%>
<div class="profile-info-visible">
<a href="experience/add.html?name=${experience1.userName}&referringPageId=${experience1.referringPageId}" class="btn btn-primary">Add Experience</a>
</div>

</body>
</html>