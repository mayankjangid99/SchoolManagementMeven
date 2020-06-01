<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Fee Category <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Fee Category Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addFeeCategory" class="btn btn-primary pull-right">Add Fee Category</a>
				</div>
			</div>
			<form role="form" action="resultFeeCategory" method="post" class="formm">
				<div class="box-body">
					<%-- <div class="col-md-12">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<label>School Name </label> 
									<select class="form-control" name="schoolCode">
										<option value="">-- Select --</option>
<c:forEach var="SchoolProfile" items="${SchoolProfiles }"><option value="${SchoolProfile.schoolCode }"> ${SchoolProfile.name } </option></c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
					</div> --%>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Class <span class="red">*</span></label>
								<input type="text"
									class="form-control intelliClass-0-className stdClassHints className" placeholder="Class Name"
									id="sclass" name="className" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> <input type="text"
								class="form-control intelliClass-0-classCode" id="classCode"
								name="classCode" readonly="readonly" />
						</div>

						<div class="col-md-4">
							<div class="form-group">
								<label>Section <span class="red">*</span></label> <input type="text"
									class="form-control sectionName intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> <input type="text"
								class="form-control intelliClass-0-sectionCode" id="sectionCode"
								name="sectionCode" readonly="readonly" />
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Fee Type </label> 
								<select name="feeType" class="form-control" id="feeType">
									<option value=""> -- Select -- </option>
									<option value="N">Non-Additional</option>
									<option value="A">Additional</option>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Fee Category</label> 
								<!-- <input type="text"
									class="form-control intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" /> -->
									<select name="feeCategoryCode" class="form-control">
										<option value="">-- Select --</option>
<c:forEach var="feeCategory" items="${FeeCategories }"><option value="${feeCategory.feeCategoryCode }">${feeCategory.feeCategoryName }</option></c:forEach>
									</select>
							</div>
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>Type </label> 
								<select name="type" class="form-control" id="type">
									<option value=""> -- Select -- </option>
									<option value="Y">Yearly</option>
<!-- 										<option value="H">Half-Yearly</option> -->
									<option value="M">Monthly</option>
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
	//$('input, select, textarea').attr('disabled', 'disabled');	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>