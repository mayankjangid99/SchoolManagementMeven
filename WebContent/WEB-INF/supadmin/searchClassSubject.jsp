<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Class Subject <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Search Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addClassSubject" class="btn btn-primary pull-right">Add Class Subject</a>
				</div>
			</div>
			<form role="form" action="resultClassSubject" method="post" class="formValid">
				<div class="box-body">						
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>Class </label> 
									<input type="text"
										class="form-control intelliClass-0-className stdClassHints" placeholder="Class Name"
										id="sclass" name="className" />
								</div>
							</div>
							<div class="col-md-3">
								<label>Class ID</label> <input type="text"
									class="form-control intelliClass-0-classCode" id="classCode"
									name="classCode" readonly="readonly" />
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>Section </label> 
									<input type="text"
										class="form-control intelliClass-0-sectionName stdSectionHints" placeholder="Class Name"
										id="sclass" name="sectionName" />
								</div>
							</div>
							<div class="col-md-3">
								<label>Section ID</label> <input type="text"
									class="form-control intelliClass-0-sectionCode" id="classCode"
									name="sectionCode" readonly="readonly" />
							</div>
							<!-- <div class="col-md-3">
								<label>Section ID</label> <input type="text"
									class="form-control intelliClass-0-sectionCode" id="sectionCode"
									name="sectionCode" readonly="readonly" />
							</div> -->
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>Type </label> 
									<select name="type" class="form-control" id="type">
										<option value=""> -- Select -- </option>
										<option value="M">Mandatory</option>
										<option value="O">Optional</option>
										<option value="C">Co-Scholastic</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6"></div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-8">
								<div class="form-group">
									<label>Subject </label> 
									<select name="subjectCode" class="form-control">
										<option value="">-- Select --</option>
<c:forEach var="subject" items="${Subjects }">
										<option value="${subject.subjectCode }">${subject.subjectName }</option>
</c:forEach>
									</select>
								</div>
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