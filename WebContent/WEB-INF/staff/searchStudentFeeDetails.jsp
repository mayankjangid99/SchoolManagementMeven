<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />

<div class="content-wrapper" style="min-height: 1266px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Student Fee Details <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="min-height: 1100px;">
			<div class="box-header">
				<!-- <h3 class="box-title">Fee Details</h3> -->
				<div class="col-md-6 pull-right">
					<!-- <a href="addStudentFeeDetails" class="btn btn-primary pull-right">Add Student Fee</a> -->
				</div>
			</div>
			<div class="col-md-12">
				<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Search Class Fee Details</h3>
						</div>
					<form role="form" action="resultClassFeeDetails" method="post" class="formValid">
						<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Class <span class="red">*</span></label> <input type="text"
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
										<label>Section <span class="red">*</span></label> <input type="text"
											class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID</label> <input type="text"
										class="form-control intelliClass-0-sectionCode" id="sectionCode"
										name="sectionCode" readonly="readonly" />
								</div>
							</div>
							

</c:if>							
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Type <span class="red">*</span></label> 
										<select	class="form-control type" name="type">
											<option value="M"><st:message code="ui.text.Monthly" /></option>
											<option value="Y"><st:message code="ui.text.Yearly" /></option>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Month <span class="red">*</span></label> 
										<input class="form-control valid month" name="month" required="required" readonly="readonly" placeholder="mm" type="text">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Submitted/No Submitted <span class="red">*</span></label> 
										<select	class="form-control resultTypee" name="resultType">
											<option value="">-- Select --</option>
											<option value="Y">Submitted</option>
											<option value="N">No Submitted</option>
										</select>
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
			</div>
			
			
			
			<div class="col-md-12">
				<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Search Student Fee Details</h3>
						</div>
					<div class="box-body">
						<form role="form" action="resultStudentFeeDetails" method="post" class="formValid">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">						
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Class <span class="red">*</span></label> <input type="text"
											class="form-control className intelliClass-1-className stdClassHints" placeholder="Class Name"
											id="sclass" name="className" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Class ID</label> <input type="text"
										class="form-control intelliClass-1-classCode" id="classCode"
										name="classCode" readonly="readonly" />
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label>Section <span class="red">*</span></label> <input type="text"
											class="form-control sectionName intelliClass-1-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID</label> <input type="text"
										class="form-control intelliClass-1-sectionCode" id="sectionCode"
										name="sectionCode" readonly="readonly" />
								</div>
							</div>
							
</c:if>							
							<div class="col-md-12 form-group">
								<div class="col-md-4">
									<div class="form-group">
										<label>Student Full Name <span class="red">*</span></label> <input type="text"
										class="form-control studentDetailsAutoComplete  intelliDetails-0-fullName" placeholder="Student Full Name"
										id="" name="" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Admission No <span class="red">*</span></label> 
										<input type="text"class="form-control admissionNo intelliDetails-0-admissionNo" name="admissionNo" placeholder="Admission No" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Roll No <span class="red">*</span></label> 
										<input type="text"class="form-control rollNo intelliDetails-0-rollNo" name="rollNo" placeholder="Roll No" />
									</div>
								</div>
							</div>
							
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
						</form>
						
						
						<form role="form" action="resultStudentFeeDetails" method="post" class="formValid">
							<div class="col-md-12 form-group" style="border-top: 3px solid #3c8dbc;"></div>
							
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Class <span class="red">*</span></label> <input type="text"
											class="form-control className intelliClass-3-className stdClassHints" placeholder="Class Name"
											id="sclass" name="className" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Class ID</label> <input type="text"
										class="form-control intelliClass-3-classCode" id="classCode"
										name="classCode" readonly="readonly" />
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label>Section <span class="red">*</span></label> <input type="text"
											class="form-control sectionName intelliClass-3-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID</label> <input type="text"
										class="form-control intelliClass-3-sectionCode" id="sectionCode"
										name="sectionCode" readonly="readonly" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Receipt No.</label> 
										<input type="text" name="receiptNo" class="form-control receiptNo" placeholder="Receipt No" />
									</div>
								</div>
							</div>
							
		
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
						</form>
						
					</div>
					<!-- /.box-body -->
				</div>
			</div>

<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">			
			<div class="col-md-12">
				<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Student Fee Details</h3>
						</div>
					<form role="form" action="addStudentFeeDetails" method="post" class="formValid">
						<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">						
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Class <span class="red">*</span></label> <input type="text"
											class="form-control className intelliClass-2-className stdClassHints" placeholder="Class Name"
											id="sclass" name="className" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Class ID</label> <input type="text"
										class="form-control intelliClass-2-classCode" id="classCode"
										name="classCode" readonly="readonly" />
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label>Section <span class="red">*</span></label> <input type="text"
											class="form-control sectionName intelliClass-2-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID</label> <input type="text"
										class="form-control intelliClass-2-sectionCode" id="sectionCode"
										name="sectionCode" readonly="readonly" />
								</div>
							</div>
							
</c:if>							
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Student Full Name <span class="red">*</span></label> <input type="text"
										class="form-control studentDetailsAutoComplete  intelliDetails-1-fullName" placeholder="Student Full Name"
										id="" name="" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Admission No <span class="red">*</span></label> 
										<input type="text"class="form-control admissionNo intelliDetails-1-admissionNo" name="admissionNo" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Roll No <span class="red">*</span></label> 
										<input type="text"class="form-control rollNo intelliDetails-1-rollNo" name="rollNo" />
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
			</div>
</c:if>			
			
			
		</div>
	</section>
</div>

<script type="text/javascript">
$(document).ready(function(){

	initializeHints();
	$(".sectionName").on('focusout', function(e){
		var classCode = $(this).closest('form').find('[name="classCode"]').val();
		var sectionCode = $(this).closest('form').find('[name="sectionCode"]').val();
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	});
	var classCode = '${userProfile.classCode}';
	var sectionCode = '${userProfile.sectionCode}';
	if(classCode && sectionCode)
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	
	$('[name="month"]').datepicker({format: 'mm', 
		viewMode: 'months', minViewMode: 'months'});
// $('input, select, textarea').attr('disabled', 'disabled');
	$('[name="type"]').on('change', function(){
		var typeVal = $('[name="type"]').val();
		if(typeVal == 'Y'){
			$('[name="month"]').attr('disabled','disabled');
			$('[name="month"]').closest('.col-md-4').hide();
		}else{
			$('[name="month"]').removeAttr('disabled');
			$('[name="month"]').closest('.col-md-4').show();
		}
	});
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>