<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Transport<!--  List <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Transport Details</h3>
<c:if test="${userProfile.getUserRoleId() eq 'ROLE_SUPADMIN' or userProfile.getUserRoleId() eq 'ROLE_ADMIN' or userProfile.getUserRoleId() eq 'ROLE_SUBADMIN'}">
				<div class="col-md-6 pull-right">
					<a href="addTransport" class="btn btn-primary pull-right">Add Transport</a>
				</div>
</c:if>
			</div>
			<form role="form" action="resultTransport" method="post" class="form">
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Route </label> 
								<input type="text" class="form-control" placeholder="Route" name="Route" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Pick Up Stop </label> 
								<input type="text" class="form-control" name="pickUpStop" placeholder="Pick Up Stop" />
							</div>
						</div>						
						<div class="col-md-4">
							<div class="form-group">
								<label>Pick Up Time </label> 
								<input type="text" class="form-control" name="pickUpTime" placeholder="Pick Up Time" />
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Vehicle </label> 
								<input type="text" class="form-control" placeholder="Vehicle" name="vehicle" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Drop Stop </label> 
								<input type="text" class="form-control" name="dropStop" placeholder="Drop Stop" />
							</div>
						</div>					
						<div class="col-md-4">
							<div class="form-group">
								<label>Drop Time </label> 
								<input type="text" class="form-control" name="dropTime" placeholder="Drop Time" />
							</div>
						</div>
					</div>
					
						
					<div class="col-md-12">				
						<div class="col-md-4">
							<div class="form-group">
								<label>Transport Fee </label> 
								<input type="text" class="form-control" name="transport Fee" placeholder="Transport Fee" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Type </label> 
								<select name="type" class="form-control" id="type">
									<option value=""> -- <st:message code="ui.text.Select" /> -- </option>
									<option value="Y">Yearly</option>
<!-- 								<option value="H">Half-Yearly</option> -->
									<option value="M">Monthly</option>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Status </label> 
								<select	class="form-control" name="status">
									<option value=""> -- <st:message code="ui.text.Select" /> -- </option>
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