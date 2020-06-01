<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="appProjModalBase">
	<div class="modal fade app-modal-primary" id="appProjModal">
		<div class="modal-dialog medium">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">Fee Transaction History</h4>
				</div>
				<div class="modal-body">
					<!-- <div style="color: green; font-size: 15px; margin: 10px;" class="msg">&nbsp;</div> -->
						<table class="table table-striped">
						<thead>
							<tr style="background-color: gainsboro;">
								<th style="width: 10px">#</th>
								<th>Date</th>
								<th>Fee Code</th>
								<th>Total Amount</th>
								<th>Amount Paid</th>
								<th>Amount Due</th>
								<th>Due Tran.</th>
							</tr>
						<thead>
						<tbody>
<c:forEach var="feeTran" items="${FeeTranHistories }" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td style="width: 170px;"><fmt:formatDate pattern="dd-MMM-yyyy hh.mm.ss aa" value="${feeTran.createdOn }" /></td>
								<td>${feeTran.feeCategoryCode }</td>
								<td>${feeTran.totalAmount }</td>
								<td>${feeTran.amountPaid }</td>
								<td>${feeTran.amountDue }</td>
								<td>
<c:if test="${empty feeTran.previousStudentFeeDetailsId }">
									No
</c:if>
<c:if test="${not empty feeTran.previousStudentFeeDetailsId }">
									Yes
</c:if>							</td>
							</tr>
</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->
</div>