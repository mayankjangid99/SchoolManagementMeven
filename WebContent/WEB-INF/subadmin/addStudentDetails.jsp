<%-- <%@ include file="../common/header.jsp"%> --%>
<%@page import="com.school.common.generic.StaticValue"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Student <small>(* fields are mandatory)</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Student Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchStudentDetails" class="btn btn-primary pull-right">Search Student Details</a>
				</div>
			</div>

			<form action="saveStudentDetails" method="post" enctype="multipart/form-data" class="addStudent">
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
													<img id="output" width="160px" height="170px"/>
												</div>
												<label><font class="fontred"></font></label> <span
													class="btn btn-primary btn-file btn-xs" style="margin-left: 37px; width: 160px;">
													<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
													<input type="file" name="psimage" onchange="loadFile(event)" /></span>


											</fieldset>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Class <span class="red">*</span></label> <input type="text"
														class="form-control intelliClass-0-className stdClassHints"
														id="className" name="classInformation.className" />
												</fieldset>
											</div>
											<div class="col-md-2">
												<label>Class ID</label> <input type="text"
													class="form-control intelliClass-0-classCode"
													id="classCode" name="classInformation.classCode"
													readonly="readonly" />
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Section <span class="red">*</span></label> <input type="text"
														class="form-control intelliClass-0-sectionName stdSectionHints"
														id="getRollForNewAdmission" name="sectionInformation.sectionName" />
												</fieldset>
											</div>
											<div class="col-md-2">
												<label>Section ID</label> <input type="text"
													class="form-control intelliClass-0-sectionCode"
													id="sectionCode" name="sectionInformation.sectionCode"
													readonly="readonly" />
											</div>
											<div class="col-md-3 form-group">
												<label>Status <span class="red">*</span></label> 
												<select	class="form-control" name="status">
													<%-- <option value="">-- <st:message code="ui.text.Select" /> --</option> --%>
													<option value="Y"><st:message code="ui.text.Active" /></option>
													<option value="N"><st:message code="ui.text.Inactive" /></option>
												</select>
											</div>
											<div class="col-md-3 form-group">
												<label>Session <span class="red">*</span></label>
												<input type="text" class="form-control" name="schoolSession.sessionCode" value="${sessionScope.SessionManager.getSchoolProfileUtils().getCurrentSession() }" readonly="readonly">
												<%-- <select class="form-control schoolSession" id="session"
													name="schoolSession.session">
													<c:forEach var="schoolSession"
														items="${sessionScope.SchoolProfile.getSchoolSessions() }">
														<option>${schoolSession.getSession() }</option>
													</c:forEach>
												</select> --%>
											</div>
											<div class="col-md-3 form-group">
												<label>Admission No <span class="red">*</span></label> <input type="text"
													class="form-control" id="admissionNo" readonly="readonly"
													name="admissionNo" value="" />
											</div>
											<div class="col-md-3 form-group">
												<label>Roll No <span class="red">*</span></label> <input type="text"
													class="form-control" id="rollNo" name="rollNo"
													readonly="readonly" value="" />
											</div>
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>First Name <span class="red">*</span></label>
												<input type="text" class="form-control" id="studentfname"
													name="firstName" />
											</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Middle Name</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="studentmidname" name="middleName" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Last Name <span class="red">*</span></label> <input
														type="text" class="form-control" id="studentlname"
														name="lastName" />
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
											<option value="M">Male</option>
											<option value="F">Female</option>
										</select>
									</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Date of Birth <span class="red">*</span></label> 
												<div class="input-group">
													<input type='text' class="date-pickerfull form-control pull-left" id='dob' readonly="readonly" placeholder="dd-mm-yyyy" name="dateOfBirth" />
													<div class="input-group-addon">
							                    	    <i class="fa fa-calendar"></i>
							                      	</div>
							                    </div>
											</fieldset>
										</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Age <span class="red">*</span></label> <input type="text"
													class="form-control" id="age" name="age" readonly="readonly"
													value="${AdmissionDetails.getStudentDetails().getAge()}" />
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
													<select name="category" class="form-control" id="categoryy">
														<option value="">-- Select --</option>
														<option value="GEN">General</option>
														<option value="OBC">OBC</option>
														<option value="SC">SC</option>
														<option value="ST">ST</option>
													</select>
											</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mobile</label> <input type="text"
												class="form-control" id="mobile" name="mobile" />
										</fieldset>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Email</label> <input type="text"
												class="form-control" id="email" name="email" /> <small
												class="primary muted">We'll never share your email
												with anyone else.</small>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Previous School Name(If Any)</label> <input
												type="text" class="form-control" id="previousSchoolName"
												name="previousSchoolName" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-10">
										<div class="form-group">
											<label>Address of Correspondence</label>
											<textarea class="form-control" rows="5" id="address"
												name="address" style="resize: none"></textarea>
										</div>
									</div>
								</div>



							</div>
	
	
	
							<div id="additional" class="tab-pane fade">
								<div class="row form-group"><font style="color: green;">Some change will display after selection class and section.</font></div>
								<div class="col-md-12">
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
								
								<div class="col-md-12 additionalFeeContainer">
								</div>
								
							</div>
	
	
	
							<div id="parent" class="tab-pane fade">
								<br>
								<div class="col-md-12">
									<div class="col-md-4 form-group">
										<input type="checkbox" name="provideUserCredential" value="Y" checked="checked" />&nbsp;&nbsp; Provide User Login Credential
									</div>
								</div>
								<div class="col-md-12 credentialDiv">
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Username <span class="red">*</span></label>
											<div class="input-group">
												<input type="text" class="form-control" name="username" maxlength="50" />
												<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
												
											</div>
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Password <span class="red">*</span></label> 
											<input type="password" class="form-control" id="password" name="password" maxlength="15" />
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Re-Password <span class="red">*</span></label> 
											<input type="password" class="form-control" name="repassword" maxlength="15" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father First Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="fName" name="fatherFirstName" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father Middle Name</label> <input type="text"
												class="form-control" id="" name="fatherMiddleName" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Father Last Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="" name="fatherLastName" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Mother Name <span class="red">*</span></label> <input type="text"
												class="form-control" id="mName" name="motherName" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Occupation <span class="red">*</span></label> <input type="text"
												class="form-control" id="fOccupation" name="fatherOccupation" />
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Occupation <span class="red">*</span></label> <input type="text"
												class="form-control" id="mOccupation" name="motherOccupation" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Mobile Number <span class="red">*</span></label> <input type="text"
												class="form-control" id="fMobileNo" name="fatherMobile" />
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Mobile Number</label> <input type="text"
												class="form-control" id="mMobileNo" name="motherMobile" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Father Email address <span class="red">*</span></label> <input type="email"
												class="form-control" id="fEmail" name="fatherEmail" /> <small>We'll
												never share your email with anyone else.</small>
										</fieldset>
									</div>
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Mother Email address</label> <input type="email"
												class="form-control" id="mEmail" name="motherEmail" /> <small>We'll
												never share your email with anyone else.</small>
										</fieldset>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-10">
										<div class="form-group">
											<label>Address: <span class="red">*</span></label>
											<input type="checkbox" class="sameAddress" style="margin-left: 20%;"> <small>(Check if same as student address)</small>
											<textarea class="form-control" rows="5" id="pAddress"
												name="parentAddress" style="resize: none"></textarea>
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
	
// $('input, select, textarea').attr('disabled', 'disabled');

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
			$parentEle.find($('input')).val("");
		}
		$parentEle.find('input').addClass('selectedFeeField');
		$parentEle.find('input').removeClass('noSelectedFeeField');
	} else {
		$parentEle.find($('input')).addClass('noSelectedFeeField').removeClass('selectedFeeField').val("");
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
		$elementGroup.find('input').removeClass('noSelectedFeeField');
		$elementGroup.find('input').addClass('selectedFeeField');
		val = 'Y';
	} else {
		$(switchEle).val('N');
		$(switchEle).removeAttr('checked');
		$elementGroup.find('input').removeClass('selectedFeeField');
		$elementGroup.find('input').addClass('noSelectedFeeField');
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
</script>