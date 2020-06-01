<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Salary Config <!-- <small>it all starts here</small> -->
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
				</div>
			</div>
			<form role="form" action="resultSalaryConfig" method="post" class="form">
				<div class="box-body">
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Config Code</label> 
								<input type="text" class="form-control" name="configCode" placeholder="Config Code"/>
							</div>
						</div>
					</div>
					<div class="col-md-12"></div>
					
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Config Name</label> 
								<input type="text" class="form-control" name="configName" placeholder="Config Name"/>
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
	//initializeHints();
		
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>