<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person Profile Jindal Infosolutions Limited</title>
<link href="http://fonts.googleapis.com/css?family=Dosis" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/facebox.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
      $('a[rel*=facebox]').facebox({
        loadingImage : '../src/loading.gif',
        closeImage   : '../src/closelabel.png'
      })
    })
  </script>
<script type="text/javascript">
	$(document).ready(function(height){
		var winHeight = document.documentElement.clientHeight;
		var m=winHeight-'127';
		$(".container-fluid").css("min-height",m);
		//alert(m);
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.23.custom.min.js"></script>
