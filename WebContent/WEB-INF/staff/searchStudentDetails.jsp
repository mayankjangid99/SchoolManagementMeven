<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Student<!--  List <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Student Details</h3>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">
				<div class="col-md-6 pull-right">
					<a href="addStudentDetails" class="btn btn-primary pull-right">Add Student Details</a>
				</div>
</c:if>
			</div>
			<form role="form" action="resultStudentDetails" method="post" class="form">
				<div class="box-body">
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Class <span class="red">*</span></label> <input type="text"
									class="form-control intelliClass-0-className stdClassHints" placeholder="Class Name"
									id="sclass" name="className" />
							</div>
						</div>
						<div class="col-md-3">
							<label>Class ID</label> <input type="text"
								class="form-control intelliClass-0-classCode" id="classCode"
								name="classCode" readonly="readonly" />
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Section <span class="red">*</span></label> <input type="text"
									class="form-control intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
							</div>
						</div>
						<div class="col-md-3">
							<label>Section ID</label> <input type="text"
								class="form-control intelliClass-0-sectionCode" id="sectionCode"
								name="sectionCode" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Status <span class="red">*</span></label> 
								<select	class="form-control" name="status">
									<option value="A" selected="selected"><st:message code="ui.text.All" /></option>
									<option value="Y"><st:message code="ui.text.Active" /></option>
									<option value="N"><st:message code="ui.text.Inactive" /></option>
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
	</section>
</div>

<script>
$(document).ready(function(){

	initializeHints();
	
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>