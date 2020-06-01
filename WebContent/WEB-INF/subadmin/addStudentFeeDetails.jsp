<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="appModalBody">
	<div class="modal fade app-modal-primary" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="saveStudentFeeDetails" method="post" id="saveStudentFeeDetails" class="formValid">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">Add Fee</h4>
				</div>
				<div class="modal-body">
					<div style="color: green; font-size: 15px; margin: 10px;"
						class="msg">&nbsp;</div>
					<section class="col-md-12 monthlyDiv" style="margin-top: 10px;">
						<div class="col-md-2"></div>
						<div class="col-md-8" style="display: none !important;">
							<div class="form-group">
<c:if test="${empty SchoolPaymentCategories }">
									<input type="text" name="paymentCategoryCode" value="CASH" />									
</c:if>

<c:forEach var="admission" items="${StudentDetails.admissionDetailses }" begin="0" end="0">
<c:set var="admissionDetails" value="${admission }"/>
</c:forEach>
								<input type='text' class="form-control" readonly="readonly" name="classCode" value="${admissionDetails.getClassInformation().getClassCode() }" />
								<input type='text' class="form-control" readonly="readonly" name="sectionCode" value="${admissionDetails.getSectionInformation().getSectionCode() }" />
								<input type='text' class="form-control" readonly="readonly" name="admissionNo" value="${StudentDetails.getAdmissionNo() }" />
								<input type='text' class="form-control" readonly="readonly" name="rollNo" value="${admissionDetails.getRollNo() }" />
								<input type='text' class="form-control" readonly="readonly" name="monthName" value="" />
								<input type='text' class="form-control" readonly="readonly" name="month" value="" />
								<input type='text' class="form-control" readonly="readonly" name="feeCategoryCode" value="" />
								<input type='text' class="form-control" readonly="readonly" name="type" value="" />
								<input type='text' class="form-control" readonly="readonly" name="typeName" value="" />
							</div>
						</div>
					</section>
<c:if test="${not empty SchoolPaymentCategories }">
					<div class="col-md-12">
						<div class="col-md-4"><label>Payment Category <span class="red">*</span></label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<select name="paymentCategoryCode" class="form-control paymentCategory">
								<option value="">-Select-</option>
								<c:forEach var="paymentCategory" items="${SchoolPaymentCategories }">
									<option value="${paymentCategory.paymentCategory.paymentCategoryCode }">${paymentCategory.paymentCategory.paymentCategoryName }</option>
								</c:forEach>
								</select>
							</div>
						</div>
					</div>
</c:if>
					<div class="col-md-12">
						<div class="col-md-4"><label>Total Amount</label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
							<input name="totalAmount" class="form-control" placeholder="Total Amount" type="text" readonly="readonly">
								<span class="glyphicons glyphicons-money form-control-feedback"></span>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4"><label>Amount Due</label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<input name="amountDue" class="form-control" placeholder="Amount Due" type="text" readonly="readonly">
								<span class="glyphicons glyphicons-money form-control-feedback"></span>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4"><label>Paid Amount <span class="red">*</span></label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<input name="amountPaid" class="form-control amountPaid" placeholder="Paid Amount"
									type="text"> <span
									class="glyphicon glyphicon form-control-feedback"></span>
							</div>
						</div>
					</div>
					
					<div class="col-md-12 bankDetails hidden">
						<div class="col-md-4"><label>Bank Branch <span class="red">*</span></label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<input name="bankBranch" class="form-control" placeholder="Bank Branch" type="text">
							</div>
						</div>
					</div>
					<div class="col-md-12 bankDetails hidden">
						<div class="col-md-4"><label>Bank Name <span class="red">*</span></label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<input name="bankName" class="form-control" placeholder="Bank Name" type="text">
							</div>
						</div>
					</div>
					<div class="col-md-12 bankDetails hidden">
						<div class="col-md-4"><label>Cheque No. <span class="red">*</span></label></div>
						<div class="col-md-8">
							<div class="form-group has-feedback">
								<input name="chequeNo" class="form-control" placeholder="Cheque No" type="text">
							</div>
						</div>
					</div>
					<!-- <span style="font-size: 12px; color: grey;">Note: System
						will send a mail on registered email.</span> -->
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="Save" />
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Student Fee Details <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Student Fee Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchStudentFeeDetails" class="btn btn-primary pull-right">Search Student Fee</a>
				</div>
			</div>
			<form role="form" action="#" method="post" class="form">
				<div class="box-body">
					
					<div class="col-md-12">
						<div class="col-md-1">
							<img src="${pageContext.request.contextPath}/upload/student_images/${StudentDetails.getImage() }" height="80px;" width="80px;" style="border-radius: 10%;" class="bgprofile"/>
						</div>
						<div class="col-md-11">
							<div class="col-md-12 form-group">
								<div class="col-md-3"><b style="margin-left: 10px;">Name: </b> ${StudentDetails.getFullname() } </div>
								<div class="col-md-3"><b style="margin-left: 12%;">Father: </b> ${StudentDetails.getParentDetails().getFatherName() }</div>
								<div class="col-md-3"><b style="margin-left: 12%;">Mother: </b> ${StudentDetails.getParentDetails().getMotherName() }</div>
								<div class="col-md-3"><b style="margin-left: 12%;">Date of Birth: </b><fmt:formatDate pattern="dd-MMM-yyyy" value="${StudentDetails.getDateOfBirth() }" /> </div><br>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-3"><b style="margin-left: 10px;">Roll No:</b> ${admissionDetails.getRollNo() }</div>
								<div class="col-md-3"><b style="margin-left: 12%;">Admission:</b> ${StudentDetails.getAdmissionNo() }</div>
			<%-- 					<b>DOB:</b> ${StudentDetails.getDob() }, --%>
								<div class="col-md-3"><b style="margin-left: 12%;">Class:</b> ${admissionDetails.getClassInformation().getClassName() }</div>
								<div class="col-md-3"><b style="margin-left: 12%;">Section:</b> ${admissionDetails.getSectionInformation().getSectionName() }</div>
							</div>
						</div>
					</div>
				</div>			

<c:if test="${not empty FeeCategoriesYearly }">
				<div class="col-md-12">		
					<div class="box box-primary">
						<div class="box-header">
							<div class="col-md-3">
								<h3 class="box-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" class="collapsed">
										<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="monthlyTitle">Yearly</span>
									</a>
								</h3>
							</div>
							<div class="col-md-3">
								<h3 class="box-title receipt-title">Receipt No.</h3>
							</div>
							<div class="col-md-3">&nbsp;
							</div>
							<div class="col-md-3" style="display: none;">
								<div class="box-title generate-title pull-right">
									<a href="#" class="generateFeeReport"><i class="fa fa-file-pdf-o" style="font-size:24px; color:red"></i></a> &nbsp;&nbsp;
									<a href="#" class="sendFeeReportOnEmail"><i class="fa fa-envelope" style="font-size:24px; color: #3c8dbc"></i></a>
								</div>
							</div>
						</div>
						
						<div id="collapseOne" class="panel-collapse collapse" aria-expanded="false">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-3">
										<label>Fee Category</label>
									</div>
									<div class="col-md-3">
										<label>Total Amount</label>
									</div>
									<div class="col-md-3">
										<label>Amount Paid</label>
									</div>
									<div class="col-md-3">
										<label>Amount Due</label>
									</div>
								</div>
	
	<c:forEach var="FeeCategory" items="${FeeCategoriesYearly }">
	<%
	boolean findFlagYr = false;
	%>
								<div class="col-md-12">
									<div class="col-md-3">
										${FeeCategory.feeCategory.feeCategoryName }
										<input type="hidden" class="feeCategoryCode" value="${FeeCategory.feeCategory.feeCategoryCode }" />
										<input type="hidden" class="type" value="${FeeCategory.type }" />
									</div>
									<div class="col-md-3 totalAmount">
										${FeeCategory.amount }
									</div>
	<c:forEach var="StudentFeeDetails" items="${StudentFeeDetailses }">
	<c:if test="${StudentFeeDetails.type eq 'Y' }">
	<c:if test="${StudentFeeDetails.feeCategoryCode eq FeeCategory.feeCategory.feeCategoryCode }">
	<%findFlagYr = true; %>			<div style="display: none;">
										<input type="hidden" class="receiptNo" value="${StudentFeeDetails.receiptNo }" />
									</div>
									<div class="col-md-3 amountPaid">
										<a class="btn yearly" data-toggle="modal" data-target="#myModal" >${StudentFeeDetails.amountPaid }</a>
									</div>
									<div class="col-md-3 amountDue">
										${StudentFeeDetails.amountDue }
									</div>
	</c:if>
	<%-- <c:if test="${StudentFeeDetails.feeCategoryCode ne FeeCategory.feeCategory.feeCategoryCode }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary yearly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if> --%>
	</c:if>
	</c:forEach>
	<c:if test="${not empty StudentFeeDetailses }">
	<%if(!findFlagYr){ %>
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary yearly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	<%} %>
	</c:if>
	<c:if test="${empty StudentFeeDetailses }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary yearly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if>
								</div>
	</c:forEach>
							</div>
						</div>
						
						
						
					</div>
				</div>
</c:if>
<c:forEach var="halfFeeCategories" items="${FeeCategoriesHalfYearly }">
<c:if test="${not empty halfFeeCategories }">
				<div class="col-md-12">		
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">
								<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed" aria-expanded="false">
									<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="monthlyTitle">Half-Yearly</span>
								</a>
							</h3>
						</div>
						<div class="col-md-3">
							<h3 class="box-title receipt-title">Receipt No.</h3>
						</div>
						<div class="col-md-3">&nbsp;
						</div>
						<div class="col-md-3" style="display: none;">
							<div class="box-title generate-title pull-right">
								<a href="#" class="generateFeeReport"><i class="fa fa-file-pdf-o" style="font-size:24px; color:red"></i></a> &nbsp;&nbsp;
								<a href="#" class="sendFeeReportOnEmail"><i class="fa fa-envelope" style="font-size:24px; color: #3c8dbc"></i></a>
							</div>
						</div>
						
						<div id="collapseTwo" class="panel-collapse collapse" aria-expanded="false">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-3">
										<label>Fee Category</label>
									</div>
									<div class="col-md-3">
										<label>Total Amount</label>
									</div>
									<div class="col-md-3">
										<label>Amount Paid</label>
									</div>
									<div class="col-md-3">
										<label>Amount Due</label>
									</div>
								</div>
	<c:forEach var="halfFeeCategory" items="${halfFeeCategories }">
								<div class="col-md-12">
									<div class="col-md-3">
										${halfFeeCategory.feeCategory.feeCategoryName }
										<input type="hidden" class="feeCategoryCode" value="${halfFeeCategory.feeCategory.feeCategoryCode }" />
										<input type="hidden" class="type" value="${halfFeeCategory.type }" />
									</div>
									<div class="col-md-3 totalAmount">
										${halfFeeCategory.amount }
									</div>
	<c:forEach var="StudentFeeDetails" items="${StudentFeeDetailses }">
	<c:if test="${StudentFeeDetails.type eq 'H' }">
	<c:if test="${StudentFeeDetails.feeCategoryCode eq halfFeeCategory.feeCategory.feeCategoryCode }">
									<div class="col-md-3 amountPaid">
										<a class="btn halfYearly" data-toggle="modal" data-target="#myModal" >${StudentFeeDetails.amountPaid }</a>
									</div>
									<div class="col-md-3 amountDue">
										${StudentFeeDetails.amountDue }
									</div>
	</c:if>
	<c:if test="${StudentFeeDetails.feeCategoryCode ne halfFeeCategory.feeCategory.feeCategoryCode }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary halfYearly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if>
	</c:if>
	</c:forEach>
	<c:if test="${empty StudentFeeDetailses }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary halfYearly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if>
								</div>
	</c:forEach>
							</div>
						</div>	
							
							
							
					</div>
				</div>
</c:if>
</c:forEach>


<%int monthCount = 1;%>
<c:forEach var="monthlyFeeCategories" items="${FeeCategoriesMonthly }">
<c:if test="${not empty monthlyFeeCategories }">
				<div class="col-md-12">		
					<div class="box box-primary">
						<div class="box-header">
							<%-- <h3 class="box-title monthlyTitle"><%=monthCount %></h3> --%>
							<div class="col-md-3">
								<h3 class="box-title">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapse<%=monthCount %>" class="collapsed" aria-expanded="false">
										<i class="fa fa-calendar"></i>&nbsp;&nbsp; <span class="monthlyTitle"><%=monthCount %></span>
									</a>
								</h3>
							</div>
							<div class="col-md-3">
								<h3 class="box-title receipt-title">Receipt No.</h3>
							</div>
							<div class="col-md-3">&nbsp;
							</div>
							<div class="col-md-3" style="display: none;">
								<div class="box-title generate-title pull-right">
									<a href="#" class="generateFeeReport"><i class="fa fa-file-pdf-o" style="font-size:24px; color:red"></i></a> &nbsp;&nbsp;
									<a href="#" class="sendFeeReportOnEmail"><i class="fa fa-envelope" style="font-size:24px; color: #3c8dbc"></i></a>
								</div>
							</div>
							<div style="display: none;">
								<input type="hidden" class="monthId" value="<%=monthCount %>" />
							</div>
						</div>
						
						<div id="collapse<%=monthCount %>" class="panel-collapse collapse" aria-expanded="false">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-3">
										<label>Fee Category</label>
									</div>
									<div class="col-md-3">
										<label>Total Amount</label>
									</div>
									<div class="col-md-3">
										<label>Amount Paid</label>
									</div>
									<div class="col-md-3">
										<label>Amount Due</label>
									</div>
								</div>
	<c:forEach var="monthlyFeeCategory" items="${monthlyFeeCategories }">
	<%
	boolean foundFlag = false;
	%>
								<div class="col-md-12">
									<div class="col-md-3">
										${monthlyFeeCategory.feeCategory.feeCategoryName }
										<input type="hidden" class="feeCategoryCode" value="${monthlyFeeCategory.feeCategory.feeCategoryCode }" />
										<input type="hidden" class="type" value="${monthlyFeeCategory.type }" />
									</div>
									<div class="col-md-3 totalAmount">
										${monthlyFeeCategory.amount }
									</div>
	<c:forEach var="StudentFeeDetails" items="${StudentFeeDetailses }">
	<c:if test="${StudentFeeDetails.type eq 'M' }">
	<c:if test="${StudentFeeDetails.feeCategoryCode eq monthlyFeeCategory.feeCategory.feeCategoryCode }">
	<c:set var="month" value="${StudentFeeDetails.month }" />
	<%
	int mnth = (int) pageContext.getAttribute("month");
	if(mnth == monthCount){
	foundFlag = true;
	%>
									<div style="display: none;">
										<input type="hidden" class="receiptNo" value="${StudentFeeDetails.receiptNo }" />
									</div>
									<div class="col-md-3 amountPaid">
										<a class="btn monthly" data-toggle="modal" data-target="#myModal" >${StudentFeeDetails.amountPaid }</a>
									</div>
									<div class="col-md-3 amountDue">
										${StudentFeeDetails.amountDue }
									</div>
	<%} %>
	<%-- <%} else { %>
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary monthly" data-toggle="modal" data-target="#myModal" >++</a>-<%=mnth %>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	<%} %> --%>
	</c:if>
	<%-- <c:if test="${StudentFeeDetails.feeCategoryCode ne monthlyFeeCategory.feeCategory.feeCategoryCode }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary monthly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if> --%>
	</c:if>
	</c:forEach>
	<c:if test="${not empty StudentFeeDetailses }">
	<%if(!foundFlag){ %>
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary monthly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	<%} %>
	</c:if>
	<c:if test="${empty StudentFeeDetailses }">
									<div class="col-md-3 amountPaid">
										<a class="btn btn-primary monthly" data-toggle="modal" data-target="#myModal" >+</a>
									</div>
									<div class="col-md-3 amountDue">
	<!-- 									<button class="btn btn-primary" name="" value="" >+</button> -->
									</div>
	</c:if>
								</div>
	</c:forEach>
							</div>
						</div>	
							
							
							
					</div>
				</div>
</c:if>
<%monthCount++; %>
</c:forEach>
				<!-- /.box-body -->

				<div class="box-footer">
					<!-- <input type="submit" class="btn btn-primary" value="Submit" /> -->
				</div>
			</form>
		</div>

	</section>
</div>

<style>
.monthly, .yearly
{
	line-height: 1.0;
	margin-bottom: 2px;
	margin-top: 2px;
}
</style>
<script>
$(document).ready(function(){
	initializeHints();
	//$('.monthlyDiv').hide();
	
// $('input, select, textarea').attr('disabled', 'disabled');
	var modalBtnEle = null;
	$('[data-toggle="modal"]').on('click', function(){
		modalBtnEle = $(this);
		$('.monthlyDiv').hide();
		$('.modal-body').find('div:not(.msg)').show();
		$('#saveStudentFeeDetails input[type="submit"]').show();
		$('.msg').text('');
		$('[name="amountPaid"]').val('');
		$('[name="amountDue"]').val('');
		var parentEle = $(this).closest('.box-primary');
		var boxTitle = $(parentEle).find('.box-title:first').find('.monthlyTitle').text();
		var monthId = $(parentEle).find('.monthId').val();
		var feeCategoryCode = $(this).parent().parent().find('.feeCategoryCode').val();
		var feeCategoryType = $(this).parent().parent().find('.type').val();
		var totalAmount = $(this).parent().parent().find('.totalAmount').text().trim();
		var amountDue = $(this).parent().parent().find('.amountDue').text().trim();
		$('[name="monthName"]').val(boxTitle);
		$('[name="month"]').val(monthId);
		$('[name="feeCategoryCode"]').val(feeCategoryCode);
		$('[name="type"]').val(feeCategoryType);
		$('[name="totalAmount"]').val(totalAmount);
		if($(this).text().trim() != "+"){
			$('[name="amountDue"]').val(amountDue);
		}
		var typeName = '';
		if(feeCategoryType == 'Y')
			typeName = 'Yearly';
		else if(feeCategoryType == 'H')
			typeName = 'Half Yearly';
		else
			typeName = 'Monthly';
		$('[name="typeName"]').val(typeName);
		$('[name="paymentCategoryCode"]').val('');
		$('.bankDetails').addClass('hidden');
		$('[name="bankBranch"]').removeClass('bankBranch');
		$('[name="bankName"]').removeClass('bankName');
		$('[name="chequeNo"]').removeClass('chequeNo');
		/* if($(this).hasClass('monthly'))
		{
			$('.monthlyDiv').show();
		}
		else if($(this).hasClass('halfYearly'))
		{
			alert('Half Yearly');
		}
		else if($(this).hasClass('yearly'))
		{
			alert('Yearly');
		} */
	});
	
	$('.monthlyTitle').each(function(){
		var monthCount = $(this).text();
		$(this).text(getMonth(monthCount));
	});
	function getMonth(monthCount)
	{ 
		var month = "";
		switch (monthCount){
		case "1":
			month = "Jan";
			break;
		case "2":
			month = "Feb";
			break;
		case "3":
			month = "Mar";
			break;
		case "4":
			month = "Apr";
			break;
		case "5":
			month = "May";
			break;
		case "6":
			month = "Jun";
			break;
		case "7":
			month = "Jul";
			break;
		case "8":
			month = "Aug";
			break;
		case "9":
			month = "Sep";
			break;
		case "10":
			month = "Oct";
			break;
		case "11":
			month = "Nov";
			break;
		case "12":
			month = "Dec";
			break;
		case "Half-Yearly":
			month = "Half-Yearly";
			break;
		case "Yearly":
			month = "Yearly";
			break;
		}
		return month;
	}
	
	$('#saveStudentFeeDetails input[type="submit"]').on("click", function(e){
		e.preventDefault();
		var query = $('#saveStudentFeeDetails').serialize();
		$.ajax({
			url : "saveStudentFeeDetails",
			data: query,
			type : "post",
			//dataType : "json",
			cache: false,
			async: false,
			success: function(data) {
			//console.log(data.msg);
				if(data.hasError) {
					$('.msg').text(data.msg).css('color', 'red');	
				}
				else {
					$('.modal-body').find('div:not(.msg)').hide();
					$('#saveStudentFeeDetails input[type="submit"]').hide();
					$('.msg').text(data.msg).css('color', 'green');
					var parentEle = $(modalBtnEle).parent().parent();
					$(parentEle).find('.amountPaid').children().removeClass('btn-primary').text(data.paid);
					if(parseFloat(data.due) > 0.00) {
						$(parentEle).find('.amountDue').text(data.due).css('color', 'red');
					} else {
						$(parentEle).find('.amountDue').text(data.due).css('color', 'black');
						$(parentEle).find('.amountPaid').children().removeAttr('data-target');
					}
					var titleParent = $(modalBtnEle).closest('.box-primary');
					var receiptNo = $(titleParent).find('.receipt-title');
					var generateReceipt = $(titleParent).find('.generateFeeReport');
					$(generateReceipt).parent().parent().show();
					if($(receiptNo).children('font').length > 0) {
						$(receiptNo).children('font').text(data.receiptNo);
					}else{
						$(receiptNo).append(' : <font style="color:blue;" class="receiptNo linkCls">' + data.receiptNo + '</font>');
						$(generateReceipt).attr('data-receipt-no', data.receiptNo);
					}
				}
			},
			error: function(e) {
				alert("error in AJAX: saveStudentFeeDetails");
			}
		}); 
	});
	
	$('.amountDue').each(function(index){
		//alert( index + ": " + $( this ).text().trim()+" closest"+$(this).closest('.amountPaid a').text());
		var dueVal = $( this ).text().trim();
		if(dueVal != "" && dueVal == 0) {
			var ele = $(this).prev('.amountPaid').children();
			$(ele).removeAttr('data-target');
			//console.log($(ele).removeAttr('data-target'));
		} else {
			$(this).css('color', 'red');
		}
	});
	
	$('input[type=hidden].receiptNo').each(function(index){
		var titleParent = $(this).closest('.box-primary');
		var receiptNo = $(titleParent).find('.receipt-title');
		var receiptReport = $(titleParent).find('.generateFeeReport');
		if($(receiptNo).children('font').length > 0) {
			$(receiptNo).children('font').text($(this).val());
			$(receiptReport).attr('data-receipt-no', $(this).val());
		} else {
			$(receiptNo).append(' : <font style="color:blue;" class="receiptNo linkCls">' + $(this).val() + '</font>');
			$(receiptReport).attr('data-receipt-no', $(this).val());
		}
		//$(receiptNo).append(' : <font style="color:blue;">' + $(this).val() + '</fornt>');
		
	});
	
	$('.generateFeeReport').on('click', function() {
		var feeCategoryType = $(this).closest('.box-primary').find('.type').val();
		var url = "generateFeeReceipt";
		var queryString = "receiptNo="+$(this).attr('data-receipt-no')+"&admissionNo="+$('[name="admissionNo"]').val()+"&rollNo="+$('[name="rollNo"]').val()+"&classCode="+$('[name="classCode"]').val()+"&sectionCode="+$('[name="sectionCode"]').val()+"&type="+feeCategoryType;
	    var inputMap = getJsonFromQueryString(queryString);
	    //console.log(inputMap);
		sendRequest(url, "post", inputMap);
	});
	
	$('.receipt-title').each(function(index){
		if($(this).children('font').length > 0){
			$(this).closest('.box-header').find('.generate-title').parent().show();
		}else{
			$(this).closest('.box-header').find('.generate-title').parent().hide();
		}
	});
	
	$('.receipt-title').on('click', '.receiptNo', function(e){
		var receiptNo = $(this).text();
		$(document.forms[0]).appProcessRequest({url:'getStudentFeeTransactionHistoryModal?receiptNo='+receiptNo, role:'modal-link'});
	});
	$('.sendFeeReportOnEmail').on('click', function(e){
		var feeCategoryType = $(this).closest('.box-primary').find('.type').val();
		var receiptNo = $(this).prev('.generateFeeReport').attr('data-receipt-no');
		var url = "sendEmailModal";
		var data = "action=sendFeeReceiptOnEmail&receiptNo="+receiptNo+"&admissionNo="+$('[name="admissionNo"]').val()+"&rollNo="+$('[name="rollNo"]').val()+"&classCode="+$('[name="classCode"]').val()+"&sectionCode="+$('[name="sectionCode"]').val()+"&type="+feeCategoryType+"&emailTo=${StudentDetails.getParentDetails().getFatherEmail() }";
		$(document.forms[0]).appProcessRequest({url:url, data: data, role:'modal-link'});
	});
	
	$('.paymentCategory').on('change', function(){
		if($(this).val() == 'CASH') {
			$('.bankDetails').addClass('hidden');
			$('[name="bankBranch"]').removeClass('bankBranch');
			$('[name="bankName"]').removeClass('bankName');
			$('[name="chequeNo"]').removeClass('chequeNo');
		} else {
			$('.bankDetails').removeClass('hidden');
			$('[name="bankBranch"]').addClass('bankBranch');
			$('[name="bankName"]').addClass('bankName');
			$('[name="chequeNo"]').addClass('chequeNo');
		}
		$()
	});
});


</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>