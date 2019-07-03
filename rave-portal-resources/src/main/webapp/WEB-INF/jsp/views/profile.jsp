<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<jsp:include page="/includes/header.jsp" />

<script>
function clickLinkedin(v)
{
	
	window.showModalDialog('http://10.36.76.123:8081/rave/linked.jsp?userId='+v,'dialogHeight:50px;dialogWidth:100px');
	}
function clickFacebook(v)
		{
			
			var popup = window.showModalDialog('http://localhost:8081/rave/fb.jsp?userId='+v);
			setTimeout(function(){popup.close()},2000)
			}
function clickYahoo(v)
{
	
	window.showModalDialog('http://www.brturism.com/facebook_app/lYahoo3.php?userId='+v,'dialogHeight:50px;dialogWidth:100px');
	}
function clickGoogle(v)
{
	
	window.showModalDialog('http://www.brturism.com/facebook_app/googleProfile.html?userId='+v,'dialogHeight:50px;dialogWidth:100px');
	}
function clickTwitter(v)
{
	
	window.showModalDialog('http://10.36.76.123:8081/rave/twitterAPI.jsp?userId='+v,'dialogHeight:50px;dialogWidth:100px');
	}
	
function statusR(v)
{
	
	if(v!='Married')
		{
		 
			document.getElementById("anni").value="";
			document.getElementById("anni").disabled="true";
		}
	else{
		document.getElementById("anni").disabled="";
		
	}
		
	}
	
	function searchPeople(v){
		
		window.location="connection/getFriend.html?keywords="+v;
		
	}
</script>
<script language="javascript">
	var counts;
	function addRow4(tableID) {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		counts = rowCount - 1;

		var cell1 = row.insertCell(0);
		var skill = document.createElement("input");
		skill.type = "text";
		skill.name = "email";
		skill.id = "jsp_" + counts + "__skill";
		skill.setAttribute("class", "");
		cell1.appendChild(skill);

		var cell2 = row.insertCell(1);
		var skill = document.createElement("label");
		skill.id = "jsp_" + counts + "__skill";
		skill.setAttribute("class", "");
		cell2.appendChild(skill);

		var cell3 = row.insertCell(2);
		var rate = document.createElement("label");
		rate.id = "jsp_" + counts + "__skill";
		rate.setAttribute("class", "");
		cell3.appendChild(skill);

	}
	function addRow1(tableID) {

		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;

		var row = table.insertRow(rowCount);
		counts = rowCount - 1;

		var cell3 = row.insertCell(0);
		var rate = document.createElement("input");
		rate.type = "text";
		rate.name = "im";
		rate.setAttribute("class", "");
		rate.id = "jsp_" + counts + "__rate";
		cell3.appendChild(rate);

		var cell2 = row.insertCell(1);	
		var selector = document.createElement('select');
		selector.id = 'selBidReceivedIsPM';
		selector.name = 'imType';
		cell2.appendChild(selector);
		var option = '';
		var skill = "Yahoo,GTalk,Skype,Window Live Messenger";
		var token = skill.split(",");
		
		option = document.createElement('option');
		option.value = "";
		option.appendChild(document.createTextNode("Select IMType"));
		selector.appendChild(option);
		for(var i = 0;i<token.length;i++){	
		option = document.createElement('option');
		option.value = token[i];
		option.appendChild(document.createTextNode(token[i]));
		selector.appendChild(option);
		}

		
		
		var cell1 = row.insertCell(2);
		var skill = document.createElement("label");
		skill.id = "jsp_" + counts + "__skill";
		skill.setAttribute("class", "");
		cell1.appendChild(skill);
	}
	function addRow5(tableID) {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		counts = rowCount - 1;

		

		var cell2 = row.insertCell(0);
		var skill = document.createElement("input");
		skill.type = "text";
		skill.name = "number";
		skill.id = "jsp_" + counts + "__skill";
		skill.setAttribute("class", "");
		cell2.appendChild(skill);

		
		
		var cell2 = row.insertCell(1);	
		var selector = document.createElement('select');
		selector.id = 'selBidReceivedIsPM';
		selector.name = 'numberType';
		cell2.appendChild(selector);
		var option = '';
		var skill = "Home,Office,Mobile";
		var token = skill.split(",");
		option = document.createElement('option');
		option.value = "";
		option.appendChild(document.createTextNode("Select NumberType"));
		selector.appendChild(option);
		
		for(var i = 0;i<token.length;i++){	
		option = document.createElement('option');
		option.value = token[i];
		option.appendChild(document.createTextNode(token[i]));
		selector.appendChild(option);
		}
		
		var cell1 = row.insertCell(2);
		var skill = document.createElement("label");
		skill.id = "jsp_" + counts + "__skill";
		skill.setAttribute("class", "");
		cell1.appendChild(skill);
	}

	function deleteRow(guestList) {
		try {
			//alert("1");
			var table = document.getElementById(guestList);
			var rowCount = table.rows.length;
			if (rowCount > 2) {
				//alert("2");
				table.deleteRow(rowCount - 1);
			}
		} catch (e) {
			alert(e);
		}
	}
</script>

<!--  <script type="text/javascript" src="js/jquery.cookies.2.2.0.min.js"></script> -->


<script>
	$(function() {
		//var cookieName = "mycookie";

		/* $("#tabs").tabs({
			selected : ($.cookies.get(cookieName) || 0),
			select : function(e, ui) {
				$.cookies.set(cookieName, ui.index);

			}
		}); */
		$('#tabs').tabs({
			selected : 0,
			load : function(event, ui) {
				$(ui.panel).delegate('a', 'click', function(event) {
					$(ui.panel).load(this.href);
					event.preventDefault();
				});
			}
		});
		$('#tabs1').tabs({
			selected : 0,
			load : function(event, ui) {
				$(ui.panel).delegate('a', 'click', function(event) {
					$(ui.panel).load(this.href);
					event.preventDefault();
				});
			}
		});
		$('#tabs2').tabs({
			selected : 0,
			load : function(event, ui) {
				$(ui.panel).delegate('a', 'click', function(event) {
					$(ui.panel).load(this.href);
					event.preventDefault();
				});
			}
		});
		$('#tabs3').tabs({
			selected : 0,
			load : function(event, ui) {
				$(ui.panel).delegate('a', 'click', function(event) {
					$(ui.panel).load(this.href);
					event.preventDefault();
				});
			}
		});

		/* $( "#tabs" ).tabs({
			cookie: {
				// store cookie for a day, without, it would be a session cookie
				expires: 1
			}
		}); */

		/*  $( "#tabs" ).tabs({ selected: v,
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				}
			}
		}); */
		/* $('#tabs').tabs({
		    load: function(event, ui) {
		        $(ui.panel).delegate('a', 'click', function(event) {
		            $(ui.panel).load(this.href);
		            event.preventDefault();
		        });
		    }
		});
		
		
		 */

		/* var $tabs = $('#tabs').tabs();
		$('#gg').click(function() { // bind click event to link
			alert("09");
		    $tabs.tabs('select', 2); // switch to third tab
		    return false;
		}); */

		/* $( "#tabs" ).tabs({ selected: selected,
			ajaxOptions: {
				
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				}
			}
		}); */

	});

	
</script>
<script type="text/javascript">
	function showNHide() {
		//alert("dgfdf");
		document.getElementById("personProfileContent").style.display = "none";
		document.getElementById("editable").style.display = "";
	}
	
</script>
<jsp:include page="/includes/menu.jsp" />
<sec:authentication property="principal.username" var="principleUsername" scope="request"/>
<sec:authentication property="principal.displayName" var="displayName" scope="request"/>
<c:set var="currentUsername"><sec:authentication property="principal.username" htmlEscape="false" /></c:set>
	<div id="pageContent">
		<div  class="section-right">Right</div>
			
				<div id="personProfileContent" class="section-left">
					<fieldset class="row-fluid" id="userProfilePrimaryData">
						<div class="profile-user-thumb">
						 <c:if test="${profile.picture!=null  || profile.picture!=' ' }"><img src='<c:out value="${profile.picture}"></c:out>' height="165px" width="125px"  /></c:if>
							<c:if test="${profile.picture==null  || profile.picture==' ' }"><img src="<c:out value='http://10.36.76.123:8081/rave/css/images/photo.gif'></c:out>" /> </c:if>
						</div>

						
							<!-- Display user info-->
							<div class="profile-info-visible">
								<h2>
									<c:out value="${profile.name}"></c:out> &nbsp;
									<c:if test="${currentUsername == profile.userId}">
									<a href="javascript:void(0)" id="profileEdit" onclick="showNHide()"><img alt="Edit" title="Edit" src="css/images/icon_edit.png" height="14" align="middle"></a> 
									</c:if>
								</h2>
								<h3>
									<span id="givenName"><c:out value="${profile.headline}"></c:out></span>&nbsp;
								</h3>
								
<ul>
<li class="spe">
<c:out value="${profile.industry}" /> &nbsp;&nbsp;|&nbsp;&nbsp; <c:out value="${profile.city}" />
</li>
<li>
<span class="icon birth">Born on:</span><c:out value="${profile.birthDay}" />
<c:if test="${profile.status=='Married'}">
<span class="icon anniversary">Married on:</span><c:out value="${profile.anniversary}" />
</c:if>
<c:if test="${profile.status!='Married'}">
<span class="icon anniversary"></span><c:out value="${profile.status}" />
</c:if>

</li>
<li>
<span class="icon email">Email:</span><c:out value="${profile.email}" />
</li>
<li>							
<span class="icon im">IM:</span><c:forTokens items="${profile.imType}" varStatus="i" delims="," var="token">
<c:forTokens items="${profile.im}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
<c:out value="${token2}" /><em>&nbsp;(<c:out value="${token}" />)</em>
</c:forTokens>
</c:forTokens>
</li>
<li>								
<span class="icon number">Contact:</span><c:forTokens items="${profile.numberType}" varStatus="i" delims="," var="token">
<c:forTokens items="${profile.number}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
<c:out value="${token2}" /><em>&nbsp;(<c:out value="${token}" />)</em>
</c:forTokens>
</c:forTokens>
</li>
<li>			
<span class="icon address">Lives at:</span><c:out value="${profile.address}" />
</li>
</ul>
				</div>
<div class="social-media"><a  onclick="clickFacebook('${profile.userId}')"><img src="images/spacer.gif" border="0" class="icon facebook"></a>
<a onclick="clickLinkedin('${profile.userId}')"><img src="../css/images/spacer.gif" border="0" class="icon linkedin"></a>
<a onclick="clickYahoo('${profile.userId}')"><img src="images/spacer.gif" border="0" class="icon yahoo"></a>
<a onclick="clickGoogle('${profile.userId}')"><img src="images/spacer.gif" border="0" class="icon google"></a>
<a onclick="clickTwitter('${profile.userId}')"><img src="images/spacer.gif" border="0" class="icon twitter"></a>

<div class="search"><input type="text" value="" /><img src="css/images/spacer.gif" border="0" class="search-icon" onClick="searchPeople(this.value)"></div>

<!-- <a href="#" oclick>Search People</a> -->
</div>
						
					</fieldset>
				</div>
				<!-- Display contact information of user -->
			





	<div id="editable" style="display: none;" class="section-left">

				<div id="personProfileContent">
					<fieldset class="row-fluid" id="userProfilePrimaryData">
						<div class="profile-user-thumb">
							<c:if test="${profile.picture!=null  || profile.picture!=' ' }"><img src='<c:out value="${profile.picture}"></c:out>' height="165px" width="125px"  /></c:if>
							<c:if test="${profile.picture==null  || profile.picture==' ' }"><img src="<c:out value='http://10.36.76.123:8081/rave/css/images/photo.gif'></c:out>" /> </c:if>
							<div align="center"><a rel="facebox" href="profile/upload.html?name=${profile.userId}&referringPageId=${profile1.referringPageId}" class="btn btn-primary">Change Picture</a></div>
							<!-- <div align="center"><a href="#" class="btn btn-warning">Remove Picture</a></div> -->
						</div>


						<!-- Display user info-->
						
							<div class="profile-info-visible" style="width:77%;">
								<form:form commandName="profile" method="POST"
									action="profile/save.html">
									<table width="100%" border="0" cellspacing="0" cellpadding="0" class="form">
										<tr>
										
											<td width="33%"><label>First Name</label><form:input path="name" value="${profile.name}" title="First Name" /></td>
											<td width="33%"><label>Middle Name</label><form:input path="maidenName" value="${profile.maidenName}" title="Middle Name" /></td>
											<td width="33%"><label>Last Name</label><form:input path="lastName" value="${profile.lastName}" title="Last Name" /></td>
										</tr>
										<input type="hidden" name="picture" value="${profile.picture}"  />
										<tr>
											<td colspan="3"><label>Profile HeadLine</label><textarea name="headline" rows="1">${profile.headline}</textarea></td>
										</tr>
										<tr>
											<td><label>Maritual Status</label>
											<c:set var="statusVal" value="Single,In a Relationship,Engaged,Married,Divorced,In an Open Relationship" ></c:set>
											<form:select path="status"  onchange="statusR(this.value)" value="${profile.status}">
											<%-- <c:if test="${profile.status!=''}"><form:option value="${profile.status}" selected="" >${profile.status}</form:option></c:if> --%>
												<c:forTokens items="${statusVal}" delims="," var="token3" >
												<c:if test="${profile.status==token3}"><form:option value="${profile.status}" selected="selected" >${profile.status}</form:option></c:if>
												<c:if test="${profile.status!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select>
										
										 <%-- <form:input path="status" value="${profile.status}"
													id="auto1" /></td>  --%>
										
											<td><label>Birthday</label><form:input path="birthDay"
													value="${profile.birthDay}" /></td>
											<td><label>Marriage Anniversary</label>
											<c:if test="${profile.status=='Married'}">
											<form:input path="anniversary"
													value="${profile.anniversary}" id="anni" />
													</c:if>
											<c:if test="${profile.status!='Married'}">
											<form:input path="anniversary"
													value="" disabled="true"  id="anni" />
													</c:if></td>
										
									<tr><td colspan="3">
									<div class="separator"></div>
									
									<table id="emailT" width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="33%"><label>Email</label></td>
												<td width="33%">&nbsp;</td>
												<td width="33%">&nbsp;</td>
											</tr>
											<c:if test="${( profile.email==null) || ( profile.email=='')   }">
												<tr>

													<td><form:input path="email" value="${profile.email}" /></td>
													<td></td>

													<td><a id="profileEdit" onclick="addRow4('emailT')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('emailT')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
														</td>



												</tr>

											</c:if>
											<c:if test="${(profile.email!=null) && ( profile.email!='')  }">
												
													<c:forTokens items="${profile.email}" varStatus="i"
														delims="," var="token">
														<tr>


																
																<td><form:input path="email" value="${token}" /></td>
															
															<td>				</td>
														
															<td><c:if test="${i.index==0}"><a id="profileEdit" onclick="addRow4('emailT')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('emailT')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a></c:if></td>



														</tr>
													</c:forTokens>
											
											</c:if>
										</table>
									
									
									<div class="separator"></div>
									
									<table id="phone" width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="33%"><label>Phone Number</label></td>
												<td width="33%"><label>Number Type</label></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<c:if test="${( profile.number==null) || ( profile.number=='') 
											|| ( profile.number=='null')   }">
												<tr>

													<td><form:input path="number" value="${profile.number}" /></td>
													<td><c:set var="phoneType" value="Mobile,Home,Office" ></c:set>
															<form:select path="numberType" >
											      <form:option value= "">Select NumberType</form:option>
												<c:forTokens items="${phoneType}" delims="," var="token3" >
												
													<form:option value="${token3}">${token3}</form:option>
												
												</c:forTokens>								
											</form:select></td>

													<td>													
														<a id="profileEdit" onclick="addRow5('phone')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('phone')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
														
														
														</td>



												</tr>

											</c:if>
											<c:if test="${(profile.number!=null) && ( profile.number!='')  }">
												
													<c:forTokens items="${profile.numberType}" varStatus="i"
														delims="," var="token">
														<tr>


																<c:forTokens items="${profile.number}" varStatus="i2"
																begin="${i.index}" end="${i.index}" delims=","
																var="token2">
																<td><form:input path="number" value="${token2}" /></td>
															</c:forTokens>
															<td>
															
															
															
															<c:set var="phoneType" value="Mobile,Home,Office" ></c:set>
															<form:select path="numberType" >
											      <form:option value= "">Select NumberType</form:option>
												<c:forTokens items="${phoneType}" delims="," var="token3" >
												<c:if test="${token==token3}"><form:option value="${token}" selected="selected" >${token}</form:option></c:if>
												<c:if test="${token!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select>
															<%-- <form:input path="numberType" value="${token}" id="phonet" /> --%></td>
														
															<td><c:if test="${i.index==0}">
															<!-- <input type="button" onclick="addRow('phone')"
																value="Add" />
																<input type="button"
																onclick="deleteRow('phone')" value="Delete" /> -->
																
																<a id="profileEdit" onclick="addRow5('phone')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('phone')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
																
																</c:if></td>



														</tr>
													</c:forTokens>
											
											</c:if>
										</table>
										<div class="separator"></div>
										<table id="im" width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="33%"><label>IM Id</label></td>
												<td width="33%"><label>IM Type</label></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<c:if
												test="${(profile.imType==null && profile.im==null) || 
								(profile.imType=='' && profile.im=='')   }">
												<tr>


													<td><form:input path="im" value="${profile.im}" /></td>
													<td><c:set var="im1Type" value="Yahoo,GTalk,Skype,Window Live Messenger" ></c:set>
															<form:select path="imType" >
											      <form:option value= "">Select IMType</form:option>
												<c:forTokens items="${im1Type}" delims="," var="token3" >
												<c:if test="${token==token3}"><form:option value="${token}" selected="selected" >${token}</form:option></c:if>
												<c:if test="${token!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select></td>
													

													<td>
													<!-- <input type="button" onclick="addRow1('im')"
														value="Add" /><input type="button" onclick="deleteRow('im')"
														value="Delete" /> -->
														<a id="profileEdit" onclick="addRow1('im')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('im')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
														
														
														</td>



												</tr>

											</c:if>
											<c:if test="${profile.imType!=null &&profile.im!=null  }">
												
													<c:forTokens items="${profile.imType}" varStatus="i"
														delims="," var="token">
														
<tr>

															<tr>
															
															<c:forTokens items="${profile.im}" varStatus="i2"
																begin="${i.index}" end="${i.index}" delims=","
																var="token2">
																<td><form:input path="im" value="${token2}" /></td>
															</c:forTokens>
															<td>
															<%-- <form:input path="imType"  value="${token}"/> --%>
																	<c:set var="im1Type" value="Yahoo,GTalk,Skype,Window Live Messenger" ></c:set>
															<form:select path="imType" >
											      <form:option value= "">Select IMType</form:option>
												<c:forTokens items="${im1Type}" delims="," var="token3" >
												<c:if test="${token==token3}"><form:option value="${token}" selected="selected" >${token}</form:option></c:if>
												<c:if test="${token!=token3}">
													<form:option value="${token3}">${token3}</form:option>
												</c:if>
												</c:forTokens>								
											</form:select>
																	
																	</td>
															<td><c:if test="${i.index==0}">
															
															<!-- <input type="button" onclick="addRow1('im')"
																value="Add" /><input type="button" onclick="deleteRow('im')"
																value="Delete" />
																 -->
																<a id="profileEdit" onclick="addRow1('im')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_add_2.png" title="Add Row" alt="ADD">
														</a>
														<a id="profileEdit" onclick="deleteRow('im')" href="javascript:void(0)">
															<img height="20" align="middle" src="css/images/icon_remove_2.png" title="Delete Row" alt="Delete">
														</a>
																</c:if></td>


																</tr>
														
													</c:forTokens>
												
											</c:if>
										</table>
										<div class="separator"></div>
										
										</td>
										<tr>
											<td><label>Current Industry</label><form:input path="industry"
													value="${profile.industry}" /></td>
										
											<td><label>Current Address</label><form:input path="address"
													value="${profile.address}" /></td>
										
											<td><label>Current City</label><form:input path="city" value="${profile.city}" /></td>
										</tr>
										<tr>
											<td><label>Pin Code</label><form:input path="pinCode"
													value="${profile.pinCode}" /></td>
													<td colspan="2">&nbsp;</td>
										</tr>
										<tr>
											<td><input type="hidden" name="userId"
												value="${profile.userId}" /> <input type="hidden"
												name="referringPageId" value="${profile1.referringPageId}" /></td>
											<td colspan="2"></td>
										</tr>
										<tr>
											<td><input type="submit" class="btn btn-primary"
												value="Save" />
												<a href="profile.html?id=/app/person/${profile.userId}?referringPageId=${profile1.referringPageId}"
												class="btn btn-primary" id="cancelEdit">Cancel</a></td>
										</tr>
									</table>
								</form:form>
							</div>
					
					</fieldset>
				</div>


	</div>
<div id="tabs">
			<ul>
				<li><a
					href="summary.html?name=${profile.userId}&referringPageId=${profile1.referringPageId}">Professional
						Summary</a></li>
				

			</ul>

		</div>
<div id="ui-tab-common" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
  <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
    <li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active"><a href="#">Network Details</a></li>
  </ul>
  <div class="ui-tabs-panel ui-widget-content ui-corner-bottom">
    <div class="profile-info-visible">
      <h3><a href="connection.html">Connections </a> (${profile1.countConnection})<!-- <a href="#" class="link">Import New Contact</a> --></h3>
      <h3><a rel="facebox" href="connection/showRequest.html">Friend Request </a> (${profile1.countRequest})<!-- <a href="#" class="link">Add New Friends</a> --></h3>
      <h3><a rel="facebox" href="recommendation/showPendingRecommend.html">Recommendations </a> <a href="recommendation/recieve.html" class="link">Ask for Recommendation</a></h3>

    </div>
  </div>
</div>

<div id="tabs2">
			<ul>
				
				<li><a
					href="education.html?name=${profile.userId}&referringPageId=${profile1.referringPageId}">Education</a></li>
				

			</ul>

		</div>
		

		<div id="tabs1">
			<ul>
				
				<li><a
					href="experience.html?name=${profile.userId}&referringPageId=${profile1.referringPageId}">Experience</a></li>
				

			</ul>

		</div>
		<div id="tabs3">
			<ul>
				
				<li><a
					href="info.html?name=${profile.userId}&referringPageId=${profile1.referringPageId}">Additional
						Information</a></li>

			</ul>

		</div>

		
		

		
				
		

	</div>

	<!-- End demo -->

<jsp:include page="/includes/footer.jsp" />







