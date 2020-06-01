<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="usr" value="${User }" />
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			<c:if test="${Operation eq 'View'}">${Operation }</c:if> <c:if test="${Operation ne 'View'}">Edit</c:if> User Details<small>( * fields are mandatory)</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">User Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchUser" class="btn btn-primary pull-right">Search User</a>
				</div>
			</div>

			<form action="updateUser" method="post" enctype="multipart/form-data" class="supadmn">
			<input type="hidden" name="image" value="${usr.userDetails.image }">
			<input type="hidden" name="userId" value="${usr.userId }">
			<input type="hidden" name="code" value="${usr.code }">
			<input type="hidden" name="modifiedBy" value="${usr.userDetails.modifiedBy }">
			<input type="hidden" name="salt" value="${usr.salt }">
			<input type="hidden" name="userDetailsId" value="${usr.userDetails.userDetailsId }">
			<input type="hidden" name="currentUserRoleId" value="${usr.userRole.userRoleId }">
				<div class="box-body">
					<div class="col-md-12">
								<div class="form-group">

									<div class="col-md-12">
										<div class="col-md-3">
											<fieldset class="form-group">
												<div style="height:170px; width:160px; margin-left:40px; margin-bottom:10px; background-image: url('${pageContext.request.contextPath}/static/img/profile.png'); background-size: 160px 170px;">
													<img id="output" width="160px" height="170px" <c:if test="${usr.userDetails.image ne null}">  src="${pageContext.request.contextPath}/upload/user_images/${usr.userDetails.image }" </c:if> />
												</div>
												<label><font class="fontred"></font></label> <span
													class="btn btn-primary btn-file btn-xs" style="margin-left: 37px; width: 160px;">
													<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
													<input type="file" name="pimage" onchange="loadFile(event)" /></span>


											</fieldset>
										</div>
										
									<%-- 	<div class="col-md-9">
											<div class="col-md-9">
												<fieldset class="form-group">
<c:if test="${userProfile.userRoleId eq 'ROLE_SUPADMIN' }">
													<label>School Name <span class="red">*</span></label> 
													<select class="form-control" name="schoolCode">
<c:forEach var="SchoolProfile" items="${SchoolProfiles }">
														<option value="${SchoolProfile.schoolCode }" <c:if test="${usr.userDetails.schoolProfile.schoolCode eq SchoolProfile.schoolCode }"> selected="selected"</c:if> > ${SchoolProfile.name } </option>
</c:forEach>
													</select>
</c:if>
<c:if test="${userProfile.userRoleId ne 'ROLE_SUPADMIN' }">
													<label>School Name <span class="red">*</span></label> 
													<select class="form-control" name="schoolCode">
														<option value="${usr.userDetails.schoolProfile.schoolCode }" > ${usr.userDetails.schoolProfile.name } </option>
													</select>
</c:if>
												</fieldset>
											</div>
										</div> --%>
										
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Role Name <span class="red">*</span></label> 
													<select class="form-control" name="userRoleId" data-val="${usr.userRole.userRoleId }">
<c:if test="${usr.userRole.userRoleId eq 'ROLE_USER' }">
														<option value="${usr.userRole.userRoleId }"> ${usr.userRole.userRoleName } </option>
														
</c:if>
<c:if test="${usr.userRole.userRoleId ne 'ROLE_USER' }">
<c:forEach var="UserRole" items="${UserRoles }">
														<option value="${UserRole.userRoleId }" <c:if test="${usr.userRole.userRoleId eq UserRole.userRoleId }"> selected="selected"</c:if> > ${UserRole.userRoleName } </option>
</c:forEach>
</c:if>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Email <span class="red">*</span></label> <input type="text"
														class="form-control" id="email" name="email" value="${usr.userDetails.email }" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Active <span class="red">*</span></label>
													<select class="form-control" name="pactive">
														<option value="">-- Select --</option>
														<option value="A" <c:if test="${usr.active eq true }"> selected="selected"</c:if> >Active</option>
														<option value="I" <c:if test="${usr.active eq false }"> selected="selected"</c:if> >Inactive</option>
													</select>
												</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>Username <span class="red">*</span></label>
												<div class="input-group">
													<input type="text" class="form-control" name="username" readonly="readonly" value="${usr.username}" />
													<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
													
												</div>
											</fieldset>
											</div>
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>Password <span class="red">*</span></label> 
												<input type="password" class="form-control" id="password" name="password" value="${usr.password }" />
											</fieldset>
											</div>
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>Re-Password <span class="red">*</span></label> 
												<input type="password" class="form-control" id="rollNo" name="repassword" value="${usr.password }" />
											</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>First Name <span class="red">*</span></label>
													<input type="text" class="form-control" id="firstname"
														name="firstname" value="${usr.userDetails.firstname }" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Middle Name</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="middlename" name="middlename" value="${usr.userDetails.middlename }" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Last Name <span class="red">*</span></label> <input
														type="text" class="form-control" id="lastname" value="${usr.userDetails.lastname }"
														name="lastname" />
												</fieldset>
											</div>
										</div>
										<div class="col-md-12">
									
										<div id="classSectionGroup" style="display: none;">
												<div class="col-md-4">
													<div class="form-group">
														<label>Class <span class="red">*</span></label> <input type="text"
															class="form-control className intelliClass-0-className stdClassHints" placeholder="Class Name"
															id="sclass" name="className" readonly="readonly" value="${usr.userDetails.classCode}${usr.userDetails.parentDetails.studentDetails.admissionDetails.classInformation.className}" />
													</div>
												</div>
												<div class="col-md-2">
													<label>Class ID</label> <input type="text"
														class="form-control intelliClass-0-classCode" id="classCode"
														name="classCode" readonly="readonly" value="${usr.userDetails.classCode}${usr.userDetails.parentDetails.studentDetails.admissionDetails.classInformation.classCode}" />
												</div>
											
												<div class="col-md-4">
													<div class="form-group">
														<label>Section <span class="red">*</span></label> <input type="text"
															class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" readonly="readonly"
															value="${usr.userDetails.sectionCode}${usr.userDetails.parentDetails.studentDetails.admissionDetails.sectionInformation.sectionName}" />
													</div>
												</div>
												<div class="col-md-2">
													<label>Section ID</label> <input type="text"
														class="form-control intelliClass-0-sectionCode" id="sectionCode"
														name="sectionCode" readonly="readonly" value="${usr.userDetails.sectionCode}${usr.userDetails.parentDetails.studentDetails.admissionDetails.sectionInformation.sectionCode}" />
												</div>
										</div>
										
										<div class="col-md-3"></div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Mobile <span class="red">*</span></label>
													<input type="text" class="form-control" id="mobile"
														name="mobile" value="${usr.userDetails.mobile }" />
												</fieldset>
											</div>
										</div>
											<div id="studentDetailsGroup" style="display: none;">	
												<input type="hidden" class="form-control" placeholder="Parent Details Id" id="" name="parentDetailsId" />
												<div class="col-md-4">
													<div class="form-group">
														<label>Student Full Name </label> <input type="text"
															class="form-control studentDetailsAutoComplete intelliDetails-0-fullName" placeholder="Student Full Name"
															readonly="readonly" id="" name="" value="${usr.userDetails.parentDetails.studentDetails.fullname }"/>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label>Admission No. <span class="red">*</span></label> <input type="text" readonly="readonly"
															class="form-control intelliDetails-0-admissionNo admissionNo" placeholder="Admission No" id="" name="admissionNo" value="${usr.userDetails.parentDetails.studentDetails.admissionNo }" disabled="disabled"/>
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label>Roll No. <span class="red">*</span></label> <input type="text" readonly="readonly"
															class="form-control intelliDetails-0-rollNo rollNo" placeholder="Roll No" id="" name="rollNo" value="${usr.userDetails.parentDetails.studentDetails.admissionDetails.rollNo }" disabled="disabled"/>
													</div>
												</div>
												
											</div>
										</div>
										
									</div>
								</div>

								
						</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div> 
			</form>
		</div>
	</section>
</div>


<script>
$(document).ready(function(){
	initializeHints();
	$(".sectionName").on('focusout', function(e){
		initializeStudentDetailsAutoComplete($('[name="classCode"]').val(), $('[name="sectionCode"]').val());
	});
	
	<c:if test="${Operation eq 'View'}">
		$('input, select, textarea').attr('disabled', 'disabled');
	</c:if>

	$('input[name~=usernamee]').on('focusout', function(){
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
				   alert("error");
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
	
	
});

var loadFile = function(event) {
   	var output = document.getElementById('output');
   	output.src = URL.createObjectURL(event.target.files[0]);
};

	var selectedVal = $('[name="userRoleId"]').val();
	if(selectedVal == 'ROLE_STAFF'){
		$('#classSectionGroup').show();
		$('#studentDetailsGroup').hide();
		$('#classSectionGroup').find('input[type="text"]').each(function(i){
			$(this).removeAttr("disabled");
		});
	} else if(selectedVal == 'ROLE_USER'){
		$('[name$="name"]').attr('readonly', 'readonly');
		$('#classSectionGroup').show();
		$('#classSectionGroup').find('input[type="text"]').each(function(i){
			$(this).removeAttr("disabled");
		});
		$('#studentDetailsGroup').show();
		$('#studentDetailsGroup').find('input[type="text"]').each(function(i){
			$(this).removeAttr("disabled");
		});
	} else {
		$('#classSectionGroup').hide();
		$('#classSectionGroup').find('input[type="text"]').each(function(i){
			$(this).attr("disabled", "disabled");
		});
		$('#studentDetailsGroup').hide();
		$('#studentDetailsGroup').find('input[type="text"]').each(function(i){
			$(this).attr("disabled", "disabled");
		});
	}
	
	$('[name="userRoleId"]').on('change', function(){
		var currentUserRoleId = $('[name="currentUserRoleId"]').val();
		var lastVal = $(this).attr('data-val');
		if(currentUserRoleId != 'ROLE_USER' && $(this).val() == 'ROLE_USER'){
			alert("Sorry! you can't change status in User...!!!");
			$(this).val(lastVal);
			return false;
		}
		var agree = confirm("Are you sure? chnage the Role of User...!!!");
		if(agree){
			var selectedVal = $(this).val();
			$(this).attr('data-val', selectedVal);
			if(selectedVal == 'ROLE_STAFF'){
				$('[name="className"]').removeAttr('readonly');
				$('[name="sectionName"]').removeAttr('readonly');
				$('#classSectionGroup').show();
				$('#studentDetailsGroup').hide();
				$('#classSectionGroup').find('input[type="text"]').each(function(i){
					$(this).removeAttr("disabled");
				});
			} else if(selectedVal == 'ROLE_USER'){
				$('[name="className"]').attr('readonly','readonly');
				$('[name="sectionName"]').attr('readonly','readonly');
				$('#classSectionGroup').show();
				$('#classSectionGroup').find('input[type="text"]').each(function(i){
					$(this).removeAttr("disabled");
				});
				$('#studentDetailsGroup').show();
				$('#studentDetailsGroup').find('input[type="text"]').each(function(i){
					$(this).removeAttr("disabled");
				});
			} else {
				$('#classSectionGroup').hide();
				$('#classSectionGroup').find('input[type="text"]').each(function(i){
					$(this).attr("disabled", "disabled");
				});
				$('#studentDetailsGroup').hide();
				$('#studentDetailsGroup').find('input[type="text"]').each(function(i){
					$(this).attr("disabled", "disabled");
				});
			}
		} else {
			$(this).val(lastVal);
		}
	});

$('[name="rollNo"]').on('change', function(e){
	var url = "getParentUserDetailsId?classCode="+$('[name="classCode"]').val()+"&sectionCode="+$('[name="sectionCode"]').val()+"&admissionNo="+$('[name="admissionNo"]').val()+"&rollNo="+$('[name="rollNo"]').val();
	var parentUserDetailsId = $(document).appProcessRequest({url:url, role: 'response-link'});
	$('[name="parentDetailsId"]').val(parentUserDetailsId);
});
</script>