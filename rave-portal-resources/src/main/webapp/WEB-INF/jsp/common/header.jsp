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
<%-- Common header will go here... --%>

<%-- example on how to render static content from the cache using the tag library
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<portal:render-static-content contentKey="standardCompanyHeader" />
--%>

<script type="text/javascript">

 $(document).ready( function () {
 var c_value=document.location.href; 
 var b = c_value.split("/");
 var value="/";
 for (var i = 3; i < b.length; i++)
 {
value=value+b[i]+"/";
 }
 var v=value.length;
 var safeQueryString = escape(value.substring(0, v-1) );
 alert( safeQueryString ); 
  document.cookie="src_url" + "="+ safeQueryString;
  alert( safeQueryString );
 }); 
 </script>