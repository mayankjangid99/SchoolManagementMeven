<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Payment Category<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Search Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addPaymentCategory" class="btn btn-primary pull-right">Add Payment Category</a>
				</div>
			</div>
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="box box-primary">
								<div class="box-header">
									<h3 class="box-title">Payment Category</h3>
								</div>
								<form role="form" action="resultPaymentCategory" method="post" class="formValidd">
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
														<label>Payment Category</label> 
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
										<input type="submit" class="btn btn-primary" value="Submit"/>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
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