
<div class="content-wrapper" style="margin-left: 0px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Sup Admin Details<small>( * fields are mandatory)</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
								<h3 class="box-title">Super Admin Details</h3>
			</div>

			<form action="saveSupAdminDetails" method="post" enctype="multipart/form-data" class="supadmn">
				<div class="box-body">
					<div class="col-md-12">
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
													<input type="file" name="pimage" onchange="loadFile(event)" /></span>


											</fieldset>
										</div>
										<div class="col-md-9">
											<div class="col-md-3">
												<fieldset class="form-group">
													<label>Role Name <span class="red">*</span></label> 
													<input type="text" class="form-control" id="roleName" name="userRoleName" value="SUPER ADMIN" readonly="readonly" />
												</fieldset>
											</div>
											<div class="col-md-3">
												<fieldset class="form-group">
													<label>Role ID <span class="red">*</span></label> 
													<input type="text" class="form-control" id="usersRoleId" name="userRoleId" value="ROLE_SUPADMIN" readonly="readonly" />
												</fieldset>
											</div>
											<!-- <div class="col-md-2">
												<label>Role ID</label> <input type="text"
													class="form-control"
													id="classCode" name="roleId" readonly="readonly" />
											</div> -->
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Email <span class="red">*</span></label> <input type="text"
														class="form-control"
														id="email" name="email" />
												</fieldset>
											</div>
											<div class="col-md-2">
												<fieldset class="form-group">
													<label>Active <span class="red">*</span></label> 
													<select class="form-control" name="pactive">
														<!-- <option value="">-- Select --</option> -->
														<option value="A">Active</option>
														<option value="I">Inactive</option>
													</select>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Username <span class="red">*</span></label>
													<div class="input-group">
														<input type="text" class="form-control" name="username" />
														<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
														
													</div>
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Password <span class="red">*</span></label> 
													<input type="password" class="form-control" id="password" name="password" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Re-Password <span class="red">*</span></label> 
													<input type="password" class="form-control" id="rollNo" name="repassword" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>First Name <span class="red">*</span></label>
													<input type="text" class="form-control" id="firstname" name="firstname" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Middle Name</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="middlename" name="middlename" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Last Name <span class="red">*</span></label> 
													<input type="text" class="form-control" id="lastname" name="lastname" />
												</fieldset>
											</div>
											
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Mobile <span class="red">*</span></label>
													<input type="text" class="form-control" id="mobile"
														name="mobile" />
												</fieldset>
											
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Micro Service Enabled</label> <span class="red">&nbsp;</span>
													<input type="text" class="form-control" name="microServiceEnabled" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Micro Service URL <span class="red">*</span></label> 
													<input type="text" class="form-control" name="microServiceUrl" />
												</fieldset>
											</div>
											</div>
										</div>
									</div>
								</div>

								<%-- <div class="col-md-12">
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
													<input type='text' class="date-pickerfull form-control pull-left" id='dob' readonly="readonly" placeholder="dd-mm-yyyy" name="dob" />
													<div class="input-group-addon">
							                    	    <i class="fa fa-calendar"></i>
							                      	</div>
							                    </div>
											</fieldset>
										</div>
										<div class="col-md-4">
											<fieldset class="form-group">
												<label>Student Age <span class="red">*</span></label> <input type="text"
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
													<select name="category" class="form-control">
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
											<label>Mobile Number</label> <input type="text"
												class="form-control" id="sMobile" name="sMobile" />
										</fieldset>
									</div>
								</div>

								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Email address</label> <input type="text"
												class="form-control" id="sEmail" name="sEmail" /> <small
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
											<textarea class="form-control" rows="5" id="sAddress"
												name="sAddress" style="resize: none"></textarea>
										</div>
									</div>
								</div> --%>
						</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" disabled="disabled" />
				</div> 
			</form>
		</div>
	</section>
</div>


<script>
$(document).ready(function(){

	$('body').removeClass().addClass('login-page');
	
	$('input[name~=username]').on('focusout', function(){
		if($(this).val() != "")
		{
		 var resData = null;
		 $.ajax({
			   url : "validateUsernameForNewUser",
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
</script>