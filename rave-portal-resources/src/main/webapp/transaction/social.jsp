<html>
	<head>
		<title>Authentication</title>
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
		</script>
	</head>
	<body>
		<table cellpadding="10" cellspacing="10" align="center">
			
			
			<tr>
				<td><a href="../socialauth.html?id=facebook"><img src="images/facebook_icon.png" alt="Facebook" title="Facebook" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=twitter"><img src="images/twitter_icon.png" alt="Twitter" title="Twitter" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=google"><img src="images/gmail-icon.jpg" alt="Gmail" title="Gmail" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=yahoo"><img src="images/yahoomail_icon.jpg" alt="YahooMail" title="YahooMail" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=hotmail"><img src="images/hotmail.jpeg" alt="HotMail" title="HotMail" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=linkedin"><img src="images/linkedin.gif" alt="Linked In" title="Linked In" border="0"></img></a></td>
				<!-- <td><a href="../socialauth.html?id=foursquare"><img src="css/images/foursquare.jpeg" alt="FourSquare" title="FourSquare" border="0"></img></a></td>
				<td><a href="../socialauth.html?id=myspace"><img src="css/images/myspace.jpeg" alt="MySpace" title="MySpace" border="0"></img></a></td> -->
			</tr>
			<!-- <tr>
				<td colspan="8" align="center">
					<form action="../socialauth.html" onsubmit="return validate(this);">
						or enter OpenID url: <input type="text" value="" name="id"/>
						<input type="submit" value="Submit"/> 
					</form>
				</td>
			</tr> -->
			
		</table>
		
	</body>
</html>