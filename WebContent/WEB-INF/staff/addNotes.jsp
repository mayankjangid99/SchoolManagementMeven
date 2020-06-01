<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Notes <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Notes Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchNotes" class="btn btn-primary pull-right">Search Notes</a>
				</div>
			</div>
			<form role="form" action="resultAttendanceDetails" method="post" class="formValid">
				<div class="box-body">
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Class <span class="red">*</span></label> <input type="text"
									class="form-control className intelliClass-0-className stdClassHints"
									id="sclass" name="className" placeholder="Class Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> <input type="text"
								class="form-control intelliClass-0-classCode" id="classCode"
								name="classCode" readonly="readonly" />
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="col-md-6">
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
					<div class="col-sm-12">
						<div class="col-md-3">
							<div class="form-group">
								<label>Date <span class="red">*</span></label> 
								<div class="input-group">
									<input type='text' class="form-control date attnDate" name="date" required="required" readonly="readonly" placeholder="dd-mm" />
									<div class="input-group-addon">
				                        <i class="fa fa-calendar"></i>
				                    </div>
								</div>
							</div>
						</div>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">						
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
</c:if>
						<br>
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
// 	$('.attnDate').datepicker({format: 'dd-mm'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>