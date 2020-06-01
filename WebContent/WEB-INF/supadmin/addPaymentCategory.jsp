<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Payment Category<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 600px;">
			<div class="box-header">
				<h3 class="box-title">Add Payment Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchPaymentCategory" class="btn btn-primary pull-right">Search Payment Category</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Payment Category</h3>
						</div>
						<form role="form" action="savePaymentCategory" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
											<div class="form-group">
												<label>Payment Category Name <span class="red">*</span></label> 
												<input type="text"
													class="form-control paymentCategoryName" placeholder="Payment Name"
													id="sclass" name="paymentCategoryName" />
											</div>
									</div>
								</div>
								
								<div class="col-md-12">
										<div class="col-md-8">
											<label>Payment Category Code <span class="red">*</span></label> <input type="text"
												class="form-control paymentCategoryCode" id="sessionCode"
												name="paymentCategoryCode" placeholder="Payment Code" />
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input class="btn btn-primary" value="Add" type="submit">
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit/Delete Payment Category</h3>
						</div>
						<form role="form" action="#" method="post" class="formValid">
							<div class="box-body">
<%-- 								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Session Name </label>
<c:if test="${fn:length(SessionDetails) eq 0 }">
												<br><label>No session available</label>
</c:if> 												
<c:if test="${fn:length(SessionDetails) gt 0 }">
 												<select class="form-control" name="sessionDetails.sessionCode" id="sessionCode">
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
								</div> --%>
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Payment Category<span class="red">*</span></label> 
													<select name="paymentCategoryCode" id="allPayCate" class="form-control paymentCategoryCode">
														<option value=""> -- Select -- </option>
<c:forEach var="payCategory" items="${All }">
														<option value="${payCategory.paymentCategoryCode }"> ${payCategory.paymentCategoryName } </option>												
</c:forEach>														
													</select>
											</div>
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="button" class="btn btn-primary formValidBtn" onclick="navTo('Edit')" value="Edit" /> &nbsp;&nbsp;
								<input type="button" class="btn btn-primary formValidBtn" onclick="navTo('Delete')" value="Delete" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Allocate Payment Category</h3>
						</div>
						<form role="form" action="saveAllocatePaymentCategory" method="post" class="formm">
							<div class="box-body">
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Not Allocated Payment Category<span class="red">*</span></label> 
<c:if test="${not empty NotAllocated }">
													<select name="paymentCategoryCode" class="form-control">
<c:forEach var="payCategory" items="${NotAllocated }">
														<option value="${payCategory.paymentCategoryCode }"> ${payCategory.paymentCategoryName } </option>												
</c:forEach>		
													</select>
</c:if>	
<c:if test="${empty NotAllocated }">
														<label>No Payment Category Found</label>
</c:if>											
											</div>
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
 								<input type="submit" class="btn btn-primary allocateSbmt" value="Submit" />
							</div>
						</form>
					</div>
				</div>
				
				<%-- <div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Remove Allocate Payment Category</h3>
						</div>
						<form role="form" action="deleteAllocatePaymentCategory" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
										<div class="col-md-8">
											<div class="form-group">
												<label>Allocated Payment Category<span class="red">*</span></label> 
													<select name="paymentCategoryCode" class="form-control paymentCategoryCode">
<c:forEach var="payCategory" items="${Allocated }">
														<option value="${payCategory.paymentCategoryCode }"> ${payCategory.paymentCategoryName } </option>												
</c:forEach>														
													</select>
											</div>
										</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
 								<input type="submit" class="btn btn-primary allocateSbmt" value="Submit" />
							</div>
						</form>
					</div>
				</div> --%>
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
	if(!$('#allPayCate').val()){
		return false;
	}
	if(action == 'Edit')
	{
		sendRequest('editPaymentCategory', 'post', {"paymentCategoryCode": $('#allPayCate').val()} );
		return false;
	}
	else
	{
		sendRequest('deletePaymentCategory', 'post', {"paymentCategoryCode": $('#allPayCate').val()} );
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