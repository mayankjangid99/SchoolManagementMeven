<%-- <%@ include file="../common/header.jsp"%> --%>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Salary Structure <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Salary Structure Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchMicroServiceConfig" class="btn btn-primary pull-right">Search Salary Structure</a>
				</div>
			</div>
			<form role="form" action="saveSalaryStructure" method="post" class="globalConfig formValid">
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="form-group">
								<label>Fullname <span class="red">*</span></label> 
								<input type="text" class="form-control configValue intelliUserDetails-0-fullname userDetailsAutoComplete" name="fullname" placeholder="Fullname"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Username <span class="red">*</span></label> 
								<input type="text" class="form-control configValue intelliUserDetails-0-username userDetailsAutoComplete" name="username" placeholder="Username" readonly="readonly"/>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="form-group">
								<label>Email</label> 
								<input type="text" class="form-control configValue intelliUserDetails-0-email userDetailsAutoComplete" name="email" placeholder="Email" readonly="readonly"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label>Mobile</label> 
								<input type="text" class="form-control configValue intelliUserDetails-0-mobile userDetailsAutoComplete" name="mobile" placeholder="Mobile" readonly="readonly"/>
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

initializeUserDetailsAutoComplete();
// 	$('.attnDate').datepicker({format: 'dd-mm'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>