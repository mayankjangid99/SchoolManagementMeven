<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Report Card Config <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Report Card Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchReportCardConfig" class="btn btn-primary pull-right">Search Report Card Config</a>
				</div>
			</div>
			<s:form role="form" action="saveReportCardConfig" cssClass="formm" modelAttribute="reportCardConfig">
				<!-- Hidden Variables -->
				<div style="display: none;">
					<input type="hidden" name="opt" value="${opt }">
				</div>
				<div class="box-body">
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Class</label> 
								<input type="text" class="form-control intelliClass-0-className stdClassHints" name="className" placeholder="Class Name" value="${reportCardConfig.classInformation.className }" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> 
							<input type="text" class="form-control intelliClass-0-classCode" id="classCode" name="classCode" readonly="readonly" value="${reportCardConfig.classInformation.classCode }" />
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Section</label> 
								<input type="text" class="form-control intelliClass-0-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" value="${reportCardConfig.sectionInformation.sectionName }" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> 
							<input type="text" class="form-control intelliClass-0-sectionCode" id="sectionCode" name="sectionCode" readonly="readonly" value="${reportCardConfig.sectionInformation.sectionCode }" />
						</div>
					</div>
					
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Code <span class="red">*</span></label> 									
									<s:select path="configCode" cssClass="form-control configCode" >
										<s:option value="">-Select-</s:option>
										<s:options items="${reportCardTermData }"/>
									</s:select>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Value<span class="red">*</span></label> 
								<s:textarea cssClass="form-control configValue" path="configValue" placeholder="Value" rows="2" ></s:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Description<span class="red">*</span></label> 
								<s:textarea cssClass="form-control configDescription" path="configDescription" placeholder="Description" rows="3" ></s:textarea>
								<br/>Unit(s)/Term(s) = HeaderName1~Max Marks2, HeaderName1~Max Marks2<br/> 
								Ex.- 3 = Unit3/Term1~10, Unit3/Term2~10, Unit3/Term3~10
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
// 	$('.attnDate').datepicker({format: 'dd-mm'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>