<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function work(value){
		
		//alert(document.getElementById("endDate").checked);
		if(value.checked==true)
			{
					document.getElementById("endDate").value="";
					document.getElementById("endDate").disabled="true";
			}
		else
			{
				
				document.getElementById("endDate").disabled="";
			}
		
		
	}
	
	
	

</script>
</head>
<body>
<form:form  method="POST" modelAttribute="addExperience" action="experience/save.html">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="form">
<tr>
<td><label>Company Name</label><form:input path="companyName" /></td>
<td ><label>Company Location</label><form:input path="companyLocation" /></td>
<td><label>Company Website</label><form:input path="companyWebsite" /></td>
</tr>
<tr><td colspan="3"><label>Company Description</label><form:textarea path="companyDesc"  cols="30" rows="2" /></td></tr>
<tr>
<td><label>Start Date</label><form:input path="startDate" /></td>
<td><label>End Date</label><c:if test="${addExperience.currentlyWork==null}"><form:input path="endDate"  id="endDate" /></c:if>
							<c:if test="${addExperience.currentlyWork!=null}"><form:input path="endDate" disabled="true"  id="endDate" /></c:if></td>
<td><label>Currently Working</label><form:checkbox path="currentlyWork" value="Present" onchange="work(this)"/></td>
</tr>
<tr>
<td><label>Job Title</label><form:input path="jobTitle" /></td>
<td><label>Job Description</label><form:input path="jobDesc" /></td><td colspan="1">&nbsp;</td></tr>

<form:hidden path="userName"/>
<form:hidden path="pkExperienceId"/>

<%-- <form:input path="referringPageId" value="${addExperience1.referringPageId}" /> --%>
<tr><td>
<input name="referringPageId" type="hidden" value="${addExperience1.referringPageId}" /></td></tr>
<!-- <tr id="TextBoxesGroup"><td><label>Skills :</label></td><td id="TextBoxDiv1"><input type='textbox' id='textbox1'  name='skills' /></td></tr>
	
<tr><td><input type='button' value='Add Button' id='addButton'/>
<input type='button' value='Remove Button' id='removeButton'/>
<input type='button' value='Get TextBox Value' id='getButtonValue/'></td></tr>-->
<tr><td colspan="3"><input type="submit" value="Add Experience"class="btn btn-primary" />
<a href="experience.html?name=${addExperience.userName}&referringPageId=${addExperience.referringPageId}"
					class="btn btn-primary" id="cancelEdit">Cancel</a>
				</td></tr>
 
</table>
</form:form>
</body>
</html>