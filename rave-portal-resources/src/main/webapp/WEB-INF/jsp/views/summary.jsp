<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Professional Summary</title>

<link rel="stylesheet" href="css/rave.css"/>


</head>
<body>
<div id="show" class="profile-info-visible">

 <div id="skillList">		
<h3>Professional Summary<c:url var="editUrl" value="/summary/add.html?id=${summary1.userName}&referringPageId=${summary1.referringPageId}" />
 &nbsp;<a href="${editUrl}"><img alt="Edit" title="Edit" src="css/images/icon_edit.png" height="14" align="middle"></a></h3>	
<ul>
<li>
<span>Skill & Expertise:</span><c:forTokens items="${summary.skills}" varStatus="i"  delims="," var="token"> 
<c:out value="${token}" />
<c:forTokens items="${summary.level}" varStatus="i2" begin="${i.index}" end="${i.index}"  delims="," var="token2">
<em>(Rating is <c:out value="${token2}"/>)</em>
</c:forTokens>
</c:forTokens> 
</li>
<li>
<span>Professional Experience:</span><c:out value="${summary.goals}" />
</li>
<li>
<span>Specialities:</span><c:out value="${summary.specialities}" />
</li>
</ul>


<c:out value="${summary.referringPageId}"></c:out>


</div>
</div>
<%--  
<div id="form">

 <form:form  method="POST"  modelAttribute="summary">


<table>
<tr><td align="char">Professional Experience & Goals :</td><td> <form:textarea path="goals" value="${summary.goals}" htmlEscape="true"  cols="40" rows="8" wrap="true" cssClass="span3 pull-left" /></td></tr>


<tr><td >Specialities :</td><td><input type="text" name="goals" value="${summary.goals}" /> </td></tr>

<tr><td>Skills:</td><td><form:input path="skills"  value="${summary.goals}"/></td></tr> 

</table>
</form:form> 

</div> 
 --%>
 
 </body>
</html>