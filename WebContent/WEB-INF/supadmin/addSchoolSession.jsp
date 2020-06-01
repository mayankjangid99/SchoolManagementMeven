<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add School Session<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 400px;">
			<div class="box-header">
				<h3 class="box-title">Add Session Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchSchoolSession" class="btn btn-primary pull-right">Search School Session</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Session Details</h3>
						</div>
						<form role="form" action="saveSessionDetails" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Session Name <span class="red">*</span></label> 
											<input type="text"
												class="form-control sessionName" placeholder="Session Name"
												id="sessionNameAdd" name="sessionName" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Session Code <span class="red">*</span></label> <input type="text"
											class="form-control sessionCode" id="sessionCodeAdd"
											name="sessionCode" placeholder="Code" />
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input class="btn btn-primary" value="Add" type="submit" /> &nbsp;&nbsp;
								<input type="button" class="btn btn-primary formValidBtn" onClick="navTo('Edit')" value="Edit"/> &nbsp;&nbsp;
								<input type="button" class="btn btn-primary formValidBtn" onClick="navTo('Delete')" value="Delete" />
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Allocate Session Details</h3>
						</div>
						<form role="form" action="saveAllocateSchoolSession" method="post" class="formm">
							<div class="box-body">
								<%-- <div class="col-md-12">
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
										<div class="col-md-8">
											<div class="form-group">
												<label>Session Name <span class="red">*</span></label>
<c:if test="${fn:length(SessionDetails) eq 0 }">
												<br><label>No session available</label>
</c:if> 												
<c:if test="${fn:length(SessionDetails) gt 0 }">
 												<select class="form-control" name="sessionDetails.sessionCode">
<c:forEach var="SessionDetail" items="${SessionDetails }">
													<option value="${SessionDetail.sessionCode }">${SessionDetail.sessionName }</option>
</c:forEach>
												</select>
</c:if>
											</div>
										</div>
										<!-- <div class="col-md-4">
											<label>Session Code</label> <input type="text"
												class="form-control" id="sessionCode"
												name="sessionCode" placeholder="Code" />
										</div> -->
								</div>
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Status <span class="red">*</span></label> 
													<select name="status" class="form-control">
														<option value="P"> Previous </option>
														<option value="C"> Current </option>
													</select>
											</div>
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
<c:if test="${fn:length(SessionDetails) eq 0 }">
								<input type="submit" class="btn btn-primary" value="Submit" onClick="validateStatus()" disabled="disabled" />
</c:if> 												
<c:if test="${fn:length(SessionDetails) gt 0 }">
 								<input type="submit" class="btn btn-primary allocateSbmt" value="Submit" onClick="validateStatus()" />
</c:if>
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

function navTo(action)
{
	if(!$('#sessionNameAdd').val() || !$('#sessionCodeAdd').val()){
		return false;
	}
	if(action == 'Edit')
	{
		sendRequest('editSessionDetails', 'post', {"sessionCode": $('[name="sessionCode"]').val()} );
		return false;
	}
	else
	{
		sendRequest('deleteSessionDetails', 'post', {"sessionCode": $('[name="sessionCode"]').val()} );
		return false;
	}
}

$(".allocateSbmt").on("click", function(e){
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