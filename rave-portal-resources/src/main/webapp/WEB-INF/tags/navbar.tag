<%--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="pageTitle" required="false" description="The title of the page" %>
<sec:authentication property="principal.username" var="principleUsername" scope="request"/>
<sec:authentication property="principal.id" var="principleid" scope="request"/>
<sec:authentication property="principal.displayName" var="displayName" scope="request"/>
<c:set var="currentUsername"><sec:authentication property="principal.username" htmlEscape="false" /></c:set>
<fmt:setBundle basename="messages" var="portalKey"/>
<fmt:setBundle basename="jdbc" var="jdbc"/>
<fmt:message key="chat.server" var="chatUrl"  bundle="${jdbc}"/>
<fmt:message key="sling.serverSpec" var="slingUrl"  bundle="${jdbc}"/>
<c:if test="${not empty topnav}">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <%--@elvariable id="topnav" type="org.apache.rave.portal.web.model.NavigationMenu"--%>
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <span class="brand"><c:out value="${pageTitle}"/></span>
                <div class="nav-collapse">
                     <ul class="nav pull-right">
                        <%--@elvariable id="navItem" type="org.apache.rave.portal.web.model.NavigationItem"--%>
                         <c:forEach items="${topnav.navigationItems}" var="navItem">
                            <sec:authorize url="${navItem.url}">
                                <c:choose>
									
                                    <c:when test="${not empty navItem.nameParam}">
                                        <li><a href="/portal/profile/getProfile.html?id=<c:out value="${fn:replace(currentUsername,'@','_')}"/>"><fmt:message key="${navItem.name}" bundle="${portalKey}">
                                            <fmt:param><c:out value="${navItem.nameParam}"/></fmt:param>
                                        </fmt:message></a></li>
                                    </c:when>
                                    <c:otherwise>
										<c:if test="${navItem.name=='page.general.logout'}"><li><a href="javascript:void(0);" onclick="logOut()"><fmt:message key="${navItem.name}" bundle="${portalKey}"/></a></li></c:if>
										<c:if test="${navItem.name!='page.general.logout'}">
											<li>
												<a href="<spring:url value="${navItem.url}"/>" ><fmt:message key="${navItem.name}" bundle="${portalKey}"/></a>
											</li>
										</c:if>	
									</c:otherwise>
                                </c:choose>
                            </sec:authorize>
                         </c:forEach>
                     </ul>
                 </div>
            </div>
        </div>
    </div>
</c:if>
<script>
	function logOut(){	
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

</script>
