<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="profile-info-visible">
<div class="profile-user-thumb">
<img alt="${friendView.name}" height="100px" width="100px" src='<c:out value='${friendView.picture}' />'>
</div>
<h2>
<a href="profile.html?id=/app/person/${friendView.userId}?referringPageId=" target="_blank" ><c:out value="${friendView.name}" /> <c:out value="${friendView.lastName}" /></a> 
</h2>
<h3>
<c:out value="${friendView.headline}" />
</h3>
<span>Email:</span>
<c:forTokens items="${friendView.email}" varStatus="i" delims="," var="token">
<a href="mailto:${token}" ><c:out value="${token}" /></a>
</c:forTokens>

<br /><br /><span>Born on:</span>
<c:if test="${friendView.birthDay!=null}"><c:out value="${friendView.birthDay}" /></c:if>
<br /><br /><span>IM:</span>
<c:forTokens items="${friendView.imType}" varStatus="i" delims="," var="token">
<c:forTokens items="${friendView.im}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
<c:out value="${token2}" /><em>&nbsp;(<c:out value="${token}" />)</em>
</c:forTokens>

</c:forTokens>
<br /><br /><span>Contact:</span>
<c:forTokens items="${friendView.numberType}" varStatus="i" delims="," var="token">
<c:forTokens items="${friendView.number}" varStatus="i2" begin="${i.index}" end="${i.index}" delims="," var="token2">
<c:out value="${token2}" /><em>&nbsp;(<c:out value="${token}" />)</em>
</c:forTokens>
</c:forTokens>
<br /><br /><span>Lives at:</span>
<c:out value="${friendView.address}" /> 


</div>



