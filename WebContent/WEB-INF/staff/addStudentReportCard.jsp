<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.getUserProfileUtils() }" />
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Student Report Card <!-- <small>it all starts here</small> -->
		</h1>
	</section>

	<form role="form" action="saveStudentReportCard" method="post" class="formValid" id="saveStudentReportCard">
<%@ include file="../common/message.jsp" %>
	
		<!-- Main content -->
		<section class="content">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">Student Report Card Details</h3>
					<div class="col-md-6 pull-right">
<c:if test="${not empty fieldsData and not empty param.admissionNo }">					
						<a href="#" class="generateStudentReportCard"><i class="fa fa-file-pdf-o pull-right" style="font-size:24px; color:red"></i></a>
</c:if>						
						<a href="searchStudentReportCard" class="btn btn-primary pull-right">Search Student Report Card</a>
					</div>
				</div>
				<div class="box-body">
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label>Class <span class="red">*</span></label>
								<input type="text" class="form-control className intelliClass-1-className stdClassHints" placeholder="Class Name" id="sclass" name="className" readonly="readonly" value="${className }">
							</div>
						</div>
						<div class="col-md-2">
							<label>Class ID</label> <input type="text" class="form-control intelliClass-1-classCode" id="classCode" name="classCode" readonly="readonly" value="${classCode }" />
						</div>
						
						<div class="col-md-4">
							<div class="form-group">
								<label>Section <span class="red">*</span></label> <input type="text" class="form-control sectionName intelliClass-1-sectionName stdSectionHints" name="sectionName" readonly="readonly" placeholder="Section Name" value="${sectionName }" />
							</div>
						</div>
						<div class="col-md-2">
							<label>Section ID</label> <input type="text" class="form-control intelliClass-1-sectionCode" id="sectionCode" name="sectionCode" readonly="readonly" value="${sectionCode }" />
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-md-4">
							<div class="form-group">
								<label>Student Full Name <span class="red">*</span></label> 
								<input type="text" class="form-control studentDetailsAutoComplete  intelliDetails-1-fullName" placeholder="Student Full Name" id="" name="fullName" value="${fullName }">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Admission No <span class="red">*</span></label> 
								<input type="text" class="form-control admissionNo intelliDetails-1-admissionNo" name="admissionNo" value="${admissionNo }" readonly="readonly" />
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label>Roll No <span class="red">*</span></label> 
								<input type="text" class="form-control rollNo intelliDetails-1-rollNo" name="rollNo" value="${rollNo }" readonly="readonly" />
							</div>
						</div>
					</div>
					
					
					
<%-- <c:forEach var="sub" items="${AllocatedSubjects }">
					<div class="col-md-12 form-group">
						<input type="hidden" name="subjectId" value="${sub.subjectCode }" />
						<label class="col-md-2">${sub.subjectName }</label>
					</div>
</c:forEach> --%>

<c:if test="${not empty fieldsData }">
<table class="table">
<c:set var="unitMarks" value="${fn:split(unitTestObject.configValue, '=') }" />
<c:set var="unitMarks" value="${fn:split(unitMarks[1], ',') }" />
<c:set var="termMarks" value="${fn:split(termObject.configValue, '=') }" />
<c:set var="termMarks" value="${fn:split(termMarks[1], ',') }" />
<c:forEach var="fields" items="${fieldsData }" begin="0" end="0">
	<c:set var="subject" value="${fieldsData[fields.key] }" />
	<c:set var="termsKey" value="${fields.key}T" />
	<c:set var="terms" value="${subject[termsKey]}" />
<tr>
	<th style="text-align: center; background-color: #edc7c7;">
		Subject
	</th>
	
	<c:set var="overAllText" value="" />
	<c:forEach var="term" items="${terms }" varStatus="loop">
		<c:set var="totalMarks" />
		<c:forEach var="unit" items="${subject[term.key] }" varStatus="loopU">
			<th style="text-align: center; background-color: #c2e5fa;">
				<c:set var="unitMark" value="${fn:split(unitMarks[loopU.index], '~') }" />
				<fmt:parseNumber var = "totalMarks" integerOnly = "true"  type = "number" value = "${totalMarks + fn:trim(unitMark[1])}" />
				${unitMark[0] }<br/>
				(${fn:trim(unitMark[1]) })
			</th>
		</c:forEach>
		<th style="text-align: center; background-color: #f7d1ff;">
			<c:set var="termMark" value="${fn:split(termMarks[loop.index], '~') }" />
			<fmt:parseNumber var = "totalMarks" integerOnly = "true"  type = "number" value = "${totalMarks + fn:trim(termMark[1])}" />
			<c:if test="${loop.index gt 0 }">
				<c:set var="overAllText" value="${overAllText } + Term${loop.index+1 }" />
			</c:if>
			<c:if test="${loop.index eq 0 }">
				<c:set var="overAllText" value="Term${loop.index+1 }" />
			</c:if>
			${termMark[0] }<br/>
			(${fn:trim(termMark[1]) })
		</th>
		<th style="text-align: center; background-color: #eee;">
			Total${loop.index+1 }<br/>
			<c:if test="${totalMarks gt 0}">
				(${fn:trim(totalMarks) })
			</c:if>
		</th>
		<th></th>
	</c:forEach>
		<th style="text-align: center; background-color: #bde199;">Overall <%-- ${overAllText } --%></th>
</tr>
</c:forEach>

<c:forEach var="fields" items="${fieldsData }">
<tr>
	<c:set var="subject" value="${fieldsData[fields.key] }" />
	<c:set var="sub" value="${subject[fields.key] }" />
	
	<td style="text-align: center; background-color: #edc7c7;">
		${sub.subjectName }
	</td>
	<c:set var="termsKey" value="${fields.key}T" />
	<c:set var="terms" value="${subject[termsKey]}" />
	
	<c:set var="overallTotal" value="${0}" />
	<c:set var="noOfTerms" value="${0}" />
	<c:forEach var="term" items="${terms }" varStatus="loop">
		<c:set var="total" value="${0}" />
		<c:forEach var="unit" items="${subject[term.key] }" varStatus="loopU">
			<c:set var="unitMark" value="${fn:split(unitMarks[loopU.index], '~') }" />
			<td style="background-color: #c2e5fa;">
				<input type="text" name="${unit.key }" value="${unit.value }" data-term-val="${term.key }" data-max-val="${fn:trim(unitMark[1]) }" class="form-control addMark sheetEle" />
				<c:set var="total" value="${total + unit.value}" />
			</td>
		</c:forEach>
		<td style="background-color: #f7d1ff;">
			<c:set var="termMark" value="${fn:split(termMarks[loop.index], '~') }" />
			<input type="text" name="${term.key }" value="${term.value }" data-term-val="${term.key }" data-max-val="${fn:trim(termMark[1]) }" class="form-control addMark sheetEle" />
			<c:set var="total" value="${total + term.value}" />
		</td>
		<td style="text-align: center; background-color: #eee;">
			<input type="text" name="${term.key }TOTAL" value="${total }" class="form-control sheetEle " readonly="readonly"/>
			<c:set var="overallTotal" value="${overallTotal + total}" />
			<c:set var="noOfTerms" value="${noOfTerms + 1}" />
		</td>
		<td></td>
	</c:forEach>
	<td style="text-align: center; background-color: #bde199;">
		<c:set var="overallAvg"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${overallTotal / noOfTerms }" /></c:set>
		<input type="text" name="${sub.subjectCode }OVERALL" value="${overallAvg }" class="form-control sheetEle" readonly="readonly"/>
	</td>
</tr>
</c:forEach>
</table>
</c:if>


				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</div>
		</section>
	</form>
</div>

<script>
$(document).ready(function(){

	initializeHints();
	
	$(".sectionName").on('focusout', function(e){
		var classCode = $(this).closest('form').find('[name="classCode"]').val();
		var sectionCode = $(this).closest('form').find('[name="sectionCode"]').val();
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	});
	var classCode = '${classCode}';
	var sectionCode = '${sectionCode}';
	if(classCode && sectionCode)
		initializeStudentDetailsAutoComplete(classCode, sectionCode);
	
// 	$('.attnDate').datepicker({format: 'dd-mm'});
// $('input, select, textarea').attr('disabled', 'disabled');

	$('[name="rollNo"]').on('change', function(){
		$('#saveStudentReportCard').sendPostReq({action: 'addStudentReportCard'});
	});
	
	$('.addMark').on('keyup', function(e) {
		$('.app-proj-error .closeMsg').trigger('click');
		var val = $(this).val();
		var maxVal = $(this).data('max-val');
		var termVal = $(this).data('term-val');
		if(val && !$.isNumeric(val)) {
			$(this).val('').keyup();
			$.processMsg.init({errors:[{element:$(this), msg:'Please enter only number.'}]}, document.forms[0]);
		} else {
			if(val > maxVal) {
				$(this).val('').keyup();
				$.processMsg.init({errors:[{element:$(this), msg:'Please enter less value from max.'}]}, document.forms[0]);
			}
			var sum = 0;
			var trEle = $(this).closest('tr');
			$('[data-term-val='+termVal+']').each(function(i, e) {
				if($(e).data('term-val') == termVal) {
					if($(e).val()) {
						if(!sum)
							sum = $(e).val();
						else
							sum = parseInt(sum) + parseInt($(e).val());
					}
				}
			});
			$('[name='+termVal+'TOTAL]').val(sum);
			
			var overallVal = 0;
			var noOfTerms = 0;
			$(trEle).find("input[name$='TOTAL']").each(function(i, e) {
				overallVal = parseInt(overallVal) + parseInt($(e).val());
				noOfTerms = noOfTerms + 1;
			});
			$(trEle).find("input[name$='OVERALL']").val((overallVal/noOfTerms).toFixed(2));
		}
	});
});



$('.generateStudentReportCard').on('click', function(){
	var feeCategoryType = $(this).closest('.box-primary').find('.type').val();
	var url = "generateStudentReportCard";
	var queryString = "receiptNo="+$(this).attr('data-receipt-no')+"&admissionNo="+$('[name="admissionNo"]').val()+"&rollNo="+$('[name="rollNo"]').val()+"&classCode="+$('[name="classCode"]').val()+"&sectionCode="+$('[name="sectionCode"]').val()+"&type="+feeCategoryType;
    var inputMap = getJsonFromQueryString(queryString);
    //console.log(inputMap);
	sendRequest(url, "post", inputMap);
});
</script>
<style>
.sheetEle {padding: 3px;text-align: center;}
</style>
<%-- <%@ include file="../common/footer.jsp" %> --%>