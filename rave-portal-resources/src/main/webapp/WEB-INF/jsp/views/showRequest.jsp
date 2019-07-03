<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>


<html>
<head>
<script>
	function reject(v){
		alert(v);
		document.getElementById("rejectFormId"+v).action="rejectRequest.html"
		//rejectForm+v.action ="rejectRequest.html";
		
		//myf
		//rejectForm.submit();
		document.getElementById("rejectFormId"+v).submit();
		
	}


</script>
</head>
<body>
<c:if test="${showRequest[0]==null}">No Records to display</c:if>
<c:forEach items="${showRequest}" var="connection" varStatus="i"  >
 <img alt="${connection.profileJoin.name}" height="50px" width="50px" src='../<c:out value='${connection.profileJoin.picture}' />'> 
 <c:out value="${connection.profileJoin.lastName}" /> ,  <c:out value="${connection.profileJoin.name}" /> 

<br />
<c:out value="${connection.profileJoin.headline}" /> 
<form:form action="acceptRequest.html" name="rejectForm${connection.friendId }" id="rejectFormId${connection.friendId }" modelAttribute="connection"  >
<input type="hidden" name="friendId" value="${connection.friendId }"  />
<input type="hidden" name="followedId" value="${connection.followedId }"  />
<input type="hidden" name="userId" value="${connection.userId }"  />
<input type="hidden" name="status" value="ACCEPTED"  />
<input type="submit"  value="Accept"  />
<input type="button"  value="Reject" onclick="reject('${connection.friendId }')" />

</form:form>

<div class="separator"></div>
</c:forEach>
</body>
</html>