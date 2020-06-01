<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit Class Section <small>it all starts here</small>
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 370px;">
			<div class="box-header">
				<h3 class="box-title">Class and Section Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addClassSection" class="btn btn-primary pull-right">Add Class Section</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Class</h3>
						</div>
						<form role="form" action="updateClass" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Class <span class="red">*</span></label> 
											<input type="text"
												class="form-control className" placeholder="Class Name" style="cursor: text;"
												id="sclass" name="className" value="${ClassInfo.className }" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Class ID <span class="red">*</span></label> <input type="text"
											class="form-control classCode" id="classCode" placeholder="Class Code"
											name="classCode" value="${ClassInfo.classCode }" readonly="readonly"/>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Class Order<span class="red">*</span></label> 
											<input type="text" class="form-control" placeholder="Class Order" name="classOrder" value="${classOrder }" />
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
				</div>
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Section</h3>
						</div>
						<form role="form" action="updateSection" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Section <span class="red">*</span></label> 
											<input type="text"
												class="form-control sectionName" name="sectionName" placeholder="Section Name"  value="${SectionInfo.sectionName }" style="cursor: text;" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Section ID <span class="red">*</span></label> <input type="text"
											class="form-control sectionCode" id="sectionCode" placeholder="Section Code" style="cursor: text;"
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
				</div>
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