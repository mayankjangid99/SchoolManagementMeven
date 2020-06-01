<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Report Card Config <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Report Card Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addReportCardConfig" class="btn btn-primary pull-right">Add Report Card Config</a>
				</div>
			</div>
			<s:form role="form" action="resultReportCardConfig" method="post" cssClass="formm" modelAttribute="reportCardConfig">
				<div class="box-body">
					
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Class </label> <input type="text"
									class="form-control className intelliClass-0-className stdClassHints"
									id="sclass" name="className" placeholder="Class Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> <input type="text"
								class="form-control intelliClass-0-classCode" id="classCode"
								name="classCode" readonly="readonly" />
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Section </label> <input type="text"
									class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> <input type="text"
								class="form-control intelliClass-0-sectionCode" id="sectionCode"
								name="sectionCode" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Report Card Config</label> 
									<s:select path="configCode" cssClass="form-control" >
										<s:option value="">-Select-</s:option>
										<s:options items="${reportCardTermData }"/>
									</s:select>
									
							</div>
						</div>
					</div>
					
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</s:form>
		</div>
	</section>
</div>

<script>
$(document).ready(function(){
	initializeHints();
		
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>