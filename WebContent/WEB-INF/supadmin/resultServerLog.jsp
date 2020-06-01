<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Server Log<!--  <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Server Log Details</h3>
				<div class="col-md-6 pull-right">
					<a href="resultServerHistoryLogs" class="btn btn-primary pull-right">Server Log History</a>
				</div>
			</div>
				<div class="box-body">
										
					<div id="example1_wrapper"
						class="dataTables_wrapper form-inline dt-bootstrap">

						<div class="row">
							<div class="col-sm-12">
								<table id="example1"
									class="table table-bordered table-striped dataTable"
									role="grid" aria-describedby="example1_info">
									<thead class="thead">
										<tr role="row">
											<th rowspan="1" colspan="1">Log Name</th>
											<th rowspan="1" colspan="1">Log Last Modified Time</th>
											<th rowspan="1" colspan="1">Log Size</th>
											<th rowspan="1" colspan="1">Download</th>
										</tr>
									</thead>
									<tbody>

											<tr role="row" class="odd">
												<td>
													${ServerLogInfo.logName }
												</td>
												<td>
													${ServerLogInfo.logLastModifiedTime }
												</td>
												<td>
													${ServerLogInfo.logSize }
												</td>
												<td style="text-align: center; width: 100px; height: 40px;">
													<a href="downloadServerLog?file=${ServerLogInfo.logPath }" class="sendPost"><i class='glyphicon glyphicon-download-alt'></i></a>
												</td>
											</tr>
										<c:if test="${empty ServerLogInfo }">
											<tr height="60px;">
												<td colspan="8" style="text-align: center;">No data to
													display</td>
											</tr>
										</c:if>
									</tbody>

									<tfoot>
										<tr>
											<th rowspan="1" colspan="1">Log Name</th>
											<th rowspan="1" colspan="1">Log Last Modified Time</th>
											<th rowspan="1" colspan="1">Log Size</th>
											<th rowspan="1" colspan="1">Download</th>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>
				
				</div>
				<!-- /.box-body -->
		</div>
	</section>
</div>


<%-- <%@ include file="../common/footer.jsp"%> --%>