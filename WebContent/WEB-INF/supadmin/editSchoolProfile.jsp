
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit School Profile<small>( * fields are mandatory)</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">School Profile</h3>
				<div class="col-md-6 pull-right">
					<a href="searchSchoolProfile" class="btn btn-primary pull-right">Search School Profile</a>
				</div>
			</div>

			<form action="updateSchoolProfile" method="post" enctype="multipart/form-data" class="formValid">
				<div class="box-body">
					<div class="col-md-12">
								<div class="form-group">

									<div class="col-md-12">
										<div class="col-md-3">
											<fieldset class="form-group">
<c:if test="${empty SchoolProfile.logo }">	
												<div style="height:170px; width:160px; margin-left:40px; margin-bottom:10px; background-image: url('${pageContext.request.contextPath}/static/img/profile.png'); background-size: 160px 170px;">
												</div>
</c:if>
<c:if test="${not empty SchoolProfile.logo }">			
												<div style="height:170px; width:160px; margin-left:40px; margin-bottom:10px; background-size: 160px 170px;">										
													<img src="${pageContext.request.contextPath}/upload/school_images/${SchoolProfile.logo }" id="output" width="160px" height="170px"/>
												</div>
</c:if>
												<label><font class="fontred"></font></label> <span
													class="btn btn-primary btn-file btn-xs" style="margin-left: 37px; width: 160px;">
													<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
													<input type="file" name="pimage" onchange="loadFile(event)" /></span>
													<input type="hidden" name="logo" value="${SchoolProfile.logo }">

											</fieldset>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
											<fieldset class="form-group">
												<label>School Code <span class="red">*</span></label>
												<div class="input-group">
													<input type="text" class="form-control schoolCode" name="schoolCode" value="${SchoolProfile.schoolCode }" readonly="readonly" />
													<div class="input-group-addon greenFont" style="font-size: 18px;"></div>
													
												</div>
											</fieldset>
											</div>
											<div class="col-md-8">
											<fieldset class="form-group">
												<label>School Name <span class="red">*</span></label> 
												<input type="text" class="form-control name" id="name" name="name" value="${SchoolProfile.name }" />
											</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Start Year <span class="red">*</span></label>
													<input type="text" class="form-control startYear" id="startYear"
														name="startYear" value="${SchoolProfile.startYear }" />
												</fieldset>
											</div>
											<div class="col-md-4">
												<fieldset class="form-group">
													<label>Registration Date</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="registrationDate" name="registrationDate" value="${SchoolProfile.registrationDate }" />
												</fieldset>
											</div>
										</div>
										<div class="col-md-9">
											<div class="col-md-6">
												<fieldset class="form-group">
													<label>Email <span class="red">*</span></label>
													<input type="text" class="form-control email" id="email"
														name="email" value="${SchoolProfile.email }" />
												</fieldset>
											</div>
											<div class="col-md-6">
												<fieldset class="form-group">
													<label>Website</label> <span class="red">&nbsp;</span><input type="text"
														class="form-control" id="website" name="website" value="${SchoolProfile.website }" />
												</fieldset>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
										<fieldset class="form-group">
											<label>Address <span class="red">*</span></label>
											<input type="text" class="form-control address" id="address"
												name="address" value="${SchoolProfile.address }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>District</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="district" name="district" value="${SchoolProfile.district }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>City</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="city" name="city" value="${SchoolProfile.city }" />
										</fieldset>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>State <span class="red">*</span></label>
											<input type="text" class="form-control state" id="state"
												name="state" value="${SchoolProfile.state }" />
										</fieldset>
									</div>
									<div class="col-md-4">
										<fieldset class="form-group">
											<label>Postcode <span class="red">*</span></label>
											<input type="text" class="form-control postcode" id="postcode"
												name="postcode" value="${SchoolProfile.postcode }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Phone</label> <span class="red">*</span><input type="text"
												class="form-control phone" id="phone" name="phone" value="${SchoolProfile.phone }" />
										</fieldset>
									</div>
									<div class="col-md-3">
										<fieldset class="form-group">
											<label>Fax</label> <span class="red">&nbsp;</span><input type="text"
												class="form-control" id="fax" name="fax" value="${SchoolProfile.fax }" />
										</fieldset>
									</div>
								</div>

								
						</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div> 
			</form>
			
			<br/><br/><br/>
			
			
			<div class="box-header">
				<h3 class="box-title">Promote School Profile</h3>
			</div>
			
			<form action="updatePromoteSchoolData" method="post" class="formValid">
				<input type="hidden" class="form-control schoolCode" name="schoolCode" value="${SchoolProfile.schoolCode }" readonly="readonly" />
				<div class="col-md-12"><br>
					<div class="col-md-4">
						<fieldset class="form-group">
							<label>Session <span class="red">*</span></label> 
							<select name="sessionCode" class="sessionName form-control">
								<option value="">Select</option>
<c:forEach var="session" items="${SessionList }">
								<option value="${session.sessionCode }">${session.sessionName }</option>
</c:forEach>							
							</select>
						</fieldset>
					</div>
				</div>
				

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div> 
			</form>
		</div>
	</section>
</div>


<script>
	$(document).ready(function() {
<c:if test="${Operation eq 'View'}">
		$('input, select, textarea').attr('disabled', 'disabled');
</c:if>

		$('input[name~=schoolCodee]').on('focusout', function() {
			if ($(this).val() != "") {
				var resData = null;
				$.ajax({
					url : "validateSchoolCode",
					data : "schoolCode="
							+ $(this).val(),
					//dataType : "json",
					cache : false,
					async : false,
					/*beforeSend: function() {
					   $("#div_id").html('<img src="./images/ajax-loader.gif"> saving...');
					  },*/
					success : function(data) {
						//called when successful
						//console.log(data);
						resData = data;
					},
					error : function(e) {
						alert("error");
					}
				});
				if (resData == true) {
					$(this).removeClass('error');
					var parentt = $(this).parent();//<i class="fa fa-check"></i>
					//console.log(parentt);
					//parentt.parent().find('label[for~=username]').remove();
					parentt.find('.input-group-addon').remove();
					parentt.append('<div class="input-group-addon greenFont" style="font-size: 18px;"><i class="fa fa-check"></i></div>');
					$('input[type=submit]').removeAttr('disabled');
				} else if (resData == false) {
					$(this).addClass('error');
					var parentt = $(this).parent();//<i class="fa fa-check"></i>
					//console.log(parentt);
					//parentt.find('label[for~=username]').remove();
					//parentt.append('<label class="error" generated="true" for="username">Username already exist.</label>');
					parentt.find('.input-group-addon').remove();
					parentt.append('<div class="input-group-addon redFont" style="font-size: 18px;"><i class="fa fa-close"></i></div>');
					$('input[type=submit]').attr('disabled', 'disabled');
				}

			} else {
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