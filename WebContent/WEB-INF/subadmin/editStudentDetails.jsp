<%-- <%@ include file="../common/header.jsp"%> --%>
<%@page import="com.school.common.generic.StaticValue"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <c:set var="student" value="${AdmissionDetails.getStudentDetailses() }" /> --%>
<c:forEach var="studentItem" items="${AdmissionDetails.getStudentDetailses() }" begin="0" end="0">
<c:set var="student" value="${studentItem }" />
<c:set var="userDetails" value="${student.parentDetails.userDetails }" />
</c:forEach>

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			<c:if test="${Operation eq 'View'}">${Operation }</c:if> <c:if test="${Operation ne 'View'}">Edit</c:if> Student 
			<small>(* fields are mandatory)</small>   
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<!-- 				<h3 class="box-title">Student Details</h3> -->
			</div>

			<form action="updateStudentDetails" method="post" enctype="multipart/form-data" class="addStudent">
				<input type="hidden" name="parentDetailsId" value="${student.getParentDetails().getParentDetailsId() }">
				<div class="box-body">
					<div class="col-md-12">
					<div class="nav-tabs-custom" style="box-shadow: none;">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#student">Student Details</a></li>
							<li><a data-toggle="tab" href="#additional">Additional Details</a></li>
							<li><a data-toggle="tab" href="#parent">Parent Details</a></li>
						</ul>
						<div class="tab-content">
							<div id="student" class="tab-pane fade in active">
								<br>
								<div class="row form-group">

									<div class="col-md-12">
										<div class="col-md-3">
											<fieldset class="form-group">
												<div style="height:170px; width:160px; margin-left:40px; margin-bottom:10px; background-image: url('${pageContext.request.contextPath}/static/img/profile.png'); background-size: 160px 170px;">
													<c:if test="${not empty student.getImage()}">
														<img id="output" width="160px" height="170px" src="${pageContext.request.contextPath}/upload/student_images/${student.getImage()}"/>
													</c:if>
													<c:if test="${empty student.getImage()}">
														<img id="output" width="160px" height="170px"/>
													</c:if>
												</div>
												<label><font class="fontred"></font></label> <span
													class="btn btn-primary btn-file btn-xs" style="margin-left: 37px; width: 160px;">
													<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
													<input type="file" name="psimage" onchange="loadFile(event)" /></span>
													<input type="hidden" name="Image" value="${student.getImage()}">


											</fieldset>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Class <span class="red">*</span></label> <input type="text"
														class="form-control intelliClass-0-className stdClassHints"
														id="sclass" name="classInformation.className" value="${AdmissionDetails.getClassInformation().getClassName()}"/>
												</fieldset>
											</div>
											<div class="col-md-2">
												<label>Class ID</label> <input type="text"
													class="form-control intelliClass-0-classCode"
													id="classCode" name="classInformation.classCode"
													readonly="readonly" value="${AdmissionDetails.getClassInformation().getClassCode()}" />
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Section <span class="red">*</span></label> <input type="text"
														class="form-control intelliClass-0-sectionName stdSectionHints"
														id="getRollForNewAdmission" name="sectionInformation.sectionName" value="${AdmissionDetails.getSectionInformation().getSectionName()}" />
												</fieldset>
											</div>
											<div class="col-md-2">
												<label>Section ID</label> <input type="text"
													class="form-control intelliClass-0-sectionCode"
													id="sectionCode" name="sectionInformation.sectionCode"
													readonly="readonly" value="${AdmissionDetails.getSectionInformation().getSectionCode()}" />
											</div>
											<div class="col-md-3 form-group">
												<label>Status <span class="red">*</span></label> 
												<select	class="form-control" name="status">
													<%-- <option value="">-- <st:message code="ui.text.Select" /> --</option> --%>
													<option value="Y" <c:if test="${AdmissionDetails.getStatus() eq 'Y' }">selected</c:if> ><st:message code="ui.text.Active" /></option>
													<option value="N" <c:if test="${AdmissionDetails.getStatus() eq 'N' }">selected</c:if> ><st:message code="ui.text.Inactive" /></option>
												</select>
											</div>
											<div class="col-md-3 form-group">
												<label>Session <span class="red">*</span></label>
												<input type="text" class="form-control" name="schoolSession.sessionCode" value="${sessionScope.SessionManager.getSchoolProfileUtils().getCurrentSession() }" readonly="readonly">
											</div>
											<div class="col-md-3 form-group">
												<label>Admission Number <span class="red">*</span></label> <input type="text"
													class="form-control" id="admissionNo" readonly="readonly"
													name="admissionNo" value="${AdmissionDetails.getAdmissionNumber().getAdmissionNo()}" />
											</div>
											<div class="col-md-3 form-group">
												<label>Roll No <span class="red">*</span></label> <input type="text"
													class="form-control" id="rollNo" name="rollNo"
													readonly="readonly" value="${AdmissionDetails.getRollNo()}" />
											</div>
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>First Name <span class="red">*</span></label>
												<input type="text" class="form-control" id="studentfname"
													name="firstName" value="${student.getFirstName() }" />
											</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Middle Name</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="studentmidname" name="middleName" value="${student.getMiddleName() }" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Last Name <span class="red">*</span></label> <input
														type="text" class="form-control" id="studentlname"
														name="lastName" value="${student.getLastName() }" />
												</fieldset>
											</div>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									
									<div class="col-md-4 form-group">
										<label>Gender <span class="red">*</span></label>
										<select class="form-control" name="gender">
											<option value="">-- Select --</option>
											<option value="M"
												<c:if test="${student.getGender() eq 'M' }"> selected="selected"</c:if>>Male</option>
											<option value="F"
												<c:if test="${student.getGender() eq 'F' }"> selected="selected"</c:if>>Female</option>
										</select>
									</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Date of Birth <span class="red">*</span></label> 
												<div class="input-group">
													<input type='text' class="date-pickerfull form-control pull-left" id='dob' readonly="readonly" placeholder="dd-mm-yyyy" name="dateOfBirth" value="${student.getDateOfBirth() }"  />
													<div class="input-group-addon">
							                    	    <i class="fa fa-calendar"></i>
							                      	</div>
							                    </div>
											</fieldset>
										</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Age <span class="red">*</span></label> <input type="text"
													class="form-control" id="age" name="age" readonly="readonly" value="${student.getAge() }"  />
											</fieldset>
										</div>
								</div>




								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
												<!-- <label>Year of Admission <span class="red">*</span></label> <input type="text"
													class="form-control" name="yearOfAdmission"
													id="yearOfAdmission" value="" /> -->
													<label>Category <span class="red">*</span></label> 
													<select name="category" class="form-control">
														<option value="">-- Select --</option>
														<option value="GEN" <c:if test="${student.getCategory() eq 'GEN' }"> selected="selected"</c:if>>General</option>
														<option value="OBC" <c:if test="${student.getCategory() eq 'OBC' }"> selected="selected"</c:if>>OBC</option>
														<option value="SC" <c:if test="${student.getCategory() eq 'SC' }"> selected="selected"</c:if>>SC</option>
														<option value="ST" <c:if test="${student.getCategory() eq 'ST' }"> selected="selected"</c:if>>ST</option>
													</select>
											</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mobile</label> <input type="text"
												class="form-control" id="mobile" name="mobile" value="${student.getStudentMobile()}" />
										</fieldset>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Email</label> <input type="text"
												class="form-control" id="email" name="email" value="${student.getEmail()}"  /> <small
												class="primary muted">We'll never share your email
												with anyone else.</small>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Previous School Name(If Any)</label> <input
												type="text" class="form-control" id="previousSchoolName"
												name="previousSchoolName" value="${student.getPreviousSchoolName() }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-10">
										<div class="form-group">
											<label>Address of Correspondence</label>
											<textarea class="form-control" rows="5" id="address"
												name="address" style="resize: none">${student.getAddress() }</textarea>
										</div>
									</div>
								</div>



							</div>
							
							
							<div id="additional" class="tab-pane fade">
								<div class="row form-group"><font style="color: green;">Some change will display after selection class and section.</font></div>
								<div class="col-md-12">
									<div class="element-group">
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Transport</label> 
												<select name="selectedAdditionalFee" class="form-control transportDrop">
													<option value="">-- <st:message code="ui.text.Select" /> --</option>
	<c:forEach var="trans" items="${Transports }">
													<option value="${trans.transportId }~${trans.transportFee }~${trans.type }">${trans.route } - ${trans.pickUpStop }</option>
	</c:forEach>												
												</select>
											</fieldset>
										</div>
										
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Amount</label> 
												<input type="text" name="amount" class="form-control noSelectedFeeField" readonly="readonly" />
											</fieldset>
										</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Type</label> 
												<input type="text" name="typeName" class="form-control noSelectedFeeField" readonly="readonly" />
												<input type="hidden" name="type" />
												<input type="hidden" name="transportId" />
												<input type="hidden" name="feeCategoryCode" value="<%=StaticValue.FEE_CATEGORY_TRANSPORT %>" />
											</fieldset>
										</div>
									</div>
								</div>
								<div class="col-md-12 additionalFeeContainer">
								</div>
								
							</div>
							
							
							
							<div id="parent" class="tab-pane fade">
								<br>
								<div class="col-md-12">
									<div class="col-md-4 form-group">
<c:if test="${not empty userDetails }">								
										<input type="checkbox" name="provideUserCredential" value="Y" checked="checked" disabled="disabled" />&nbsp;&nbsp; Provide User Login Credential
</c:if>	
<c:if test="${empty userDetails }">								
										<input type="checkbox" name="provideUserCredential" value="Y" />&nbsp;&nbsp; Provide User Login Credential
</c:if>											
									</div>
								</div>
								<div class="col-md-12 credentialDiv">
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Username <span class="red">*</span></label>
											<div class="input-group">
<c:if test="${not empty userDetails }">												
												<input type="text" class="form-control" name="username" value="${userDetails.username }" readonly="readonly" maxlength="50" />
</c:if>	
<c:if test="${empty userDetails }">												
												<input type="text" class="form-control" name="username" value="" maxlength="50" />
</c:if>												
												<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
												
											</div>
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Password <span class="red">*</span></label> 
											<input type="password" class="form-control" id="password" name="password" value="${userDetails.password }" />
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Re-Password <span class="red">*</span></label> 
											<input type="password" class="form-control" name="repassword" value="${userDetails.password }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father First Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="fName" name="fatherFirstName" value="${student.getParentDetails().getFatherFirstName() }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father Middle Name</label> <input type="text"
												class="form-control" id="" name="fatherMiddleName" value="${student.getParentDetails().getFatherMiddleName() }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father Last Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="" name="fatherLastName" value="${student.getParentDetails().getFatherLastName() }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Mother Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="mName" name="motherName" value="${student.getParentDetails().getMotherName() }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Occupation</label> <input type="text"
												class="form-control" id="fatherOccupation" name="fatherOccupation" value="${student.getParentDetails().getFatherOccupation() }" />
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Occupation</label> <input type="text"
												class="form-control" id="motherOccupation" name="motherOccupation" value="${student.getParentDetails().getMotherOccupation() }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Mobile Number</label> <input type="text"
												class="form-control" id="fatherMobile" name="fatherMobile" value="${student.getParentDetails().getFatherMobile() }" />
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Mobile Number</label> <input type="text"
												class="form-control" id="motherMobile" name="motherMobile" value="${student.getParentDetails().getMotherMobile() }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Email address</label> <input type="email"
												class="form-control" id="fatherEmail" name="fatherEmail" value="${student.getParentDetails().getFatherEmail() }" /> <small>We'll
												never share your email with anyone else.</small>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Email address</label> <input type="email"
												class="form-control" id="motherEmail" name="motherEmail" value="${student.getParentDetails().getMotherEmail() }" /> <small>We'll
												never share your email with anyone else.</small>
										</fieldset>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-10">
										<div class="form-group">
											<label>Address:</label>
											<input type="checkbox" class="sameAddress" style="margin-left: 20%;"> <small>(Check if same as student address)</small>
											<textarea class="form-control" rows="5" id="parentAddress"
												name="parentAddress" style="resize: none">${student.getParentDetails().getParentAddress() } </textarea>
											<br> <input type="submit" class="btn btn-primary"
												value="Submit" />
											<button type="reset" class="btn bg-orange">Reset</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>
					</div>

				</div>
				<!-- /.box-body -->
<!-- 
				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div> -->
			</form>
		</div>
	</section>
</div>

<%-- <%@ include file="../common/footer.jsp"%> --%>
<style>
.selectedFeeField {
	border: 1px solid green;
}
.noSelectedFeeField {
	border: 1px solid #ff8181;
}
</style>

<script>
$(document).ready(function(){

	initializeHints();
	<c:if test="${Operation eq 'View'}">
		$('input, select, textarea').attr('disabled', 'disabled');
	</c:if>

	var dateNow = new Date();
	$('#yearOfAdmission').val(dateNow.getFullYear());
	$('#yearOfAdmission').datepicker({format: 'yyyy', viewMode: 'years', minViewMode: 'years' });

	
	
//	Get Admission No And Roll
	 $('#getRollForNewAdmission').focusout(function() {
		 var elementId=findPropertyNameForElement($(this));
		 var classCode=$('.'+elementId+'-classCode').val();
		 var sectionCode=$('.'+elementId+'-sectionCode').val();
		 var schooSession=$('.schoolSession').val();
		   //console.log(classCode+'---'+sectionCode+'------'+schooSession);
		 var admissionField = -1;
		 $('input[name~=admissionNo]').each(function(i,items){
				//console.log($(this).val()+'i::'+i);
			 	admissionField=i;
			 	//console.log(i);
			});
		 if(classCode!='' && sectionCode!='' && admissionField >-1)
		 {
		   $.ajax({
			   url : "getRollForNewAdmission",
			   data:"classCode="+classCode+"&sectionCode="+sectionCode+"&schoolSession="+schooSession,
				//dataType : "json",
				cache: false,
				async: false,
				 /*beforeSend: function() {
					   $("#div_id").html('<img src="./images/ajax-loader.gif"> saving...');
					  },*/
			   success: function(data) {
			 	//called when successful
				   //console.log(data);
				   var dataRes = data.split('~');
				   if(dataRes[1]!='notData')
				   {
				   		$('input[name=admissionNo]').val(dataRes[0]);
						$('input[name=rollNo]').val(dataRes[1]);
				   }
			   },
			   error: function(e) {
				   alert("error in getRollForNewAdmission");
			   }
			 }); 
		}
		   
		getAdditionalFee(classCode, sectionCode);
		
	   });
	 
	 
	
	$('input[name~=dateOfBirth]').on('change', function(){
// 		var yearOfAdmiss = $('#yearOfAdmission').val();
		$('input[name~=age]').val("");
		var dateNow = new Date();
		var yearOfAdmiss = dateNow.getFullYear();
		if(yearOfAdmiss != '')
		{
			var dob = $('input[name~=dateOfBirth]').val();
			if(dob != "")
			{
				var sdob = dob.split('-');
				//console.log(yearOfAdmiss-sdob[2]);
				$('input[name~=age]').val(yearOfAdmiss-sdob[2]);	
			}		
		}
	});

	
	$('.sameAddress').on('change', function(){
		if(this.checked)
		{
			var sAddress = $('textarea[name~=address]').val();
			$('textarea[name~=parentAddress]').val(sAddress);
		}
	});
	
	
});

var loadFile = function(event) {
   	var output = document.getElementById('output');
   	output.src = URL.createObjectURL(event.target.files[0]);
};

$('input[name~=username]').on('focusout', function(){
	if($(this).val() != "")
	{
	 var resData = null;
	 $.ajax({
		   url : jContextPath + "/validateUsernameForNewUser",
		   data:"username=" + $(this).val(),
			//dataType : "json",
			cache: false,
			async: false,
			 /*beforeSend: function() {
				   $("#div_id").html('<img src="./images/ajax-loader.gif"> saving...');
				  },*/
		   success: function(data) {
		 	//called when successful
			   //console.log(data);
			   resData = data;
		   },
		   error: function(e) {
			   alert("error in validateUsernameFOrNewUser");
		   }
		 }); 
		if(resData == true)
		{
			$(this).removeClass('error');
			var parentt = $(this).parent();//<i class="fa fa-check"></i>
			//console.log(parentt);
			//parentt.parent().find('label[for~=username]').remove();
			parentt.find('.input-group-addon').remove();
			parentt.append('<div class="input-group-addon greenFont" style="font-size: 18px;"><i class="fa fa-check"></i></div>');
			$('input[type=submit]').removeAttr('disabled');
		}
		else if(resData == false)
		{
			$(this).addClass('error');
			var parentt = $(this).parent();//<i class="fa fa-check"></i>
			//console.log(parentt);
			//parentt.find('label[for~=username]').remove();
			//parentt.append('<label class="error" generated="true" for="username">Username already exist.</label>');
			parentt.find('.input-group-addon').remove();
			parentt.append('<div class="input-group-addon redFont" style="font-size: 18px;"><i class="fa fa-close"></i></div>');
			$('input[type=submit]').attr('disabled', 'disabled');
		}

	}
	else
	{
		$(this).removeClass('error');
		var parentt = $(this).parent();//<i class="fa fa-check"></i>
		//console.log(parentt);
		//parentt.parent().find('label[for~=username]').remove();
		parentt.find('.input-group-addon').children().remove();
	}
});

$('[name="provideUserCredential"]').on('click', function(){
	if($(this).is(':checked')){
		$('.credentialDiv').find('input').removeAttr('disabled');
	} else {
		$('.credentialDiv').find('input').attr('disabled', 'disabled');
	}
});

if($('[name="provideUserCredential"]').is(':checked')){
	$('.credentialDiv').find('input').removeAttr('disabled');
} else {
	$('.credentialDiv').find('input').attr('disabled', 'disabled');
}

var classCode = $('input[name="classInformation.classCode"]').val();
var sectionCode = $('input[name="sectionInformation.sectionCode"]').val();
getAdditionalFee(classCode, sectionCode);


function getAdditionalFee (classCode, sectionCode){
	if(classCode && sectionCode){
		$.ajax({
			url : "getAdditionaFeeCategories",
			data : "classCode=" + classCode + "&sectionCode=" + sectionCode,
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(data) {
				//called when successful
				renderAdditionalFee(data);
			},
			error : function(e) {
				alert("error in getAdditionaFeeCategories");
			}
		});
	}
}



function renderAdditionalFee (data){
	var $additionalFeeContainer = $('.additionalFeeContainer');
	$additionalFeeContainer.empty();
	$.each(data, function(i, j){
		//alert(j.feeCategoryCode);
		var typeNameVal = '';
		if(j.type == "Y")
			typeNameVal = 'Yearly';
		else
			typeNameVal = 'Monthly';
		
		var additionalEle = ''
		+ '<div class="element-group">'
			+ '<div class="col-md-4">'
				+ '<fieldset class="form-group">'
					+ '<label>'+j.feeCategoryName+'</label>' 
					+ '<div class="switch">'
						+ '<input id="'+j.feeCategoryCode+'" name="selectedAdditionalFee" value="N" class="cmn-toggle cmn-toggle-round-small" type="checkbox" />'
						+ '<label for="'+j.feeCategoryCode+'"></label>'
					+ '</div>'
				+ '</fieldset>'
			+ '</div>'
			+ '<div class="col-md-4">'
				+ '<fieldset class="form-group">'
					+ '<label>Amount</label>' 
					+ '<input type="text" name="amount" class="form-control noSelectedFeeField" readonly="readonly" value="'+j.amount+'" />'
				+ '</fieldset>'
			+ '</div>'
			+ '<div class="col-md-4">'
				+ '<fieldset class="form-group">'
					+ '<label>Type</label>' 
					+ '<input type="text" name="typeName" class="form-control noSelectedFeeField" value="'+typeNameVal+'" readonly="readonly" />'
					+ '<input type="hidden" name="type" class="form-control" value="'+j.type+'" readonly="readonly" />'
					+ '<input type="hidden" name="feeCategoryCode" value="'+j.feeCategoryCode+'" />'
				+ '</fieldset>'
			+ '</div>'
		+ '</div>';
		
		$additionalFeeContainer.append(additionalEle);
	});
}

$('.transportDrop').on('change', function(){
	var $parentEle = $(this).closest('.col-md-12');
	var val = $(this).val();
	if(val){
		$parentEle.find($('input[name="transportId"]')).val(val.split('~')[0]);
		$parentEle.find($('input[name="amount"]')).val(val.split('~')[1]);
		if(val.split('~')[2] == "Y") {
			$parentEle.find($('input[name="type"]')).val("Y");
			$parentEle.find($('input[name="typeName"]')).val("Yearly");
		} else if(val.split('~')[2] == "M") {
			$parentEle.find($('input[name="type"]')).val("M");
			$parentEle.find($('input[name="typeName"]')).val("Monthly");
		} else {
			$parentEle.find($('input:not([name="feeCategoryCode"])')).val("");
		}
		$parentEle.find('input').addClass('selectedFeeField');
		$parentEle.find('input').removeClass('noSelectedFeeField');
	} else {
		$parentEle.find($('input:not([name="feeCategoryCode"])')).addClass('noSelectedFeeField').removeClass('selectedFeeField').val("");
	}
});

$(document).on("change", ".switch", function () {
	var switchEle = $(this).find('input');
	var $elementGroup = $(this).closest('.element-group');
	var switchVal = $(switchEle).val();
	var switchName = $(switchEle).attr('name');
	//var applicationSettingId = $(switchEle).closest('.form-group').find('.applicationSettingId').val();
	var val = '';
	if(switchVal == "N"){
		$(switchEle).val('Y');
		$(switchEle).attr('checked', 'checked');
		$elementGroup.find('input').removeClass('noSelectedFeeField').addClass('selectedFeeField');
		val = 'Y';
	} else {
		$(switchEle).val('N');
		$(switchEle).removeAttr('checked');
		$elementGroup.find('input').removeClass('selectedFeeField').addClass('noSelectedFeeField');
		val = 'N';
	}
	/* var response = $(document).appProcessRequest({url: 'addOrChangeEmailSettings', data: switchName+'='+val+"&applicationSettingId="+applicationSettingId , role: 'response-link'});
	if(response == "SUCCESS"){
		var msg = new String("Email Settings Successfully saved, Please login again to use changed application settings...!!!");
		alert(msg);
	} else {
		alert("Sorry! Email Settings is not Successfully saved...!!!");
	} */
	
});

$('input:hidden[name="feeCategoryCode"]').each(function(){
	var $elementGroup = $(this).closest('.element-group');
	<c:forEach var="additionalFee" items="${StudentAdditionalFeeDetailsList }">
<c:if test="${additionalFee.feeCategoryCode eq StudentTransportFee.feeCategoryCode }">
		if('${additionalFee.feeCategoryCode }' == $(this).val()) {
			$elementGroup.find('[name="selectedAdditionalFee"]').val('${StudentTransportFee.attribute1 }~${StudentTransportFee.amount }~${StudentTransportFee.type }').change();
			$elementGroup.find('input').removeClass('noSelectedFeeField').addClass('selectedFeeField');
		}
</c:if>
<c:if test="${additionalFee.feeCategoryCode ne StudentTransportFee.feeCategoryCode }">
		if('${additionalFee.feeCategoryCode }' == $(this).val()) {
			$elementGroup.find('[name="selectedAdditionalFee"]').val('Y').attr('checked', 'checked');
			$elementGroup.find('input').removeClass('noSelectedFeeField').addClass('selectedFeeField');
			//$elementGroup.find('[name="selectedAdditionalFee"]').trigger('click');
		}
</c:if>		
	</c:forEach>
});
</script>