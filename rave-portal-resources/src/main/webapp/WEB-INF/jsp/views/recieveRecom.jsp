<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/includes/header.jsp"/>
<c:import url="/includes/menu.jsp"/>

<h2>Manage recommendations you have received</h2>
<c:forEach items="${recieveRecommend}" var="experience"  >


<h3>
 
<c:out value="${experience.jobTitle}" />
<em>at
<c:out value="${experience.companyName}"></c:out></em>
<c:set var="headline" value="${experience.jobTitle} at ${experience.companyName}" />
</h3>
You have 
<c:set var="countHidden" value="${0}" />
<c:set var="countAccept" value="${0}" />
<c:set var="countPending" value="${0}" />
<c:forEach items="${recommendations}" var="recommend" varStatus="io" >
<c:if test="${recommend.fk_key_for_exp==experience.pkExperienceId}">

<c:if test="${recommend.status=='ACCEPT'}">
<c:set var="countAccept" value="${countAccept+1}" />
</c:if>

<c:if test="${recommend.hide=='true' && recommend.status=='ACCEPT'}">
<c:set var="countHidden" value="${countHidden+1}" />
</c:if>
<c:if test="${recommend.status=='PENDING'}">
<c:set var="countPending" value="${countPending+1}" />
</c:if>
</c:if>
</c:forEach>
<c:out value="${countAccept }" />
<c:if test="${countAccept<=1}">
recommendation
</c:if>
<c:if test="${countAccept>1}">
recommendations
</c:if>
<c:if test="${countAccept==0}">
 for this position. [ <a rel="facebox" href="request.html?forPos=${experience.pkExperienceId}"> Ask to be endorsed </a>  ]
 </c:if>
 <c:if test="${countAccept!=0}">
 for this position. [ <a href="request.html?forPos=${experience.pkExperienceId}"> Manage</a>  | <a rel="facebox" href="request.html?forPos=${experience.pkExperienceId}"> Ask to be endorsed </a>  ]
 (<c:out value="${countAccept-countHidden}"></c:out> visible and <c:out value="${countHidden}"></c:out> hidden ) and <c:out value="${countPending}"/> pending request .
 </c:if>

<div class="separator"></div>
</c:forEach>
<c:import url="/includes/footer.jsp"/>