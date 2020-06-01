<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Student Attendance<!--  <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Student Details</h3>
			</div>
			<form role="form" action="resultStudentAttendanceDetails" method="post" class="formValid" id="searchStudentAtteDetails">
				<div class="box-body">
					<div class="col-md-12">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">
						<div class="col-md-3">
							<div class="form-group">
								<label>Class <span class="red">*</span></label> 
								<input type="text" class="form-control className intelliClass-0-className stdClassHints" name="className" placeholder="Class Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> 
							<input type="text" class="form-control intelliClass-0-classCode" id="classCode" name="classCode" readonly="readonly" />
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Section <span class="red">*</span></label> 
								<input type="text" class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> 
							<input type="text" class="form-control intelliClass-0-sectionCode" id="sectionCode" name="sectionCode" readonly="readonly" />
						</div>
</c:if>
						<div class="col-md-2">
							<div class="form-group">
								<label>Date <span class="red">*</span></label> 
								<div class="input-group">
									<input type="text" class="form-control date attnDate" name="date" required="required" readonly="readonly" placeholder="dd-mm-yyyy" />
									<div class="input-group-addon" style="height: 34px;">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                </div>
							</div>
						</div>
					</div>

<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN' or userProfile.getUserRoleId() eq 'ROLE_STAFF'}">					
					<div class="col-md-12">
						<div class="col-md-3">
							<div class="form-group">
								<label>Student Full Name <span class="red">*</span></label> <input type="text"
								class="form-control studentDetailsAutoComplete intelliDetails-0-fullName" placeholder="Student Full Name"
								id="" name="" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Admission No <span class="red">*</span></label> <input
									type='text' class="form-control admissionNo intelliDetails-0-admissionNo" name="admissionNo" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Roll No <span class="red">*</span></label> <input
									type='text' class="form-control rollNo intelliDetails-0-rollNo" name="rollNo" />
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label>Status <span class="red">*</span></label> 
								<select	class="form-control status" name="status">
									<option value="A"><st:message code="ui.text.All" /></option>
									<option value="Y" selected="selected"><st:message code="ui.text.Active" /></option>
									<option value="N"><st:message code="ui.text.Inactive" /></option>
								</select>
							</div>
						</div>
					</div>
</c:if>
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
		var classCode = $(this).closest('form').find('[name="classCode"]').val();
		var sectionCode = $(this).closest('form').find('[name="sectionCode"]').val();
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	});
	var classCode = '${userProfile.classCode}';
	var sectionCode = '${userProfile.sectionCode}';
	var userRole = '${userProfile.userRoleId}';
	if(classCode && sectionCode && userRole != 'ROLE_USER')
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	$('.attnDate').datepicker({format: 'dd-mm-yyyy', 
		viewMode: 'months', minViewMode: 'months'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>