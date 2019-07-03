<html>
<meta charset="utf-8" />
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<fmt:setBundle basename="jdbc" var="jdbc"/>
<fmt:message key="cas.serverName" var="casServer"  bundle="${jdbc}"/>

<sec:authentication property="principal.extensionNumber" var="principleExtension" scope="request"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


	Loading , Please Wait . . . 
	
	<!--<div id="iframeId" style="display:none;">
            <iframe src="http://172.17.1.26/Jphone/jphonelite-javascript.php?userid=<c:out value="${principleExtension}" />" ></iframe>
        </div> -->
</div>

<c:set var="serviceCheckVar" value="${serviceCheck}"/>
<%session.setAttribute("serviceCheck",(String)pageContext.getAttribute("serviceCheckVar"));%>
<script>
	
    setCookie('CASTGC',"<c:out value="${key}" />",365);
	var redirect=getCookie('visioninfo');
	if(redirect!=null){
		window.location.href=redirect;
	}else{
		window.location.href="/";
	}

   function setCookie(c_name,value,exdays)
   {
        var exdate=new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var c_value=escape(value) + ((exdays==null) ? "" : "; path=/<c:out value="${casServer}" />/; expires="+exdate.toUTCString());
        document.cookie=c_name + "=" + c_value;
    }
    function getCookie(c_name)
    {
        var i,x,y,ARRcookies=document.cookie.split(";");
        for (i=0;i<ARRcookies.length;i++)
          {
          x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
          y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
          x=x.replace(/^\s+|\s+$/g,"");
          if (x==c_name)
            {
            return unescape(y);
            }
          }
    }

</script>


</html>