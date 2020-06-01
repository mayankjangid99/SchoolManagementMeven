<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Transport<!--  List <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Transport Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchTransport" class="btn btn-primary pull-right">Search Transport</a>
				</div>
			</div>
			
			<form role="form" action="saveTransport" method="post" class="formValid">
				<!-- Hidden Variables -->
				<div class="hidden">
				</div>
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Route <span class="red">*</span></label> 
								<input type="text" class="form-control route" placeholder="Route" name="route" maxlength="50" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Pick Up Stop <span class="red">*</span></label> 
								<input type="text" class="form-control pickUpStop" name="pickUpStop" placeholder="Pick Up Stop" maxlength="50" />
							</div>
						</div>						
						<div class="col-md-4">
							<div class="form-group">
								<label>Pick Up Time <span class="red">*</span></label> 
								<input type="text" class="form-control pickUpTime" name="pickUpTime" placeholder="Pick Up Time" maxlength="10" />
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Vehicle <span class="red">*</span></label> 
								<input type="text" class="form-control vehicle" placeholder="Vehicle" name="vehicle" maxlength="20" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Drop Stop <span class="red">*</span></label> 
								<input type="text" class="form-control dropStop" name="dropStop" placeholder="Drop Stop" maxlength="50" />
							</div>
						</div>					
						<div class="col-md-4">
							<div class="form-group">
								<label>Drop Time <span class="red">*</span></label> 
								<input type="text" class="form-control dropTime" name="dropTime" placeholder="Drop Time" maxlength="10" />
							</div>
						</div>
					</div>
					
						
					<div class="col-md-12">				
						<div class="col-md-4">
							<div class="form-group">
								<label>Transport Fee <span class="red">*</span></label> 
								<input type="text" class="form-control transportFee" name="transportFee" placeholder="Transport Fee" maxlength="7" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Type <span class="red">*</span></label> 
								<select name="type" class="form-control type" id="type">
									<option value=""> -- Select -- </option>
									<option value="Y">Yearly</option>
<!-- 								<option value="H">Half-Yearly</option> -->
									<option value="M">Monthly</option>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Status <span class="red">*</span></label> 
								<select	class="form-control status" name="status">
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
					<input type="submit" class="btn btn-primary saveReturn" value="Submit & Return" />
					<input type="submit" class="btn bg-navy cancel" value="Cancel" />
				</div>
			</form>
			
		</div>
	</section>
</div>

<script>
$(document).ready(function(){

	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>