<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="r" %>

<c:import url="/includes/header.jsp"/>
<script>
	function displayBlock(){
		document.getElementById("displayType").style.display="none";
		document.getElementById("hiddentype").style.display="block";
		var radio_check_val = "";
        for (i = 0; i < document.getElementsByName('type').length; i++) {
            if (document.getElementsByName('type')[i].checked) {
                radio_check_val = document.getElementsByName('type')[i].value;
                <c:set var="recommendType" value="%s" /> + radio_check_val;
                 if(radio_check_val=='colleague'){
                	
                	document.getElementById("colleague").style.display="block";
                	document.getElementById("partner").style.display="none";
                }
                else if(radio_check_val=="partner"){
                	
                	document.getElementById("colleague").style.display="none";
                	document.getElementById("partner").style.display="block";
                	
                } 
                
            }

        }
        if (radio_check_val === "")
        {
            alert("Please select radio button");
        }


	}

	function cancel(){
		document.getElementById("displayType").style.display="block";
		document.getElementById("hiddentype").style.display="none";
		
	}
	
	function asd(v){
		var splitVar=v.split("%");	
		document.getElementById("recommendByTitleId").value=splitVar[0];
		document.getElementById("keyId").value=splitVar[1];
		if(v=="")
			{
			document.getElementById("recommendByTitleId").value="";
			document.getElementById("keyId").value="";
			}
	}

</script>
<c:import url="/includes/menu.jsp"/>
<form:form  method="POST" modelAttribute="pendingRecommend" action="savePendingRequest.html">
<input type="hidden" value="${pendingRecommend.recommendId }" name="recommendId"/>
<input type="hidden" value="${pendingRecommend.fk_key_for_exp }" name="fk_key_for_exp"/>
<input type="hidden" value="${pendingRecommend.recommendFor }"  name="recommendFor" />
<input type="hidden" value="${pendingRecommend.recommendBy }" name="recommendBy" />
<input type="hidden" value="ACCEPT" name="status" />
<input type="hidden" value="${pendingRecommend.recommendForTitle }" name="recommendForTitle" />
<div id="displayType" style="display:block;" >
<input type="radio" id="recommendType1" name="type" value="colleague" /> Colleague: You have worked with Pranav at the same company <br />
<input type="radio" id="recommendType2" name="type" value="partner"    /> Business Partner: You have worked with Pranav, but not as a client or colleague<br />
<input type="button" value="Continue >>" onclick="displayBlock()"  />
</div>


<div id="hiddentype" style="display: none;">
Basis of recommendation:<br />
<div id="partner" >
 <form:select path="recommendType"  >
<option value="" selected>Choose...</option>
<option value="WDC">You worked with Pranav but were at different companies</option>
<option value="ECR">Pranav was a client of yours</option>
</form:select>
</div>

<div id="colleague">
<form:select path="recommendType">
<option value="" selected>Choose...</option>
<option value="RME">You managed Pranav directly</option>
<option value="RRE">You reported directly to Pranav</option>
<option value="RSE">You were senior to Pranav, but did not manage directly</option>
<option value="ESR">Pranav was senior to you, but you did not report directly</option>
<option value="WSG">You worked with Pranav in the same group</option>
<option value="WDG">You worked with Pranav in different groups</option>
</form:select> 
</div><br />
Your title at the time:<br />
<select  onchange="asd(this.value)" >
<option value="" selected>Choose...</option>
<c:forEach  items="${listExperience}" var="listExperience" >
<option value="<c:out value='${listExperience.jobTitle }'/> at <c:out value='${listExperience.companyName }'/>%<c:out value='${listExperience.pkExperienceId }'/>"   ><c:out value='${listExperience.jobTitle }'/> at <c:out value='${listExperience.companyName }'/></option>
</c:forEach>
</select><br />
<input type="hidden" id="recommendByTitleId" name="recommendByTitle"  />
<input type="hidden" id="keyId" name="fk_key_by_exp"  />
Write a brief recommendation for Pranav. Recommendations you write will appear on your profile.<br />
<form:textarea path="desc"/>
<input type="submit" value="Send" /><input type="button" value="Cancel" onclick="cancel()" />
</div>

</form:form>
<c:import url="/includes/footer.jsp"/>