<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Edit Fee Category <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 300px;">
			<div class="box-header">
				<h3 class="box-title">Edit Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addFeeCategory" class="btn btn-primary pull-right">Add Fee Category</a>
					<a href="searchFeeCategory" class="btn btn-primary pull-right">Search Fee Category</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Edit Fee Category</h3>
						</div>
						<form role="form" action="updateFeeCategory" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Fee Category Name <span class="red">*</span></label> 
											<input type="text"
												class="form-control feeCategoryName" placeholder="Fee Category Name"
												name="feeCategoryName" value="${FeeCategoryInfo.feeCategoryName }" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Category Code <span class="red">*</span></label> <input type="text"
											class="form-control feeCategoryCode" placeholder="Fee Category Code"
											name="feeCategoryCode" value="${FeeCategoryInfo.feeCategoryCode }" readonly="readonly"/>
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