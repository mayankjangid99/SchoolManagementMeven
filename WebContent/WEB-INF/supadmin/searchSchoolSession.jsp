<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search School Session<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Search Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addSchoolSession" class="btn btn-primary pull-right">Add School Session</a>
				</div>
			</div>
			<form role="form" action="resultSchoolSession" method="post" class="formm">
				<div class="box-body">
					
<%-- 					<div class="col-md-12">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<label>School Name </label> 
									<select class="form-control" name="schoolCode">
										<option value="">-- Select --</option>
	<c:forEach var="SchoolProfile" items="${SchoolProfiles }"><option value="${SchoolProfile.schoolCode }"> ${SchoolProfile.name } </option></c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div> --%>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>School Session </label> 
										<select name="sessionCode" class="form-control">
											<option value="">-- All --</option>
<c:forEach var="schoolSession" items="${sessionScope.SessionManager.schoolProfileUtils.schoolSessions }"><option value="${schoolSession.sessionDetails.sessionCode }">${schoolSession.sessionDetails.sessionName }</option></c:forEach>
										</select>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>Status</label> 
										<select name="status" class="form-control">
											<option value="">-- All --</option>
											<option value="C"> Current </option>
											<option value="P"> Previous </option>
										</select>
								</div>
							</div>
							<!-- <div class="col-md-3">
								<label>Section ID</label> <input type="text"
									class="form-control intelliClass-0-sectionCode" id="sectionCode"
									name="sectionCode" readonly="readonly" />
							</div> -->
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
	//$('input, select, textarea').attr('disabled', 'disabled');	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>