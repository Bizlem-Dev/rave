<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script language="JavaScript">
				function Validate()
				  {
					
					 var image =document.getElementById("image").value;
					 if(image!=''){
						  var checkimg = image.toLowerCase();
						  if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
							  alert("Please enter  Image  File Extensions .jpg,.png,.jpeg");
							  document.getElementById("image").focus();
							  return false;
						    }
						 }
					 return true;
				 }			
</script>
</head>
<body>
<form:form modelAttribute="uploadItem" method="POST" action="profile/uploadPost.html"
	enctype="multipart/form-data" onSubmit="return Validate();"   >

		<input type="hidden" value="${profile.referringPageId}" name="referringPageId"/>
		<input type="hidden" value="${profile.userId}" name="userId"/>
		Upload Picture : <form:input type="file" id="image" path="fileData" />
		<input type="submit" value="upload" />
		
 	
	</form:form>
</body>
</html>