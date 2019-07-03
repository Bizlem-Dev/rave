<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript">
	var counts;
	function addRow(tableID) {
		
		
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		counts = rowCount - 1;
		

		
		var cell2 = row.insertCell(0);	
		var selector = document.createElement('select');
		selector.id = 'selBidReceivedIsPM';
		selector.name = 'skills';
		cell2.appendChild(selector);
		var option = '';
		var skill = "Java,.Net,Python,php,Linux,Window";
		var token = skill.split(",");
		for(var i = 0;i<token.length;i++){	
		option = document.createElement('option');
		option.value = token[i];
		option.appendChild(document.createTextNode(token[i]));
		selector.appendChild(option);
		}
		
		
		var cell3 = row.insertCell(1);
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "level";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts
		+ "__rate";
		cell3.appendChild(rate);
		
		var cell1 = row.insertCell(2);
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

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.23.min.js"></script>
<script type="text/javascript" src="js/highlight.min.js"></script>
<script type="text/javascript" src="js/jquery.dynotable.js"></script>

</head>
<body>
	<form:form method="POST" modelAttribute="addSummary"
		action="summary/save.html">

		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="form">
			<tr>
				<td colspan="3">
				<label>Professional Experience &amp; Goals</label>
				<form:textarea path="goals" cols="40" rows="2" wrap="true" /></td>
			</tr>


			<tr>
				<td colspan="3">
				<label>Specialities</label>
				<form:textarea path="specialities" cols="40" rows="2" /></td>
			</tr>
			<tr>
				<td><input type="hidden" name="userName"
					value="${addSummary1.userName }" /> <input type="hidden"
					name="referringPageId" value="${addSummary1.referringPageId }" /></td>
			</tr>
			<tr>



					<td width="45%"><label>Skill & Expertise</label>
					</td>
					
					<td width="15%"><label>Rate</label>
					</td>
					<td>&nbsp;</td>
					</tr>
					<tbody id="skillList">
					<c:if test="${addSummary.skills!=null && addSummary.level!=null  }">
				<c:forTokens items="${addSummary.skills}" varStatus="i" delims=","
					var="token">
				
				<tr>
				<td><c:set var="skillType" value="Java,.Net,Python,php,Linux,Window" ></c:set>
															<form:select path="skills" >
											      
												<c:forTokens items="${skillType}" delims="," var="token3" >
												<c:if test="${token==token3}"><form:option value="${token}" selected="selected" >${token}</form:option></c:if>
												<c:if test="${token!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select></td>
				<td><c:forTokens items="${addSummary.level}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
						
						<form:input path="level" value="${token2}" />
						</c:forTokens>
						</td>
				<td><c:if test="${i.index==0}">
			<!-- 	<input type="button" onclick="addRow('skillList')" value="Add" />
						<input type="button" onclick="deleteRow('skillList')" value="Delete" />
						 -->
						&nbsp; <a id="profileEdit" onclick="addRow('skillList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('skillList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
						
						</c:if></td>
				</tr>
				
				
				
						</c:forTokens>
						</c:if>
				
				
				
				<c:if test="${(addSummary.skills==null && addSummary.level==null) || 
								(addSummary.skills=='' && addSummary.level=='') || 
								(addSummary.skills==',' && addSummary.level==',')   }">


						<tr>
						<td><c:set var="skillType" value="Java,.Net,Python,php,Linux,Window" ></c:set>
															<form:select path="skills" >
											      
												<c:forTokens items="${skillType}" delims="," var="token3" >
												<c:if test="${token==token3}"><form:option value="${token}" selected="selected" >${token}</form:option></c:if>
												<c:if test="${token!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select></td>
							<td><form:input path="level" value="${addSummary.level}" /></td>
						
						<td><!-- <input type="button" onclick="addRow('skillList')"
							value="Add" /><input type="button" onclick="deleteRow('skillList')"
							value="Delete" /> -->
						&nbsp; <a id="profileEdit" onclick="addRow('skillList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('skillList')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
							
							</td>

								</tr>
					
					</c:if>
				
					
				
							</tbody>
			<tr>
				<td colspan="3"><input type="submit" value="Save Changes" class="btn btn-primary" />
				<a href="summary.html?name=${addSummary1.userName}&referringPageId=${addSummary1.referringPageId}"
					class="btn btn-primary" id="cancelEdit">Cancel</a>
				</td>
			</tr>

		</table>
	</form:form>
</body>
</html>