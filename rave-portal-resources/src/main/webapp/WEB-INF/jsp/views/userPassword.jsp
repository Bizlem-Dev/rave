<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~      http://www.apache.org/licenses/LICENSE-2.0
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
<tiles:insertDefinition name="templates.base">

    <tiles:putAttribute name="pageTitleKey" value="page.changepassword.title"/>
    <tiles:importAttribute name="pageTitleKey" scope="request"/>

    <tiles:putAttribute name="body">
	<script>
	function submitForm(){
		if(document.getElementById("newPasswordId").value==document.getElementById("confPasswordId").value){
			document.getElementById("submitId").click();
		}else{
			document.getElementById("confDivId").style.display="block";
		}
	}
	
</script>
	<header>
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
		            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		                <span class="icon-bar"></span>
		            </a>
		            <span class="brand">VisionInfoCon</span>
		            <div class="nav-collapse">
		                 <ul class="nav pull-right">		                 
		                 	
		                        <li>
		                        	<a id="loginButton" type="submit" href="${pageContext.request.contextPath}">
		                        		&laquo; 
				                    	Back to Home
		                        	</a>
		                        </li>
		                    
		                 	
		                 </ul>
		             </div>
		        </div>
		    </div>
		</div>
	</header>
        <div class="container-fluid">
			<div class="row-fluid" style="padding:3px 1px 1px 7px;">
            <h1><fmt:message key="page.changepassword.title"/></h1>
				<form action="${pageContext.request.contextPath}/secure/setPassword.html" method="POST" id="formId" >
                <fieldset>
                    <p><fmt:message key="form.all.fields.required"/></p>
                    <p>
						<c:if test="${response=='error'}">
							<div class="alert-error" style="padding:4px;" align="center" >Current Password did not match !</div>
						</c:if>
						<c:if test="${response=='success'}">
							<div class="alert-success" style="padding:4px;" align="center" >Password Changed Successfully !</div>
						</c:if>
					</p>
						
					<p>
                        <label for="passwordField">Current Password</label>
                        <input type="password" name="currentPassword"  required="required" />
                    </p>
						
                    <p>
                        <label for="passwordField">New <fmt:message key="page.general.password"/></label>
                        <input type="password" name="newPassword" id="newPasswordId"  required="required" />
                    </p>

                    <p>
                        <label for="passwordConfirmField"><fmt:message key="page.general.confirmpassword"/></label>
                        <input type="password" name="confPassword" id="confPasswordId" style="float:left;" required="required" />
						
                    </p>
                    <div id="confDivId" style="padding: 4px;display:none;" class="error">&nbsp; Password did not match </div>
                </fieldset>
                <fieldset>
                    <fmt:message key="page.changepassword.button" var="submitButtonText"/>
					<input type="submit" id="submitId" value="Submit" style="display:none;" />
                    <input type="button" onclick="submitForm()" class="btn btn-primary" value="${submitButtonText}"/>
                </fieldset>
            </form>
        </div>
		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>