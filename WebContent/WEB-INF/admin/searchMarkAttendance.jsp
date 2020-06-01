<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper" style="height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add/Edit Mark Attendance <small>it all starts here</small>
		</h1>
	</section>
	
<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 900px;">
			<div class="box-header">
				<h3 class="box-title">Add Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchAttendanceDetails" class="btn btn-primary pull-right">Get Attendance</a>
					<a href="searchStudentAttendanceDetails" class="btn btn-primary pull-right">Get Student Attendance</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Attendance Details</h3>
						</div>
						<form role="form" action="addMarkAttendance" method="post" class="formValid">
							<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">							
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Class <span class="red">*</span></label> <input type="text"
												class="form-control className intelliClass-0-className stdClassHints"
												id="sclass" name="className" placeholder="Class Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Class ID</label> <input type="text"
											class="form-control intelliClass-0-classCode" id="classCode"
											name="classCode" readonly="readonly" />
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Section <span class="red">*</span></label> <input type="text"
												class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Section ID</label> <input type="text"
											class="form-control intelliClass-0-sectionCode" id="sectionCode"
											name="sectionCode" readonly="readonly" />
									</div>
								</div>
</c:if>								
								<div class="col-md-12">
									<div class="form-group col-md-6">
										<label>Date <span class="red">*</span></label> 
										<div class="input-group">
											<input class="form-control date-picker date" name="date" required="required" readonly="readonly" placeholder="dd-mm-yyyy" type="text">
											<div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
										</div>
									</div>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">									
									<div class="col-md-4">
										<div class="form-group">
											<label>Status <span class="red">*</span></label> 
											<select	class="form-control status" name="status" id="status">
												<option value=""><st:message code="ui.text.Select" /></option>
												<option value="A"><st:message code="ui.text.All" /></option>
												<option value="Y" selected="selected"><st:message code="ui.text.Active" /></option>
												<option value="N"><st:message code="ui.text.Inactive" /></option>
											</select>
										</div>
									</div>
</c:if>									
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
						</form>
					</div>
				</div>
				
				
					
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Attendance Details</h3>
						</div>
						<form role="form" action="editMarkAttendance" method="post" class="formValid">
							<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">							
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Class <span class="red">*</span></label> <input type="text"
												class="form-control className intelliClass-1-className stdClassHints"
												id="sclass" name="className" placeholder="Class Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Class ID</label> <input type="text"
											class="form-control intelliClass-1-classCode" id="classCode"
											name="classCode" readonly="readonly" />
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Section <span class="red">*</span></label> <input type="text"
												class="form-control sectionName intelliClass-1-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Section ID</label> <input type="text"
											class="form-control intelliClass-1-sectionCode" id="sectionCode"
											name="sectionCode" readonly="readonly" />
									</div>
								</div>
</c:if>								
								<div class="col-md-12">
									<div class="form-group col-md-6">
										<label>Date <span class="red">*</span></label> 
										<div class="input-group">
											<input class="form-control date date-picker" name="date" required="required" readonly="readonly" placeholder="dd-mm-yyyy" type="text">
											<div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
										</div>
									</div>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">									
									<div class="col-md-4">
										<div class="form-group">
											<label>Status <span class="red">*</span></label> 
											<select	class="form-control status" name="status">
												<option value=""><st:message code="ui.text.Select" /></option>
												<option value="A"><st:message code="ui.text.All" /></option>
												<option value="Y" selected="selected"><st:message code="ui.text.Active" /></option>
												<option value="N"><st:message code="ui.text.Inactive" /></option>
											</select>
										</div>
									</div>
</c:if>									
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
						</form>
					</div>
				</div>
				
			</div>
			
			
			<div class="col-md-12">	
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Download Bulk Attendance Details</h3>
						</div>
						<form role="form" action="downloadBulkMarkAttendance" method="post" class="formValid">
							<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">							
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Class <span class="red">*</span></label> <input type="text"
												class="form-control className intelliClass-2-className stdClassHints"
												id="sclass" name="className" placeholder="Class Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Class ID</label> <input type="text"
											class="form-control intelliClass-2-classCode" id="classCode"
											name="classCode" readonly="readonly" />
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Section <span class="red">*</span></label> <input type="text"
												class="form-control sectionName intelliClass-2-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
										</div>
									</div>
									<div class="col-md-3">
										<label>Section ID</label> <input type="text"
											class="form-control intelliClass-2-sectionCode" id="sectionCode"
											name="sectionCode" readonly="readonly" />
									</div>
								</div>
</c:if>								
								<div class="col-md-12">
									<div class="form-group col-md-5">
										<label>Date <span class="red">*</span></label> 
										<div class="input-group">
											<input class="form-control date date-picker" name="date" required="required" readonly="readonly" placeholder="dd-mm-yyyy" type="text">
											<div class="input-group-addon">
						                        <i class="fa fa-calendar"></i>
						                    </div>
										</div>
									</div>
									<div class="form-group col-md-4">
										<label>File Type <span class="red">*</span></label> 
										<select class="form-control fileType" name="fileType">
											<option value="">-- Select --</option>
											<option value="xls">xls</option>
											<option value="xlsx">xlsx</option>
										</select>
									</div>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">									
									<div class="col-md-3">
										<div class="form-group">
											<label>Status <span class="red">*</span></label> 
											<select	class="form-control status" name="status">
												<option value=""><st:message code="ui.text.Select" /></option>
												<option value="A"><st:message code="ui.text.All" /></option>
												<option value="Y" selected="selected"><st:message code="ui.text.Active" /></option>
												<option value="N"><st:message code="ui.text.Inactive" /></option>
											</select>
										</div>
									</div>
</c:if>									
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Download File..." />
							</div>
						</form>
					</div>
				</div>
				
				
				
				<div class="col-md-6">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Upload Bulk Attendance Details</h3>
				</div>
				<form role="form" action="uploadBulkMarkAttendance" method="post" enctype="multipart/form-data" class="formValid">
					<div class="box-body">
						<div class="col-md-12">
							<fieldset class="form-group">
								<label>Attendance File <span class="red">*</span></label>
								<div style="display: table;">
									<span class="btn btn-primary btn-file btn-xs" style="width: 120px;height: 30px;padding: 5px;">
										<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
										<input type="file" name="bulkFile" class="bulkFile" onchange="loadFile(event)" />
									</span>
									<font id="output"></font>
								</div>
							</fieldset>
						</div>
						
						<div class="col-md-12">
							<font style="font-size: 12px;" class="redFont">(upload only .xls and .xlsx)</font>
						</div>
					</div>
					<!-- /.box-body -->
	
					<div class="box-footer">
						<input type="submit" class="btn btn-primary bulkFileBtn" value="Upload File..." />
					</div>
				</form>
			</div>
		</div>
			</div>
		</div>
		
	</section>
</div>

<script>
$(document).ready(function(){

	initializeHints();
	
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
var loadFile = function(event) {
	console.log(event.target.files[0].name);
	$('#output').html(event.target.files[0].name);
   	//var output = document.getElementById('output');
   	//output.src = URL.createObjectURL(event.target.files[0]);
};
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>