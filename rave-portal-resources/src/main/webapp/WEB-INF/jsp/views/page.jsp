<%@ taglib prefix="portal" uri="http://www.apache.org/rave/tags" %>
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
<%@ page language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<fmt:setBundle basename="messages" var="portalKey"/>
<fmt:setBundle basename="jdbc" var="jdbc"/>
<jsp:useBean id="pages" type="java.util.List<org.apache.rave.portal.model.Page>" scope="request"/>
<jsp:useBean id="pageUser" type="org.apache.rave.portal.model.PageUser" scope="request"/>
<jsp:useBean id="pageLayouts" type="java.util.List<org.apache.rave.portal.model.JpaPageLayout>" scope="request"/>
<%--@elvariable id="page" type="org.apache.rave.portal.model.Page"--%>
<sec:authentication property="principal.username" var="principleUsername" scope="request"/>
<sec:authentication property="principal.displayName" var="displayName" scope="request"/>
<sec:authentication property="principal.id" var="principleid" scope="request"/>
<sec:authentication property="principal.encrypt" var="principleEnc" scope="request"/>
<sec:authentication property="principal.extensionNumber" var="principleExtension" scope="request"/>
<sec:authentication property="principal.authorities[0].entityId" var="roleId" scope="request"/>
<fmt:message key="page.home.welcome" var="pagetitle" bundle="${portalKey}">
    <fmt:param>
        <c:choose>
            <c:when test="${not empty displayName}"><c:out value="${displayName}"/></c:when>
            <c:otherwise><c:out value="${principleUsername}"/></c:otherwise>
        </c:choose>
    </fmt:param>
</fmt:message>
<rave:navbar pageTitle="${pagetitle}"/>
<fmt:message key="chat.server" var="chatUrl"  bundle="${jdbc}"/>
<fmt:message key="phone.server" var="phoneUrl"  bundle="${jdbc}"/>
<div id="testDiv" class="navbar-spacer" align="right" class="navbar-spacer" style="display: block; height: 30px; position: fixed; right:0px;bottom: 0px; margin: 0px 0; padding: 0 0; z-index: 9999; border-radius: 4px 4px 0px 0px;"><div class="miximize" id="sendBtn"></div>
	<iframe name="bar1" id="bar1" 
	src="<c:out value="${chatUrl}"/>/OpenFireClient/Login.jsp?username=u<c:out value="${principleid}"/>&password=<c:out value="${principleEnc}"/>&nick=<c:out value="${displayName}"/>" frameborder="0" height="310px"></iframe></div>
<!--<div id="phoneDiv" class="navbar-spacer" align="right"  class="navbar-spacer" style="right:270px !important; display: block; height: 168px; position: fixed; right:0px;bottom: 0px; margin: 0px 0; padding: 0 0; z-index: 9999; border-radius: 4px 4px 0px 0px;"><iframe height="320px" id="bar2" width="220px" name="bar2" scrolling="no" frameborder="0" src="http://14.140.116.246:5080/sip/javascript/index.html?username=<c:out value="${principleExtension}"/>&password=05GDO05VVO0" ></iframe></div>-->

<div id="phoneDiv" class="navbar-spacer" align="right"  class="navbar-spacer" style="right:270px !important; display: block; height: 30px; position: fixed; right:0px;bottom: 0px; margin: 0px 0; padding: 0 0; z-index: 9999; border-radius: 4px 4px 0px 0px;"><iframe height="123px" id="bar2" width="180px" name="bar2" scrolling="no" frameborder="0" src="<c:out value="${phoneUrl}"/>/expert.htm?pid=<c:out value="${principleExtension}"/>&passwd=<c:out value="${principleExtension}"/>" ></iframe></div> 

<!-- ?pid=<c:out value="${principleExtension}"/>&passwd=<c:out value="${principleExtension}"/> -->
            <input type="hidden" id="destination" value="bar1" />
            <input type="hidden" id="message" autocomplete="off" value="message">
	
<input id="currentPageId" type="hidden" value="${page.id}"/>
<c:set var="hasOnlyOnePage" scope="request">
    <c:choose>
        <c:when test="${fn:length(pages) == 1}">true</c:when>
        <c:otherwise>false</c:otherwise>
    </c:choose>
</c:set>
<c:set var="canMoveWidgetsToEditablePage" scope="request" value="false"/>

<div id="pageContent" class="container-fluid full-width">
    <nav>
        <ul class="nav nav-tabs">
            <c:forEach var="userPage" items="${pages}">
                <%-- determine if the current page in the list matches the page the user is viewing --%>
                <c:set var="isCurrentPage">
                    <c:choose>
                        <c:when test="${page.id == userPage.id}">true</c:when>
                        <c:otherwise>false</c:otherwise>
                    </c:choose>
                </c:set>
                <c:set var="isSharedToMe">
                    <c:choose>
                        <c:when test="${userPage.owner.username == principleUsername}">false</c:when>
                        <c:otherwise>true</c:otherwise>
                    </c:choose>
                </c:set>
                <c:set var="isSharedByMe">
                    <c:choose>
                        <c:when test="${fn:length(userPage.members) > 1 and isSharedToMe == false}">true</c:when>
                        <c:otherwise>false</c:otherwise>
                    </c:choose>
                </c:set>
                <fmt:message key="sharing.page.tab.icon.tip.from" var="iconShareToolTipFrom" bundle="${portalKey}" >
                    <fmt:param value="${userPage.owner.username}"/>
                </fmt:message>
                <fmt:message key="sharing.page.tab.icon.tip.to" var="iconShareToolTipTo" bundle="${portalKey}" />
                <c:choose>
                    <c:when test="${isCurrentPage}">
                        <li id="tab-${userPage.id}" class="active <c:if test="${roleId=='2'}" > dropdown</c:if>" >
                            <a href="#" <c:if test="${roleId=='2'}" > class="dropdown-toggle" data-toggle="dropdown" </c:if> >
                                <c:if test="${isSharedToMe}">
                                    <b id="pageMenuSharedIcon" class="ui-icon ui-icon-person" title="<c:out value="${iconShareToolTipFrom}"/>"></b>
                                </c:if>
                                <c:if test="${isSharedByMe}">
                                    <b id="pageMenuSharedIcon" class="ui-icon ui-icon-folder-open" title="<c:out value="${iconShareToolTipTo}"/>"></b>
                                </c:if>
                                <c:out value="${userPage.name}"/>
								<c:if test="${roleId=='2'}" >
									<b class="caret"></b>
								</c:if>
                            </a>
							<c:if test="${roleId=='2'}" >
                            <ul class="dropdown-menu" >
                                <li id="pageMenuEdit" class="<c:if test="${isSharedToMe}">menu-item-disabled</c:if>"><a href="#"><fmt:message key="page.general.editpage" bundle="${portalKey}"/></a></li>
                                <li id="pageMenuDelete" class="<c:if test='${hasOnlyOnePage or isSharedToMe}'>menu-item-disabled</c:if>"><a href="#"><fmt:message key="page.general.deletepage" bundle="${portalKey}"/></a></li>
                                <li id="pageMenuMove" class="<c:if test='${hasOnlyOnePage}'>menu-item-disabled</c:if>"><a href="#"><fmt:message key="page.general.movepage" bundle="${portalKey}"/></a></li>
                                <li id="pageMenuShare" class="<c:if test="${isSharedToMe}">menu-item-disabled</c:if>"><a href="#"><fmt:message key="page.general.sharepage" bundle="${portalKey}"/></a></li>
                                <li id="pageMenuRevokeShare" class="<c:if test="${isSharedToMe == false}">menu-item-disabled</c:if>"><a href="#"><fmt:message key="page.general.removeshare" bundle="${portalKey}"/></a></li>
                            </ul>
							</c:if>
                        </li>
                    </c:when>
                    <c:otherwise>
						<c:choose>
							<c:when test="${userPage.id=='491' || userPage.id=='493' || userPage.id=='495' || 
									userPage.id=='499' || userPage.id=='603' || userPage.id=='605' || userPage.id=='567' ||
									userPage.id=='604' }" >
								<c:if test="${userPage.id=='493' && fn:substring(sessionScope.serviceCheck, 0, 0 + 1)=='Y' ||
								userPage.id=='491' && fn:substring(sessionScope.serviceCheck, 1, 1 + 1)=='Y' ||
								userPage.id=='495' && fn:substring(sessionScope.serviceCheck, 2, 2 + 1)=='Y' ||
								userPage.id=='499' && fn:substring(sessionScope.serviceCheck, 3, 3 + 1)=='Y' ||
								userPage.id=='603' && fn:substring(sessionScope.serviceCheck, 4, 4 + 1)=='Y' ||
								userPage.id=='605' && fn:substring(sessionScope.serviceCheck, 5, 5 + 1)=='Y' ||
								userPage.id=='567' && fn:substring(sessionScope.serviceCheck, 6, 6 + 1)=='Y' ||
								userPage.id=='604' && fn:substring(sessionScope.serviceCheck, 7, 7 + 1)=='Y' }" >
									<li id="tab-${userPage.id}" onclick="rave.viewPage(${userPage.id});">
										<c:choose>
											<c:when test="${isSharedToMe}">
												<a href="#" class="rave-ui-tab-shared-to-me">
													<b id="pageMenuSharedIcon" class="ui-icon ui-icon-person" title="<c:out value="${iconShareToolTipFrom}"/>"></b>
													<c:out value="${userPage.name}"/>
												</a>
											</c:when>
											<c:when test="${isSharedByMe}">
												<a href="#" class="rave-ui-tab-shared-by-me">
													<b id="pageMenuSharedIcon" class="ui-icon ui-icon-folder-open" title="<c:out value="${iconShareToolTipTo}"/>"></b>
													<c:out value="${userPage.name}"/>
												</a>
											</c:when>
											<c:otherwise>
												 <a href="#"><c:out value="${userPage.name}"/></a>
											</c:otherwise>
										</c:choose>
									</li>
								</c:if>	
							</c:when>
							<c:otherwise>
								<li id="tab-${userPage.id}" onclick="rave.viewPage(${userPage.id});">
									<c:choose>
										<c:when test="${isSharedToMe}">
											<a href="#" class="rave-ui-tab-shared-to-me">
												<b id="pageMenuSharedIcon" class="ui-icon ui-icon-person" title="<c:out value="${iconShareToolTipFrom}"/>"></b>
												<c:out value="${userPage.name}"/>
											</a>
										</c:when>
										<c:when test="${isSharedByMe}">
											<a href="#" class="rave-ui-tab-shared-by-me">
												<b id="pageMenuSharedIcon" class="ui-icon ui-icon-folder-open" title="<c:out value="${iconShareToolTipTo}"/>"></b>
												<c:out value="${userPage.name}"/>
											</a>
										</c:when>
										<c:otherwise>
											 <a href="#"><c:out value="${userPage.name}"/></a>
										</c:otherwise>
									</c:choose>
								</li>
							</c:otherwise>
						</c:choose>	
                    </c:otherwise>
                </c:choose>
                <c:forEach var="members" items="${userPage.members}">
                    <c:if test="${members.user.username == principleUsername and members.editor and userPage.id != page.id}">
                        <c:set var="canMoveWidgetsToEditablePage" scope="request" value="true"/>
                    </c:if>
               </c:forEach>
            </c:forEach>
			<c:if test="${roleId=='2'}" >
				<li id="addPageButton"><a href="#"><i class="icon-plus"></i></a></li>
			</c:if>
        </ul>
    </nav>


    <div class="row-fluid">
        <div id="emptyPageMessageWrapper" class="emptyPageMessageWrapper hidden">
            <div class="emptyPageMessage">
                <c:choose>
                    <c:when test="${pageUser.editor == true}">
                        <a href="<spring:url value="/app/store?referringPageId=${page.id}" />"><fmt:message key="page.general.empty" bundle="${portalKey}"/></a>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="page.general.non.editing.empty" bundle="${portalKey}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="regions">
            <%-- insert the region layout template --%>
            <tiles:insertTemplate template="${layout}"/>
        </div>
        <div class="clear-float">&nbsp;</div>
    </div>


    <div id="pageMenuDialog" class="modal hide" data-backdrop="static">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h3 id="pageMenuDialogHeader"></h3>
        </div>
        <div class="modal-body">
            <form id="pageForm" class="form-horizontal">
                <input type="hidden" name="tab_id" id="tab_id" value=""/>
                <fieldset>
                    <div class="control-group error">
                        <label id="pageFormErrors" class="control-label"></label>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="tab_title"><fmt:message key="page.general.addpage.title" bundle="${portalKey}"/></label>
                        <div class="controls">
                            <input id="tab_title" name="tab_title" class="input-xlarge focused required" type="text" value="" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="pageLayout"><fmt:message key="page.general.addpage.selectlayout" bundle="${portalKey}"/></label>
                        <div class="controls">
                            <select name="pageLayout" id="pageLayout">
                                <c:forEach var="pageLayout" items="${pageLayouts}">
                                    <option value="${pageLayout.code}" id="${pageLayout.code}_id">
                                        <fmt:message key="page.general.addpage.layout.${pageLayout.code}" bundle="${portalKey}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer">
            <a id="pageMenuCloseButton" href="#" class="btn"><fmt:message key="_rave_client.common.cancel" bundle="${portalKey}"/></a>
            <a id="pageMenuUpdateButton" href="#" class="btn btn-primary"></a>
        </div>
    </div>

    <div id="movePageDialog" class="modal hide" data-backdrop="static">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h3><fmt:message key="page.general.movethispage" bundle="${portalKey}"/></h3>
        </div>
        <div class="modal-body">
            <form id="movePageForm" class="form-horizontal">
                <fieldset>
                    <div class="control-group">
                        <div class="controls">
                            <select id="moveAfterPageId">
                                <c:if test="${pageUser.renderSequence != 1}">
                                    <option value="-1"><fmt:message key="page.general.movethispage.tofirst" bundle="${portalKey}"/></option>
                                </c:if>
                                <c:forEach var="userPage" items="${pages}">
                                    <c:if test="${userPage.id != page.id}">
                                        <option value="${userPage.id}">
                                            <fmt:message key="page.general.movethispage.after" bundle="${portalKey}">
                                                <fmt:param><c:out value="${userPage.name}"/></fmt:param>
                                            </fmt:message>
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" onclick="$('#movePageDialog').modal('hide');"><fmt:message key="_rave_client.common.cancel" bundle="${portalKey}"/></a>
            <a href="#" class="btn btn-primary" onclick="rave.layout.movePage();"><fmt:message key="page.general.movepage" bundle="${portalKey}"/></a>
        </div>
    </div>

    <fmt:message key="widget.menu.movetopage" var="moveWidgetToPageTitle" bundle="${portalKey}"/>
    <div id="moveWidgetModal" class="modal hide" data-backdrop="static">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h3><fmt:message key="widget.menu.movethiswidget" bundle="${portalKey}"/></h3>
        </div>
        <div class="modal-body">
            <form id="moveWidgetForm" class="form-horizontal">
                <fieldset>
                    <div class="control-group">
                        <div class="controls">
                            <select id="moveToPageId">
                                <c:forEach var="userPage" items="${pages}">
                                    <c:forEach var="members" items="${userPage.members}">
                                        <c:if test="${members.user.username == principleUsername and members.editor and userPage.id != page.id}">
                                            <option value="${userPage.id}">
                                                <c:out value="${userPage.name}"/>
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" onclick="$('#moveWidgetModal').modal('hide');"><fmt:message key="_rave_client.common.cancel" bundle="${portalKey}"/></a>
            <a href="#" class="btn btn-primary" onclick="rave.layout.moveWidgetToPage($('#moveWidgetModal').data('regionWidgetId'));"><fmt:message key="_rave_client.common.move" bundle="${portalKey}"/></a>
        </div>
    </div>

    <div id="sharePageDialog" class="modal hide" data-backdrop="static">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h3><fmt:message key="page.general.search.title" bundle="${portalKey}"/></h3>
        </div>
        <div class="modal-body">
            <div id="sharePageDialogContent" >
                <div id="shareContent">
                    <div id="searchControls"><input id="searchTerm" name="searchTerm" type="text"/>
                        <input id="shareSearchButton" value="<fmt:message key="page.store.search.button" bundle="${portalKey}"/>" type="submit"/>
                        <input id="clearSearchButton" value="<fmt:message key="admin.clearsearch" bundle="${portalKey}"/>" type="submit" class="hide"/>
                    </div>
                    <div id="shareSearchListHeader"></div>
                    <div id="shareSearchListPaging"></div>
                    <div id="shareSearchResults"></div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" onclick="$('#sharePageDialog').modal('hide');"><fmt:message key="_rave_client.common.cancel" bundle="${portalKey}"/></a>
        </div>
    </div>

    <div id="confirmSharePageDialog" class="modal hide" data-backdrop="static">
        <div class="modal-header">
            <a href="#" class="close" data-dismiss="modal">&times;</a>
            <h3><fmt:message key="sharing.dialog.confirm.title" bundle="${portalKey}"/></h3>
        </div>
        <div class="modal-body">
            <div id="confirmSharePageDialogLegend">
                <fmt:message key="sharing.dialog.confirm.message" bundle="${portalKey}">
                    <fmt:param value="${page.owner.username}"/>
                </fmt:message>
            </div>
        </div>

        <div class="modal-footer">
		
            <a href="#" class="btn btn-primary" onclick="rave.layout.searchHandler.acceptShare()"><fmt:message key="_rave_client.common.accept" bundle="${portalKey}"/></a>
            <a href="#" class="btn" onclick="rave.layout.searchHandler.declineShare();"><fmt:message key="_rave_client.common.decline" bundle="${portalKey}"/></a>
            <a href="#" class="btn" onclick="$('#confirmSharePageDialog').modal('hide');"><fmt:message key="_rave_client.common.cancel" bundle="${portalKey}"/></a>
        </div>
    </div>
			</div>
<portal:register-init-script location="${'AFTER_RAVE'}">
    <script>
        $(function() {
            rave.initPageEditorStatus(<c:out value="${pageUser.editor}"/>);
            rave.initProviders();
            rave.initWidgets();
            rave.initUI();
            rave.layout.init();
            rave.layout.searchHandler.setDefaults("<c:out value="${principleUsername}"/>","<sec:authentication property="principal.id" />","<c:out value="${page.id}"/>", "${pageUser.pageStatus}");
            rave.runOnPageInitializedHandlers();
        });
    </script>
<!-- Cross Domain Communication -->
	<script src="<spring:url value="/wro/g6.js" />" ></script>
    <script type="text/javascript">

(function () {

    var proxyurl = {
        "bar1": "<c:out value="${chatUrl}"/>/OpenFireClient/proxy.html"
    };

    var target = {
        "bar1": "frames['bar1']"
    };
//YAHOO.util.CrossFrame.send("http://10.36.76.150:8080/OpenFireClient/proxy.html", "frames['bar1']", "pop1:ghgh");
    YAHOO.util.Event.addListener("sendBtn", "click", function (evt) {
        // Prevent the form from being submitted...
        YAHOO.util.Event.stopEvent(evt);
        // Get the destination and message
        var dst = YAHOO.util.Dom.get("destination").value;
        var msg = YAHOO.util.Dom.get("message").value;
        YAHOO.util.CrossFrame.send(proxyurl[dst], target[dst], "pop1:ghgh");
		var dv5 = document.getElementById('testDiv');
		var buttonDiv = document.getElementById('sendBtn');
	if($(dv5).height()=="312")

        {
            $(dv5).css("height","30px");
			
			$(buttonDiv).removeClass("minimize").addClass("miximize");
        }
        else
        {
            $(dv5).css("height","312px");
			$(buttonDiv).removeClass("miximize").addClass("minimize");
        }

    });

    YAHOO.util.CrossFrame.onMessageEvent.subscribe(function (type, args, obj) {
        var message = args[0];
        var domain = args[1];
		//alert("message");
		var frame=document.getElementById('bar2');
		var div=document.getElementById('phoneDiv');
		if(message=='min'){
			//change();
			YAHOO.util.Dom.get("minimize").onclick();
		}else if(message=='close'){
			// alert("lddd2");
			document.getElementById('phoneDiv').style.display='none';
		}else if(message=='toggle'){
			if($(div).height()=="168")
				{
					$(div).css("height","30px");
					$(frame).height("30px");			
				}
				else
				{
					$(div).css("height","168px");
					$(frame).height("168px");
				}
		}else if(message=='open'){
			var div=document.getElementById('phoneDiv');
			document.getElementById('phoneDiv').style.display='block';

			$(div).css("height","168px");
			$(frame).height("168px");

		}else if(message=='toggleChat'){
			var divChat = document.getElementById('testDiv');
			var buttonDiv2 = document.getElementById('sendBtn');
			if($(divChat).height()=="312px")

				{
					$(divChat).css("height","30px");
					
					$(buttonDiv2).removeClass("minimize").addClass("miximize");
				}
				else
				{
					$(divChat).css("height","312px");
					$(buttonDiv2).removeClass("miximize").addClass("minimize");
				}
		}else if(message.indexOf("Phone")> (-1)){
			var value=message.split(":");
			//alert(value[1]);
			callPopUp(value[1],true);
		}
    });

})();

    </script>
<input type="hidden" id="minimize" onclick="minimize()" />
<script>
	function minimize(){
		var dv5 = document.getElementById('testDiv');
		
       
            $(dv5).css("height","312px");
			if($('#sendBtn').hasClass('miximize')){
				$('#sendBtn').removeClass("miximize").addClass("minimize");
			}else{}
	}
</script>
<script type="text/javascript">
	var int ="";
	
	function changeTitle(){
	  if (document.title == "Main - VisionInfoCon"){
		document.title = "New Message Recieved";
	  }else{
		document.title = "Main - VisionInfoCon";
	  }
	}

	function change(){
	  clearInterval(int);
	  int = setInterval(function(){changeTitle()},1000);
	}

	function stop(){
	  clearInterval(int);
	  document.title = "Main - VisionInfoCon";
	}
	$(document).mousemove(function(event) {
		$("#testDiv").mouseover(function(){
			stop();	
		});
	});
</script>
<!-- *** Ends Here *** -->
<!-- *** Phone Div Script *** -->
<script type="text/javascript">
window.onload = function() {
    var oFrame = document.getElementById("bar2");
    oFrame.contentWindow.document.getElementById("callId").onclick = function() {
		var phoneDiv = document.getElementById('phoneDiv');
		if($(phoneDiv).height()=="122"){
			$(phoneDiv).css("height","30px");
		}
		else{
			$(phoneDiv).css("height","122px");
		}
		var el1 = oFrame.contentWindow.document.getElementById("videoContainerId");
		if ( el1.style.display != 'none' ) {
			el.style.display = 'none';
		}
		else {
			el1.style.display = '';
		}
		var el2 = oFrame.contentWindow.document.getElementById("labelBoxId");
		if ( el2.style.display != 'none' ) {
			el2.style.display = 'none';
		}
		else {
			el2.style.display = '';
		} 
    };
	
	oFrame.contentWindow.document.getElementById("openId").onclick = function() {
		oFrame.contentWindow.document.getElementById("videoContainerId").style.display = '';
		oFrame.contentWindow.document.getElementById("labelBoxId").style.display = '';
		$("#phoneDiv").css("height","122px");
	}
};
</script>
<!-- *** Phone Div Script Ends Here*** -->
    <c:forEach var="members" items="${page.members}">
        <script>rave.layout.searchHandler.addExistingMember("${members.user.username}",${members.editor});</script>
    </c:forEach>
</portal:register-init-script>
