<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form  method="POST" modelAttribute="addEducation" action="education/save.html">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="form">
<tr>
<td><label>School</label><form:input path="school"   cssClass="span3 pull-left" /></td></tr><tr>
<td><label>Degree</label><form:input path="degree" cssClass="span3 pull-left" /></td></tr><tr>
<td><label>Study</label><form:input path="fieldStudy"  cols="30" rows="5" cssClass="span3 pull-left" /></td>
</tr>
<tr>
<td><label>Activity</label><form:input path="activity"   cssClass="span3 pull-left" /></td></tr><tr>
<td><label>Attended From Date</label><form:input path="toDate"   cssClass="span3 pull-left" /></td></tr><tr>
<td><label>End Date</label><form:input path="endDate"  cssClass="span3 pull-left" /></td>
</tr>
<tr>
<td><label>Grade</label><form:input path="grade"   cssClass="span3 pull-left" /></td>
</tr>
<tr><td><label>Additional Notes</label><form:textarea path="additionalNotes"  cols="30" rows="2" cssClass="span3 pull-left" /></td></tr>

<form:hidden path="userId"/>
<form:hidden path="pkEducationId"/>
<tr><td><input type="hidden" name="referringPageId" value="${addEducation1.referringPageId}" /></td></tr>

<tr><td><input type="submit" value="Add Education" class="btn btn-primary" />
<a href="education.html?name=${addEducation.userId}&referringPageId=${addEducation1.referringPageId}"
					class="btn btn-primary" id="cancelEdit">Cancel</a>
				</td></tr>
 
</table>
</form:form>
</body>
</html>