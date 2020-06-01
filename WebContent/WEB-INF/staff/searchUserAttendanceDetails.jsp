<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search User Attendance <small>it all starts here</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">User Details</h3>
			</div>
			<form role="form" action="resultUserAttendanceDetails" method="post" class="formm" id="searchUserAtteDetails">
				<div class="box-body">					
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Date <span class="red">*</span></label> 
								<div class="input-group">
									<input type="text" class="form-control attnDate" name="date" required="required" readonly="readonly" placeholder="dd-mm-yyyy" />
									<div class="input-group-addon" style="height: 34px;">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                </div>
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
	$('.attnDate').datepicker({format: 'dd-mm-yyyy', 
		viewMode: 'months', minViewMode: 'months'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>