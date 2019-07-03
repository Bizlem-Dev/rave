
<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~   http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  --%>
<%@ page language="java" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<fmt:setBundle basename="messages"/>
<%-- Note: This page has the body definition embedded so we can reference it directly from the security config file. --%>
<tiles:insertDefinition name="templates.base">
    <%-- Override the default pageTitleKey and then export it to the request scope for use later on this page --%>
    <tiles:putAttribute name="pageTitleKey" value="page.login.title"/>
    <tiles:importAttribute name="pageTitleKey" scope="request"/>

     <tiles:putAttribute name="body">
    
  <%-- <rave:login_navbar hideButton="loginButton" /> --%>
<c:if test="${not empty service}">
fsdgfdfg

</c:if>
<c:choose>	
<c:when test="${not empty paramValues}">
	<c:forEach var="par" items="${paramValues}">
	  <c:choose>  
		<c:when test="${par.key eq 'service'}">
			<script>
						var exdays=365;
						var exdate=new Date();
						exdate.setDate(exdate.getDate() + exdays);
						var c_value=escape('<c:out value="${par.value[0]}" />') + ((exdays==null) ? "" : "; host=portal;path=/portal/; expires="+exdate.toUTCString());
						document.cookie="visioninfo=" + c_value;
			</script>
		</c:when>
		<c:when test="${par.key eq 'authfail'}">
		
		</c:when>
		<c:when test="${par.key eq 'authexp'}">
		</c:when>
		<c:otherwise>
			<script>
						var exdays=365;
						var exdate=new Date();
						exdate.setDate(exdate.getDate() + exdays);
						var c_value=escape('/') + ((exdays==null) ? "" : "; path=/portal/; expires="+exdate.toUTCString());
						document.cookie="visioninfo=" + c_value;
			</script>
		</c:otherwise>
	  </c:choose>	
	</c:forEach>
</c:when>
<c:otherwise>
			<script>
						var exdays=365;
						var exdate=new Date();
						exdate.setDate(exdate.getDate() + exdays);
						var c_value=escape('/') + ((exdays==null) ? "" : "; path=/portal/; expires="+exdate.toUTCString());
						document.cookie="visioninfo=" + c_value;
			</script>
</c:otherwise>	
</c:choose>	
<div class="section-internal">
<div><label>Sign in with your</label></div>
<form class="form-horizontal" id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
<c:if test="${param['authfail'] eq 'form'}">
                                    <div class="alert alert-error"><fmt:message key="page.login.usernamepassword.fail"/></div>
                                </c:if>
<c:if test="${param['authexp'] eq 'form'}">
                                    <div class="alert alert-error">You have logged In from different location.</div>
                                </c:if>
<c:if test="${not empty message}">
                <div class="alert alert-info">${message}</div>
            </c:if>
<div>
  <label class="note"><fmt:message key="page.general.username"/></label>
  <input class="input-large" id="usernameField" type="text" name="j_username" autofocus="autofocus" style="width:350px;"/>
</div>

<div style="margin-bottom:0px;">
  <label class="note"><fmt:message key="page.general.password"/></label>
  <input class="input-large" id="passwordField" type="password" name="j_password" style="width:350px;"/>
</div>
<div>
<input type='checkbox' name='_spring_security_remember_me' id="remember_me" value="true"/><label class="note" style="display:inline !important; margin-left:5px;"><fmt:message key="page.login.rememberme"/></label> | <a href="${pageContext.request.contextPath}/secure/forgotPassword.html" style="float: none; color: rgb(108, 108, 108);font-size: 11px;">Forgotten your password?</a>
</div>
<div><fmt:message key="page.login.usernamepassword.login" var="loginButtonText"/><button class="btn btn-primary" type="submit" value="${loginButtonText}">${loginButtonText}</button></div>
</form>

<!--<div>
<span class="bullet">or</span>
<label>Sign in using OpenId</label></div>
<form class="form-horizontal" id="openIdForm" name='oidf' action='j_spring_openid_security_check' method='POST'>
	                       
	                            
                                <c:if test="${param['authfail'] eq 'openid'}">
                                    <div class="alert alert-error"><fmt:message key="page.login.openid.fail"/></div>
                                </c:if>
	                            <div>
	                                <label class="note"><fmt:message key="page.login.openid.identifier"/></label>
	                                
	                                    <input class="input-large" type="text" id="openid_identifier" name='openid_identifier' autofocus="autofocus" style="width:350px;"/>
	                                
	                            </div>
	                            <div>
									<input type='checkbox' name='_spring_security_remember_me' id="remember_me_openid" value="true"/>
	                                <label class="note" style="display:inline !important; margin-left:5px;">
	                                    <fmt:message key="page.login.rememberme"/>
	                                </label>
	                            </div>
								<div><fmt:message key="page.login.openid.button" var="openidButtonText"/>
	                                <button class="btn btn-primary" type="submit" value="${openidButtonText}">${openidButtonText}</button>
	                            </div>
	                    </form>
-->
<div>
<span class="bullet">or</span>
<label>Sign in using</label>
<label class="note">Click on one of the icon to login using your respective account</label><ul class="icon-social-media">
<li><a href="socialauth.html?id=facebook"><img src="css/images/facebook_icon.png" alt="Facebook" title="Facebook" border="0"></img></a></li>
<li><a href="socialauth.html?id=yahoo"><img src="css/images/yahoomail_icon.jpg" alt="YahooMail" title="YahooMail" border="0"></img></a></li>
<li><a href="socialauth.html?id=hotmail"><img src="css/images/hotmail.jpeg" alt="HotMail" title="HotMail" border="0"></img></a></li>
<li><a href="socialauth.html?id=linkedin"><img src="css/images/linkedin.gif" alt="Linked In" title="Linked In" border="0"></img></a></li>
<li><a href="socialauth.html?id=twitter"><img src="css/images/twitter_icon.png" alt="Twitter" title="Twitter" border="0"></img></a></li>
<li><a href="socialauth.html?id=googleplus"><img src="css/images/gmail-icon.jpg" alt="Google" title="Google" border="0"></img></a></li>
<li><a rel="facebox" href="javascript:void(0)" onclick="openId()" ><img src="css/images/openid.png" alt="Open Id" title="Open Id" border="0"></img></a></li>
</ul>
</div>
<form action="socialauth.html" onsubmit="return validate(this);">
<div id="openId" style="display:none;">
						Enter OpenID url: <input type="text" value="" name="id"/>
						<input type="submit" value="Submit"/> 
</div>						
</form>
<hr />
<div>
  <label>Don't have Existing Account ?</label>
<label class="note">Click the button to create a new account if you are not having any account to login from the above given choices</label>
 <br />
 <a href="/portal/app/newaccount.jsp"><img src="/portal/static/images/btn_create_new.gif" border="0" /></a>
</div>
</div>
<script>
			function validate(obj){
				var val = obj.id.value;
				if(trimString(val).length <= 0){
					alert("Please enter OpenID URL");
					return false;
				}else{
					return true;
				}
			}
			function trimString(tempStr)
			{
			   return tempStr.replace(/^\s*|\s*$/g,"");
			}
			function openId(obj){
				document.getElementById("openId").style.display="block";
			}
			
			
</script>
<%--
<script type="text/javascript">

    var cbVideoDisable;
    var cbAVPFDisable;
    var txtWebsocketServerUrl;
    var txtSIPOutboundProxyUrl;
    var txtInfo;

    window.onload = function () {
		//alert(document.getElementById("cbVideoDisable").value);
        cbVideoDisable = document.getElementById("cbVideoDisable");
        cbRTCWebBreaker = document.getElementById("cbRTCWebBreaker");
        txtWebsocketServerUrl = document.getElementById("txtWebsocketServerUrl");
        txtSIPOutboundProxyUrl = document.getElementById("txtSIPOutboundProxyUrl");
        txtInfo = document.getElementById("txtInfo");

        txtWebsocketServerUrl.disabled = !window.WebSocket;
        document.getElementById("btnSave").disabled = !window.localStorage;
        document.getElementById("btnRevert").disabled = !window.localStorage;

        if(window.localStorage){
            //settingsRevert(true);
			settingsSave();
        }
    }

    function settingsSave() {
        window.localStorage.setItem('org.doubango.expert.disable_video', cbVideoDisable.checked ? "true" : "false");
        window.localStorage.setItem('org.doubango.expert.enable_rtcweb_breaker', cbRTCWebBreaker.checked ? "true" : "false");
        if (!txtWebsocketServerUrl.disabled) {
            window.localStorage.setItem('org.doubango.expert.websocket_server_url', txtWebsocketServerUrl.value);
        }
        window.localStorage.setItem('org.doubango.expert.sip_outboundproxy_url', txtSIPOutboundProxyUrl.value);
        window.localStorage.setItem('org.doubango.expert.ice_servers', txtIceServers.value);

        txtInfo.innerHTML = '<i>Saved</i>';
    }

    function settingsRevert(bNotUserAction) {
        cbVideoDisable.checked = (window.localStorage.getItem('org.doubango.expert.disable_video') == "true");
        cbRTCWebBreaker.checked = (window.localStorage.getItem('org.doubango.expert.enable_rtcweb_breaker') == "true");
        txtWebsocketServerUrl.value = (window.localStorage.getItem('org.doubango.expert.websocket_server_url') || "");
        txtSIPOutboundProxyUrl.value = (window.localStorage.getItem('org.doubango.expert.sip_outboundproxy_url') || "");
        txtIceServers.value = (window.localStorage.getItem('org.doubango.expert.ice_servers') || "");

        if (!bNotUserAction) {
            txtInfo.innerHTML = '<i>Reverted</i>';
        }
    }
</script>


    <div class="container" style="display:none;">
        <div class="span7 well">
            <label align="center" id="txtInfo"> </label>
            <h2> Expert settings</h2>
            <br />
            <table style='width: 100%'>
                <tr>
                    <td>
                        <label style="height: 100%">Disable Video:</label>
                    </td>
                    <td>
                        <input type='checkbox' id='cbVideoDisable' checked='checked' />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="height: 100%">Enable RTCWeb Breaker<sup><a href="#aRTCWebBreaker">[1]</a></sup>:</label>
                    </td>
                    <td>
                        <input type='checkbox' id='cbRTCWebBreaker' />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="height: 100%">WebSocket Server URL<sup><a href="#aWebSocketServerURL">[2]</a></sup>:</label>
                    </td>
                    <td>
                        <input type="text" style="width: 100%; height: 100%" id="txtWebsocketServerUrl" value="ws://10.36.76.184:10060" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="height: 100%">SIP outbound Proxy URL<sup><a href="#aSIPOutboundProxyURL">[3]</a></sup>:</label>
                    </td>
                    <td>
                        <input type="text" style="width: 100%; height: 100%" id="txtSIPOutboundProxyUrl" value="udp://10.36.76.98:5060" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label style="height: 100%">ICE Servers<sup><a href="#aIceServers">[4]</a></sup>:</label>
                    </td>
                    <td>
                        <input type="text" style="width: 100%; height: 100%" id="txtIceServers" value="132.177.123.6" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="right">
                        <input type="button" class="btn-success" id="btnSave" value="Save" onclick='settingsSave();' />
                        &nbsp;
                        <input type="button" class="btn-danger" id="btnRevert" value="Revert" onclick='settingsRevert();' />
                    </td>
               </tr>
            </table>
        </div>
    </div>
	--%>
<!-- Start Open Web Analytics Tracker 
<script type="text/javascript">
//<![CDATA[
var owa_baseUrl = 'http://indiawebmail.in/owa/';
var owa_cmds = owa_cmds || [];
owa_cmds.push(['setSiteId', '888ef7236375ddba128d63f19fdfac8c']);
owa_cmds.push(['trackPageView']);
owa_cmds.push(['trackClicks']);
owa_cmds.push(['trackDomStream']);

(function() {
	var _owa = document.createElement('script'); _owa.type = 'text/javascript'; _owa.async = true;
	owa_baseUrl = ('https:' == document.location.protocol ? window.owa_baseSecUrl || owa_baseUrl.replace(/http:/, 'https:') : owa_baseUrl );
	_owa.src = owa_baseUrl + 'modules/base/js/owa.tracker-combined-min.js';
	var _owa_s = document.getElementsByTagName('script')[0]; _owa_s.parentNode.insertBefore(_owa, _owa_s);
}());
//]]>
</script>
 End Open Web Analytics Code -->

<!-- Piwik 
<script type="text/javascript">
var pkBaseURL = (("https:" == document.location.protocol) ? "https://14.140.116.243/piwik/" : "http://14.140.116.243/piwik/");
document.write(unescape("%3Cscript src='" + pkBaseURL + "piwik.js' type='text/javascript'%3E%3C/script%3E"));
</script><script type="text/javascript">
try {
var piwikTracker = Piwik.getTracker(pkBaseURL + "piwik.php", 3);
piwikTracker.trackPageView();
piwikTracker.enableLinkTracking();
} catch( err ) {}
</script><noscript><p><img src="http://14.140.116.243/piwik/piwik.php?idsite=3" style="border:0" alt="" /></p></noscript>
 End Piwik Tracking Code -->


<!-- Start Open Web Analytics Tracker 
<script type="text/javascript">
//<![CDATA[
var owa_baseUrl = 'http://14.140.116.243/owa/';
var owa_cmds = owa_cmds || [];
owa_cmds.push(['setSiteId', '888ef7236375ddba128d63f19fdfac8c']);
owa_cmds.push(['trackPageView']);
owa_cmds.push(['trackClicks']);
owa_cmds.push(['trackDomStream']);

(function() {
	var _owa = document.createElement('script'); _owa.type = 'text/javascript'; _owa.async = true;
	owa_baseUrl = ('https:' == document.location.protocol ? window.owa_baseSecUrl || owa_baseUrl.replace(/http:/, 'https:') : owa_baseUrl );
	_owa.src = owa_baseUrl + 'modules/base/js/owa.tracker-combined-min.js';
	var _owa_s = document.getElementsByTagName('script')[0]; _owa_s.parentNode.insertBefore(_owa, _owa_s);
}());
//]]>
</script>
 End Open Web Analytics Code -->

    </tiles:putAttribute>
</tiles:insertDefinition>
