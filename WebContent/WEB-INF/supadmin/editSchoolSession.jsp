<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit School Session<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 400px;">
			<div class="box-header">
				<h3 class="box-title">Edit Session Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addSchoolSession" class="btn btn-primary pull-right">Add School Session</a>
					<a href="searchSchoolSession" class="btn btn-primary pull-right">Search School Session</a>
				</div>
			</div>
			
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Session Details</h3>
						</div>
						<form role="form" action="updateSessionDetails" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
											<div class="form-group">
												<label>Session Name <span class="red">*</span></label> 
												<input type="text"
													class="form-control sessionName" placeholder="Session Name"
													id="sclass" name="sessionName" value="${SessionDetails.sessionName }" />
											</div>
										</div>
										<div class="col-md-4">
											<label>Session Code <span class="red">*</span></label> <input type="text"
												class="form-control sessionCode" id="sessionCode"
												name="sessionCode" placeholder="Code" value="${SessionDetails.sessionCode }" readonly="readonly" />
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input class="btn btn-primary" value="Update" type="submit">
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Allocate Session Details</h3>
						</div>
						<form role="form" action="updateAllocatedSchoolSession" method="post" class="formValid">
							<input type="hidden" name="sequence" value="${SchoolSession.sequence }" />
							<div class="box-body">
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Session Name <span class="red">*</span></label> 
												<input type="text"
													class="form-control sessionName" placeholder="Session Name"
													id="sclass" name="sessionDetails.sessionName" value="${SchoolSession.sessionDetails.sessionName }" readonly="readonly" />
											</div>
										</div>
										<div class="col-md-4">
											<label>Session Code <span class="red">*</span></label> <input type="text"
												class="form-control sessionCode" id="sessionCode"
												name="sessionDetails.sessionCode" readonly="readonly" value="${SchoolSession.sessionDetails.sessionCode }" placeholder="Code" />
										</div>
								</div>
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Status <span class="red">*</span></label> 
													<select name="status" class="form-control status">
														<option value="P" <c:if test="${SchoolSession.status eq 'P' }"> selected="selected"</c:if> > Previous </option>
														<option value="C" <c:if test="${SchoolSession.status eq 'C' }"> selected="selected"</c:if> > Current </option>
													</select>
											</div>
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" onClick="validateStatus()" />
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
	//initializeHints();
	//$('input, select, textarea').attr('disabled', 'disabled');	
	
});
$("input[type=submit]").on("click", function(e){
	var statusVal = $("[name=status] option:selected").val();
	if(statusVal == "C")
	{
		var conf = confirm("Are you sure to save this 'School Session' as a current session");
		if(!conf)
			e.preventDefault();
	}	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>