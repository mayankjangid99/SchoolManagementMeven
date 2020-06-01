<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Unique Id
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Unique Id Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addUniqueId" class="btn btn-primary pull-right">Add Unique Id</a>
				</div>
			</div>
			<form role="form" action="resultUniqueId" method="post" class="formm">
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="form-group">
								<label>Unique Id </label> 
								<input type="text" class="form-control" name="templateId" placeholder="Unique Id" />
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="form-group">
								<label>Unique Name</label>
								<input type="text" class="form-control" name="templateName" placeholder="Unique Name" />
							</div>
						</div>
						<div class="col-md-6"></div>
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