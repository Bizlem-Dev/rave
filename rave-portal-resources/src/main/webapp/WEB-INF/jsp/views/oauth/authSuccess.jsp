<html>
<head>
	<title>SocialAuth Demo</title>
	<style>
		.sectiontableheader {background-color:#C8D7E3;color:#293D6B;font-size:8pt;font-weight:bold;padding:2px;}
		.sectiontableentry2 {background:none repeat scroll 0 0 #F7F7F7;padding:2px;}
		.sectiontableentry1 {background:none repeat scroll 0 0 #FFFFF0;padding:2px;}
	</style>
	<script>
    	function updateStatus(){
        	var btn = document.getElementById('btnUpdateStatus');
        	btn.disabled=true;
			var msg = prompt("Enter your status here:");
			if(msg == null || msg.length == 0){
				btn.disabled=false;
		    	return false;
	        }
			msg = "statusMessage="+msg;
			var req = new XMLHttpRequest();
			req.open("POST", "<%=request.getContextPath()%>/updateStatus.do");
			req.setRequestHeader("Accept", "text/xml");
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.setRequestHeader("Content-length", msg.length);
			req.setRequestHeader("Connection", "close");
			req.onreadystatechange = function () {
				if (req.readyState == 4) {
					if(req.responseText.length > 0) {
							alert(req.responseText);
							btn.disabled=false;
					}
				}
			};
			req.send(msg);
    	}
	</script>
</head>
<body>
<%@page import="org.brickred.socialauth.Profile,java.util.*;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<h2 align="center">Authentication has been successful.</h2>
<br/>
<div align="center">Please Wait Loading ..........</div>
<br />

<div class="container-fluid" style="display:none;" >
            <h1>Login</h1>
            <div class="row-fluid" id="loginOptions">
            	<div id="loginBlock" class="clearfix well">
	                <div class="span6">
                        <form class="form-horizontal" id="loginForm" name="loginSocialForm" action="j_spring_security_check" method="post">
	                        <fieldset>
	                            <legend>Username and Password</legend>
                                <div class="control-group">
	                                <label class="control-label" for="usernameField">Username:</label>
	                                <div class="controls">
	                                    <input class="input-large" id="usernameField" type="text" name="j_username" autofocus="autofocus"  value="<c:out value="${auth.username}"/>"/>
	                                </div>
	                            </div>
	                            <div class="control-group">
	                                <label class="control-label" for="passwordField">Password:</label>
	                                <div class="controls">
	                                    <input class="input-large" id="passwordField" type="password" name="j_password" value="<c:out value="${auth.plaintext_password}"/>"/>
	                                </div>
	                            </div>
	                            <div class="control-group">
	                                <label class="control-label" for="remember_me">
	                                    Remember me</label>
	                                <div class="controls">
	                                    <input type='checkbox' name='_spring_security_remember_me' id="remember_me" value="true"/>
	                                </div>
	                            </div>
	                        </fieldset>
	                        <fieldset>
	                            <div class="controls">
	                                <button class="btn btn-primary" type="submit" value="Log in">Log in</button>
				
	                            </div>
	                        </fieldset>
	                    </form>
	                </div>
	                
            	</div>
            </div>
        </div>



<script src="//cdnjs.cloudflare.com/ajax/libs/json2/20110223/json2.js"></script>

<script src="//ajax.aspnetcdn.com/ajax/jquery/jquery-1.7.2.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.8.17/jquery-ui.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.8.1/jquery.validate.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.3/bootstrap.min.js"></script>

<!--[if lt IE 9]><script src="//css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->


<script src="http://10.36.76.123:8080/gadgets/js/container:pubsub-2:open-views.js?c=1&amp;container=default&amp;debug=1"></script>


    
    
        <script src="/portal/static/script/rave.js"></script>
        <script src="/portal/static/script/rave_api.js"></script>
        <script src="/portal/static/script/rave_opensocial.js"></script>
        <script src="/portal/static/script/rave_wookie.js"></script>
        <script src="/portal/static/script/rave_layout.js"></script>
        <script src="/portal/static/script/rave_forms.js"></script>
        <script src="/portal/static/script/rave_person_profile.js"></script>
        <script src="/portal/static/script/rave_store.js"></script>
        <script src="/portal/static/script/rave_admin.js"></script>
    

<script src="/portal/app/messagebundle/rave_client_messages.js"></script>


<script>
    rave.setContext("/portal/app/");
	loginSocialForm.submit();
</script>






</body>
</html>