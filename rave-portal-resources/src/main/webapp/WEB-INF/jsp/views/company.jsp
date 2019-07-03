<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/includes/header.jsp"/>
<script>
	var counts;
	function addRow(tableID) {
		
		
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		counts = rowCount - 1;
		

		
		var cell2 = row.insertCell(0);	
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "level";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts
		+ "__rate";
		cell2.appendChild(rate);
		
		
		var cell3 = row.insertCell(1);
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "level";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts
		+ "__rate";
		cell3.appendChild(rate);
		
		var cell4 = row.insertCell(2);
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "level";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts
		+ "__rate";
		cell4.appendChild(rate);
		
		var cell1 = row.insertCell(3);
		var skill = document.createElement("label");
		skill.id = "jsp_" + counts
				+ "__skill";
		skill.setAttribute("class", "");
		cell1.appendChild(skill);		
	}

	function deleteRow(guestList) {
		try {
			var table = document.getElementById(guestList);
			var rowCount = table.rows.length;
			if (rowCount > 1) {
				table.deleteRow(rowCount - 1);
			}
		} catch (e) {
			alert(e);
		}
	}


</script>
<c:import url="/includes/menu.jsp"/>
 

<div id="personProfileContent">
					<fieldset class="row-fluid" id="userProfilePrimaryData">
						<div class="profile-user-thumb">
<img alt="Company logo" src="${pageContext.request.contextPath}/css/images/photo.gif" height="200px" width="200px"  >
</div>
<table>
<tr><td><br />Major Export</td></tr>
<tr><td>Major Import</td></tr>
<tr><td>Major Purchase</td></tr>
<tr><td>Major Sell</td></tr>
<tr><td>Major Raw Material</td></tr>
<tr><td>Major Capital Equipment Used</td></tr>
<tr><td>Inquiries</td></tr>
<tr><td>Career</td></tr>

</table>
</fieldset>
<form:form modelAttribute="company" action="saveProfile.html" >
<table>
<%-- <tr><td></td><td><form:input path="companyId"/></td></tr>

 <tr><td>Major Import :</td><td><form:input path="majorImport" value="${company.majorImport}"  /></td></tr>
 <tr><td>Major Export :</td><td><form:input path="export" value="${company.majorImport}" /></td></tr>
 <tr><td>Major Purchase :</td><td><form:input path="purchase" value="${company.majorImport}"/></td></tr>
 <tr><td> Major Sell :</td><td><form:input path="sell" value="${company.majorImport}"/></td></tr>
 <tr><td> Major Raw Material :</td><td><form:input path="rawMaterial" value="${company.majorImport}"/></td></tr>
 <tr><td>Major Capital Equipment Used :</td><td><form:input path="equipmentUsed" value="${company.majorImport}"/></td></tr>
 <tr><td>Inquiries :</td><td><form:input path="inquiries" value="${company.majorImport}"/></td></tr>
 <tr><td>Career :</td><td><form:input path="career" value="${company.majorImport}"/></td></tr> --%>
 <tr>
 <td>Company Name :</td><td colspan="3"><form:input path="name" value="${company.majorImport}"/></td></tr>
 <tr><td>WebSite URL :</td><td><form:input path="website" value="${company.majorImport}" /></td></tr>
 <tr><td>Description :</td><td><form:textarea path="description" value="${company.majorImport}"/></td></tr>
 <tr><td>Industries :</td><td><form:input path="industries" value="${company.majorImport}"/></td></tr>
 <tr><td>No. of Employee :</td><td><form:input path="employeesNum" value="${company.majorImport}"/></td></tr>
 <tr><td>Type :</td><td><form:input path="type" value="${company.majorImport}"/></td></tr>
 <tr><td>Yearly TurnOver :</td><td><form:input path="turnOver" value="${company.majorImport}"/></td></tr>
 <tr><td>Founded :</td><td><form:input path="founded" value="${company.majorImport}"/></td></tr>
 <tr><td colspan="2"><u>Head Quarter</u></td></tr>
<%--  <tr><td></td><td><form:input path="headQuater"/></td></tr> --%>
 <tr><td>Address :</td><td><form:input path="address" value="${company.majorImport}"/></td></tr>
 <tr><td>City :</td><td><form:input path="city" value="${company.majorImport}"/></td></tr>
 <tr><td>Postal Code :</td><td><form:input path="postalCode" value="${company.majorImport}"/></td></tr>
 <tr><td>Land Line Number :</td><td><form:input path="workNumber" value="${company.majorImport}"/></td></tr>
 <tr><th>Mobile Number</th><th>Contact Email Id</th><th>Creator Email Id</th></tr>
<%--  <tr><td><form:input path="mobileNumber" value="${company.mobileNumber}"/></td>
 	 <td><form:input path="contactEmailId" value="${company.contactEmailId}"/></td>
 	 <td><form:input path="creatorEmailId" value="${company.creatorEmailId}"/></td></tr> --%>
 	 <tbody id="contactList">
					<c:if test="${company.mobileNumber!=null && company.contactEmailId!=null && company.creatorEmailId!=null }">
				<c:forTokens items="${company.mobileNumber}" varStatus="i" delims=","
					var="token">
				
				<tr>
				<td><form:input path="mobileNumber" value="${company.mobileNumber}"/></td>
				<td><c:forTokens items="${company.contactEmailId}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
						
						<form:input path="contactEmailId" value="${company.contactEmailId}"/>
						<c:forTokens items="${company.creatorEmailId}" varStatus="i3" begin="${i2.index}" end="${i2.index}" delims="," var="token3">
							<td><form:input path="creatorEmailId" value="${company.creatorEmailId}"/></td>
						</c:forTokens>
						</c:forTokens>
						</td>
				<td><c:if test="${i.index==0}">
			<!-- 	<input type="button" onclick="addRow('skillList')" value="Add" />
						<input type="button" onclick="deleteRow('skillList')" value="Delete" />
						 -->
						&nbsp; <a id="profileEdit" onclick="addRow('contactList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('contactList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
						
						</c:if></td>
				</tr>
				
				
				
						</c:forTokens>
						</c:if>
				
				
				
				<c:if test="${(company.mobileNumber==null && company.contactEmailId==null && company.creatorEmailId==null) || 
								(company.mobileNumber=='' && company.contactEmailId=='' && company.creatorEmailId==null) || 
								(company.mobileNumber==',' && company.contactEmailId==',' && company.creatorEmailId==null)   }">


						<tr>
						<td><form:input path="mobileNumber" value="${company.mobileNumber}"/></td>
							<td><form:input path="contactEmailId" value="${company.contactEmailId}" /></td>
							<td><form:input path="creatorEmailId" value="${company.creatorEmailId}" /></td>
						<td><!-- <input type="button" onclick="addRow('skillList')"
							value="Add" /><input type="button" onclick="deleteRow('skillList')"
							value="Delete" /> -->
						&nbsp; <a id="profileEdit" onclick="addRow('contactList')" href="javascript:void(0)">
															<img height="20" align="middle" src="${pageContext.request.contextPath}/css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('contactList')" href="javascript:void(0)">
															<img height="20" align="middle" src="${pageContext.request.contextPath}/css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
							
							</td>

								</tr>
					
					</c:if>
				
					
				
							</tbody>
 
 <tr><td>Your Title :</td><td><form:input path="title" value="${company.majorImport}"/></td></tr>
 <tr><td>Joining Date :</td><td><form:input path="joiningDate" value="${company.majorImport}"/></td></tr>
 <tr><td><input type="submit" value="Save"  /></td></tr>
 </table>
</form:form>

</div>
<c:import url="/includes/footer.jsp"/>