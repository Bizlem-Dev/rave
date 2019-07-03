<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	function changeWebsiteType(i) {
		var a = i;
		document.getElementById('websitetype').className = 'website-type ' + a;
		document.getElementById('webTypeId').value = i;
	}
	function changeDynamic(i,v) {
		var a = i;
		var iconType="websitetype"+v;
		var type="webTypeId"+v;
		document.getElementById(iconType).className = 'website-type ' + a;
		document.getElementById(type).value = i;
	}
</script>

<script language="javascript">
	var counts;
	function addRow(tableID) {

		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		counts = rowCount - 1;

		var cell3 = row.insertCell(0);
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "websites";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts + "__rate";
		cell3.appendChild(rate);
		
		var cell2 = row.insertCell(1);
		
		/* cell2.innerHTML="<ul>"+
		"<li onClick=\"changeDynamic(\'office\',"+counts+")\">" +"</li></ul>"; */
	
		 cell2.innerHTML="<ul id='nav'></span><li><a href='javascript:void(0)'></a>"+
		"<ul><li><a href='javascript:void(0);'></a><ul><li onclick=\"changeDynamic(\'personal\',"+counts+")\">"
		+"<a href='javascript:void(0)'><span class='website-type personal'></span>Personal</a></li>"+
		"<li onclick=\"changeDynamic(\'office\',"+counts+")\"><a href='javascript:void(0)'><span class='website-type office'></span>Office</a>"+
		"</li><li onclick=\"changeDynamic(\'blog\',"+counts+")\"><a href='javascript:void(0)'><span class='website-type blog'></span>Blog</a>"+
		"</li><li onclick=\"changeDynamic(\'rss\',"+counts+")\"><a href='javascript:void(0)'><span class='website-type rss'></span>RSS</a></li>"+
		"</ul></li></ul><span id='websitetype"+counts+"' class='website-type personal' style='float: right; margin-top: 5px;'>"+
		" <input type='hidden' id='webTypeId"+counts+"' name='webType' value='' />";
 
		

		var cell1 = row.insertCell(2);
		var skill = document.createElement("label");
		skill.id = "jsp_" + counts + "__skill";
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
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" modelAttribute="addinfo"
		action="info/save.html">

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form">
			<tr>
				<td><label>Websites</label><a id="profileEdit" onclick="addRow('infoP')" href="javascript:void(0)">
							<img height="14" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
				</a><a id="profileEdit" onclick="deleteRow('infoP')" href="javascript:void(0)">
					<img height="14" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
				</a>
				
				<!-- <input type="button" onclick="addRow('infoP')" value="Add" />
						<input type="button" onclick="deleteRow('infoP')" value="Delete" /> -->
				<%-- <form:select path=""></form:select> --%></td>
			</tr>
			<tbody id="infoP">
				<c:if test="${ addinfo.webType!=null  }">
					<c:forTokens items="${addinfo.webType}" varStatus="i" delims=","
						var="token">

						<tr>
							<td><c:forTokens items="${addinfo.websites}" varStatus="i2"
									begin="${i.index}" end="${i.index}" delims="," var="token2">

									<form:input path="websites" value="${token2}"
										cssClass="span3 pull-left" cssStyle="width:200px !important;" />
								</c:forTokens></td>
							<td><ul id="nav">
									<li><a href="javascript:void(0)"></a>
										<ul>
											<li onclick="changeWebsiteType('personal')"><a
												href="javascript:void(0)"><span
													class="website-type personal"></span>Personal</a></li>
											<li onclick="changeWebsiteType('office')"><a
												href="javascript:void(0)"><span
													class="website-type office"></span>Office</a></li>
											<li onclick="changeWebsiteType('blog')"><a
												href="javascript:void(0)"><span
													class="website-type blog"></span>Blog</a></li>
											<li onclick="changeWebsiteType('rss')"><a
												href="javascript:void(0)"><span class="website-type rss"></span>RSS</a></li>
										</ul></li>
								</ul> 
								<c:if test="${addinfo.webType=='personal'}"><span id="websitetype" title="personal" class="website-type personal"
								style="float: right; margin-top: 5px;"></span></c:if>
								<c:if test="${addinfo.webType=='office'}"><span id="websitetype" title="office" class="website-type office"
								style="float: right; margin-top: 5px;"></span></c:if>
								<c:if test="${addinfo.webType=='blog'}"><span id="websitetype" title="blog" class="website-type blog"
								style="float: right; margin-top: 5px;"></span></c:if>
								<c:if test="${addinfo.webType=='rss'}"><span id="websitetype" title="rss"  class="website-type rss"
								style="float: right; margin-top: 5px;"></span></c:if>
								
								<input
								type="hidden" id="webTypeId" name="webType"
								value="${addinfo.webType}" /></td>

							<td><c:if test="${i.index==0}">
									 <!-- <input type="button" onclick="addRow('infoP')" value="Add" />
						<input type="button" onclick="deleteRow('infoP')" value="Delete" />  -->
								</c:if></td>
						</tr>



					</c:forTokens>
				</c:if>



				<c:if
					test="${(addinfo.webType==null) || 
								(addinfo.webType=='') || 
								(addinfo.webType==',')   }">


					<tr>
						<td><form:input path="websites" value="${addinfo.websites}" /></td>
						<td><ul id="nav">
								<li><a href="javascript:void(0)"></a>
									<ul>
										<li onclick="changeWebsiteType('personal')"><a
											href="javascript:void(0)"><span
												class="website-type personal"></span>Personal</a></li>
										<li onclick="changeWebsiteType('office')"><a
											href="javascript:void(0)"><span
												class="website-type office"></span>Office</a></li>
										<li onclick="changeWebsiteType('blog')"><a
											href="javascript:void(0)"><span class="website-type blog"></span>Blog</a></li>
										<li onclick="changeWebsiteType('rss')"><a
											href="javascript:void(0)"><span class="website-type rss"></span>RSS</a></li>
									</ul></li>
							</ul> <span id="websitetype" class="website-type personal"
							style="float: right; margin-top: 5px;"></span> <input
							type="hidden" id="webTypeId" name="webType"
							value="${addinfo.webType}" /></td>


						<td><input type="button" onclick="addRow('infoP')"
							value="Add" /><input type="button" onclick="deleteRow('infoP')"
							value="Delete" /></td>

					</tr>

				</c:if>



			</tbody>

			<tr>
				<td colspan="2"><label>Groups and Association</label>
				<form:textarea path="groupsAssociations" cols="30" rows="2"
						cssClass="span3 pull-left" /></td>
			</tr>
			<tr>
				<td colspan="2"><label>Interests</label>
				<form:textarea path="interests" cols="30" rows="2"
						cssClass="span3 pull-left" /></td>
			</tr>
			<tr>
				<td colspan="2"><label>Honors and Awards</label>
				<form:textarea path="honorsAwards" cols="30" rows="2"
						cssClass="span3 pull-left" /></td>
			</tr>
			<form:hidden path="userId" />
			<tr>
				<td><input type="hidden" name="referringPageId"
					value="${addinfo1.referringPageId}" /></td>
			</tr>
			<!-- <tr id="TextBoxesGroup"><td><label>Skills :</label></td><td id="TextBoxDiv1"><input type='textbox' id='textbox1'  name='skills' /></td></tr>
	
<tr><td><input type='button' value='Add Button' id='addButton'/>
<input type='button' value='Remove Button' id='removeButton'/>
<input type='button' value='Get TextBox Value' id='getButtonValue/'></td></tr>-->
			<tr>
				<td colspan="2"><input type="submit" value="Save Article"
					class="btn btn-primary" /> <a
					href="info.html?name=${addinfo.userId}&referringPageId=${addinfo1.referringPageId}"
					class="btn btn-primary" id="cancelEdit">Cancel</a></td>
			</tr>

		</table>
	</form:form>
</body>
</html>