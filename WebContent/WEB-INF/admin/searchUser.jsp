<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />
<c:set var="schoolProfile" value="${sessionScope.SessionManager.schoolProfileUtils }" />

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search User Details<!-- <small>( * fields are mandatory)</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">User Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addUser" class="btn btn-primary pull-right">Add User</a>
				</div>
			</div>

			<form action="resultUser" method="post" enctype="" class="supadmnn">
				<div class="box-body">
									<div class="col-md-12">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Role Name </label> 
													<select class="form-control" name="userRoleId">
														<option value="">-- Select --</option>
<c:forEach var="UserRole" items="${UserRoles }"><option value="${UserRole.userRoleId }"> ${UserRole.userRoleName } </option></c:forEach>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Username </label>
													<div class="input-group">
														<input type="text" class="form-control" name="username" />
														<div class="input-group-addon greenFont" style="font-size: 18px;"></div>												
													</div>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Email </label> <input type="text"
														class="form-control"
														id="email" name="email" />
												</fieldset>
											</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-3">
											<fieldset class="form-group">
												<label>First Name </label>
												<input type="text" class="form-control" id="firstname"
													name="firstname" />
											</fieldset>
										</div>
										<div class="col-md-3">
												<fieldset class="form-group">
													<label>Middle Name</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="middlename" name="middlename" />
												</fieldset>
										</div>
										<div class="col-md-3">
											<fieldset class="form-group">
												<label>Last Name </label> <input
													type="text" class="form-control" id="lastname"
													name="lastname" />
											</fieldset>
										</div>
										<div class="col-md-3">
											<fieldset class="form-group">
												<label>Active </label> 
												<select class="form-control" name="pactive">
													<option value="">-- Select --</option>
													<option value="A">Active</option>
													<option value="I">Inactive</option>
												</select>
											</fieldset>
										</div>
									</div>
									<hr>
									<div class="col-md-12">
										<div id="classSectionGroup" style="display: none;">
												<div class="col-md-4">
													<div class="form-group">
														<label>Class </label> <input type="text"
															class="form-control className intelliClass-0-className stdClassHints" placeholder="Class Name"
															id="sclass" name="className" />
													</div>
												</div>
												<div class="col-md-2">
													<label>Class ID</label> <input type="text"
														class="form-control intelliClass-0-classCode" id="classCode"
														name="classCode" readonly="readonly" />
												</div>
											
												<div class="col-md-4">
													<div class="form-group">
														<label>Section </label> <input type="text"
															class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
													</div>
												</div>
												<div class="col-md-2">
													<label>Section ID</label> <input type="text"
														class="form-control intelliClass-0-sectionCode" id="sectionCode"
														name="sectionCode" readonly="readonly" />
												</div>
										</div>
											<div id="studentDetailsGroup" style="display: none;">	
												<input type="hidden" class="form-control" placeholder="Parent Details Id" id="" name="parentDetailsId" />
												<div class="col-md-4">
													<div class="form-group">
														<label>Student Full Name </label> <input type="text"
															class="form-control studentDetailsAutoComplete intelliDetails-0-fullName" placeholder="Student Full Name"
															id="" name="" />
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label>Admission No. </label> <input type="text"
															class="form-control intelliDetails-0-admissionNo" placeholder="Admission No" id="" name="admissionNo" />
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label>Roll No. </label> <input type="text"
															class="form-control intelliDetails-0-rollNo" placeholder="Roll No" id="" name="rollNo" />
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
$('[name="userRoleId"]').on('change', function(){
	var selectedVal = $(this).val();
	if(selectedVal == 'ROLE_STAFF'){
		$('#classSectionGroup').show();
		$('#studentDetailsGroup').hide();
		$('#classSectionGroup').find('input[type="text"]').each(function(i){
			$(this).removeAttr("disabled");
		});
	} else if(selectedVal == 'ROLE_USER'){
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
	
});

$('[name="rollNo"]').on('change', function(e){
	var url = "getParentUserDetailsId?classCode="+$('[name="classCode"]').val()+"&sectionCode="+$('[name="sectionCode"]').val()+"&admissionNo="+$('[name="admissionNo"]').val()+"&rollNo="+$('[name="rollNo"]').val();
	var parentUserDetailsId = $(document).appProcessRequest({url:url, role: 'response-link'});
	$('[name="parentDetailsId"]').val(parentUserDetailsId);
});
</script>