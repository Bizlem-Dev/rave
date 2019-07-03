<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="/includes/header.jsp" />
<script>
	$(function() {
		//var cookieName = "mycookie";

	
	
	$( "#tabs" ).tabs({
		ajaxOptions: {
			error: function( xhr, status, index, anchor ) {
				$( anchor.hash ).html(
					"Couldn't load this tab. We'll try to fix this as soon as possible. " +
					"If this wouldn't be a demo." );
			}
		}
	});
});

</script>
<script>
function setHREF(followerId){
	alert("sdfsd");
	document.getElementById("tabId").href="connection/view.html?id="+followerId;
}
</script>

<jsp:include page="/includes/menu.jsp" />
<div class="section-left">
<c:if test="${connection[0]==null}">No Records to display</c:if>

<div id="tabs" class="friendList">
<ul>
<c:forEach items="${connection}" var="connection"  >
<li>
<a href="connection/view.html?id=${connection.followedId }"  >
<div class="profile-user-thumb">
<img alt="${connection.name}" height="50px" width="50px" src='<c:out value='${connection.picture}' />'>
</div>
<div class="profile-info-visible">
<h2>
<c:out value="${connection.lastName}" /> , <c:out value="${connection.name}" />
</h2>
<h3>
<c:out value="${connection.headline}" /> 
</h3>
</div>
</a>
</li>
</c:forEach>
</ul>
</div>
</div>
<jsp:include page="/includes/footer.jsp" />