<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit Subject<!--  <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 300px;">
			<div class="box-header">
				<h3 class="box-title">Edit Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addClassSubject" class="btn btn-primary pull-right">Add Subject</a>
					<a href="searchClassSubject" class="btn btn-primary pull-right">Search Subject</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Subject</h3>
						</div>
						<form role="form" action="updateSubjectDetails" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Subject Name <span class="red">*</span></label> 
											<input type="text"
												class="form-control subjectName" placeholder="Subject Name" style="cursor: text;"
												id="subjectName" name="subjectName" value="${Subject.subjectName }" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Subject Code <span class="red">*</span></label> <input type="text"
											class="form-control subjectCode" id="classCode" placeholder="Subject Code"
											name="subjectCode" value="${Subject.subjectCode }" readonly="readonly"/>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
						</form>
					</div>
				</div>
				<%-- <div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Section</h3>
						</div>
						<form role="form" action="updateSection" method="post" class="formm">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Section</label> 
											<input type="text"
												class="form-control" name="sectionName" placeholder="Section Name"  value="${SectionInfo.sectionName }" style="cursor: text;" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Section ID</label> <input type="text"
											class="form-control" id="sectionCode" placeholder="Section Code" style="cursor: text;"
											name="sectionCode" value="${SectionInfo.sectionCode }" readonly="readonly" />
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Submit" />
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

	initializeHints();
	
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>