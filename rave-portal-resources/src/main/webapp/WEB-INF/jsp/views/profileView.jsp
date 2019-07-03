
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<sec:authentication property="principal.id" var="principleid" scope="request"/>
<sec:authentication property="principal.authorities[0].entityId" var="roleId" scope="request"/>
<sec:authentication property="principal.username" var="principleUsername" scope="request"/>
<fmt:setBundle basename="jdbc" var="jdbc"/>
<fmt:message key="sling.serverSpec" var="slingUrl"  bundle="${jdbc}"/>
<fmt:message key="chat.server" var="chatUrl"  bundle="${jdbc}"/>
<html style="overflow-y: hidden;" >
<head>
<title>Person Profile</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<script>
	function logout(){	
	// ** window.location="/portal/j_spring_security_logout";
		$.ajax({
					  type: 'GET',
                      url: '<c:out value="${chatUrl}"/>/OpenFireClient/Logout.jsp',
                      data: 'username=u<c:out value="${principleid}"/>', 
                      complete: function(){
							window.location="<c:out value="${slingUrl}"/>/logout.jsp";
					  }
		});
	}
	
	function loadFrame(value,obj){
		$(".frameLoad").parent().show();
		$(obj).parent().hide();
		if(value=='privacy'){
			$("#frameId").attr('src','<c:out value="${slingUrl}"/>/servlet/security/verify.privacy?userId=<c:out value="${profile}"/>');
		}else if(value=='resume'){
			$("#frameId").attr('src','<c:out value="${slingUrl}"/>/content/user/<c:out value="${profile}"/>.resumeBuilder');
		}else if(value=='profile'){
			$("#frameId").attr('src','<c:out value="${slingUrl}"/>/content/user/info?id=<c:out value="${profile}"/>');
		}
	}
</script>	
</head>
<body style="padding-bottom: 0px !important;">

	<!-- get the display name of user -->
	<!-- get the title of personal information -->
	<!-- get the title of basic information -->
	<!-- get the title of contact information -->
<!-- 

	$(document).ready(function(){
					$("#profileFrameShow").load("http://10.36.76.123:8081/sling/content/user/info?id=pran3bull_gmail.com")
					});
					
					-->
					
	<div class="navbar">
		<div class="navbar-inner" style="border-radius: 0 0 0 0;">
			<div class="container">

				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				
				<div class="nav-collapse">
					<ul class="nav pull-right">

						
						<li><a href="${pageContext.request.contextPath}/app/page/view/1">Back to Home</a></li>
					<c:if test="${roleId=='2'}" >
						<li><a href="${pageContext.request.contextPath}/app/admin/">Admin interface</a></li>
					</c:if>
						<li><a href="${pageContext.request.contextPath}/secure/changePassword.html">Change Password</a></li>
						<li style="display:none;" ><a href="javascript:void(0);" onclick="loadFrame('profile',this)"  class="frameLoad" >Profile</a></li>
						<li><a href="javascript:void(0);" onclick="loadFrame('resume',this)" class="frameLoad" >Resume</a></li>
						<li><a href="javascript:void(0);" onclick="loadFrame('privacy',this)" class="frameLoad" >Privacy Setting</a></li>
						<li><a href="javascript:void(0);" onclick="logout()">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
<c:choose>	
	<c:when test="${fn:replace(principleUsername,'@', '_')==param['id']}">	 
		<iframe id="frameId" frameborder="0" seamless="seamless" src="<c:out value="${slingUrl}"/>/content/user/info?id=<c:out value='${profile}'/>" height="93%" width="100%"  /> 
	</c:when>
	<c:otherwise>
		<iframe id="frameId" frameborder="0" seamless="seamless" src="${slingUrl}/content/user/info?id=${profile}&userId=${fn:replace(principleUsername,'@', '_')}&friendId=${profile}" height="93%" width="100%"  /> 
	</c:otherwise>
</c:choose>	

	</body>


</html>