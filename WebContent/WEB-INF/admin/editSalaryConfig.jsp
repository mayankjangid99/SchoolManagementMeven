<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit Salary Configuration 
			<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Salary Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addSalaryConfig" class="btn btn-primary pull-right">Add Salary Config</a>
					<a href="searchSalaryConfig" class="btn btn-primary pull-right">Search Salary Config</a>
				</div>
			</div>
			<form role="form" action="updateSalaryConfig" method="post" class="SalaryConfig formValid" id="">
				<div class="box-body">
					
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Type <span class="red">*</span></label> 
									<select name="type" class="form-control type">
										<option value="">-Select-</option>
										<option value="E">Earnings</option>
										<option value="D">Declarations</option>
									</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Config Code<span class="red">*</span></label> 
								<input type="text" class="form-control configCode" name="configCode" placeholder="Config Code" value="${salaryConfig.configCode }"/>
							</div>
						</div>
					</div>
					<div class="col-md-12"></div>
					
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Config Name<span class="red">*</span></label> 
								<input type="text" class="form-control configName" name="configName" placeholder="Config Name" value="${salaryConfig.configName }"/>
							</div>
						</div>
					</div>
					<div class="col-md-12"></div>
					
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
	<c:if test="${Operation eq 'View'}">
		$('input, select, textarea').attr('disabled', 'disabled');
	</c:if>	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>