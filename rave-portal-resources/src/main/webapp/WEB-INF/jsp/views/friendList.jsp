<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:import url="/includes/header.jsp"/>
<c:import url="/includes/menu.jsp"/>
<div class="section-left">
<div class="friendList search-result ui-tabs ui-widget ui-widget-content ui-corner-all">
<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
<c:forEach items="${friendList}" var="connection" varStatus="i2" >
<c:set var="salary" scope="session" value="${0}"/>
<li class="ui-state-default ui-corner-top">
<div class="profile-user-thumb">
<img alt="${connection.name}" height="50px" width="50px" src='../<c:out value='${connection.picture}' />'>
</div>
<div>
<h2>
<c:out value="${connection.lastName}" /> , <c:out value="${connection.name}" />
</h2>
<h3>
<c:out value="${connection.headline}" />
</h3>
<c:forEach items="${friendInList}" var="inList" varStatus="i" >
<c:if test="${inList.followedId==connection.userId}">
<span style="color: #999; font-size: 10px;">Already a Friend</span>
<c:set var="salary" scope="session" value="${1}"/>
</c:if>
</c:forEach>

<c:if test="${salary!=1}">

<form:form action="sendRequest.html" modelAttribute="connection" >
<input type="hidden" name="followedId" value="${connection.userId}" />
<input type="hidden" name="userId" value="pranav" />
<input type="hidden" name="firstName" value="${connection.name}" />
<input type="hidden" name="lastName" value="${connection.lastName}" />
<input type="hidden" name="headline" value="${connection.headline}" />
<input type="hidden" name="picture" value="${connection.picture}" />
<input type="hidden" name="status" value="HOLD" />
<input type="submit" value="ADD Friend" class="btn btn-info" >
<c:set var="salary" scope="session" value="${2}"/>
</form:form>
</c:if>
</div>
</li>
</c:forEach>
</ul>
</div>
</div>
<c:import url="/includes/footer.jsp"/>