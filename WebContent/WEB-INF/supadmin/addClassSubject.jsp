<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Class Subject <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 800px;">
			<div class="box-header">
				<h3 class="box-title">Add Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchClassSubject" class="btn btn-primary pull-right">Search Class Subject</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Subject</h3>
						</div>
						<form role="form" action="saveSubject" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Subject Name <span class="red">*</span></label> 
											<input type="text"
												class="form-control subjectName" placeholder="Subject Name" style="cursor: text;"
												id="subjectName" name="subjectName" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Subject Code <span class="red">*</span></label> <input type="text"
											class="form-control subjectCode" id="subjectCode" placeholder="Subject Code"
											name="subjectCode"/>
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
							<h3 class="box-title">Edit/Delete Subject</h3>
						</div>
						<form role="form" action="editSubjectDetails" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Subjects <span class="red">*</span></label> 
											<select name="subjectCode" class="form-control subjectCode" id="subjectCodeED">
												<option value="">-- Select --</option>
<c:forEach var="subject" items="${Subjects }"><option value="${subject.subjectCode }">${subject.subjectName }</option></c:forEach>
											</select>
										</div>
									</div>
									<!-- <div class="col-md-4">
										<label>Section ID</label> <input type="text"
											class="form-control" id="sectionCode" placeholder="Section Code" style="cursor: text;"
											name="sectionCode" />
									</div> -->
								</div>
							</div>
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Edit" /> 
								<button class="btn btn-primary formValidBtn" onClick="navTo('Delete')">Delete</button>
							</div>
						</form>
					</div>
				</div> 
				
			</div>
			
			
			<div class="col-md-12" style="margin-top: 20px;">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Allocate Subject to Class</h3>
					</div>
					<form action="saveAllocatedSubjectToClass" name="selection" method="post" onSubmit="return selectAll()" class="formValid"> 
						<div class="box-body">
							<%-- <div class="col-md-12">
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
							</div> --%>
							<div class="col-md-12">
								<div class="col-md-3">
									<div class="form-group">
										<label>Type <span class="red">*</span></label> 
										<select name="type" class="form-control type" id="type">
											<option value=""> -- Select -- </option>
											<option value="M">Mandatory</option>
											<option value="O">Optional</option>
											<option value="C">Co-Scholastic</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-3">
									<div class="form-group">
										<label>Class <span class="red">*</span> </label> 
										<input type="text"
										class="form-control intelliClass-1-className stdClassHints className" placeholder="Class Name"
										id="clsName" name="className" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Class ID <span class="red">*</span></label> <input type="text"
									class="form-control intelliClass-1-classCode" id="clsCode" placeholder="Class Code" readonly="readonly"
									name="classCode"/>
								</div>
								<div class="col-md-2"></div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Section <span class="red">*</span> </label> 
										<input type="text"
										class="form-control intelliClass-1-sectionName stdSectionHints sectionName" placeholder="Section Name"
										id="secName" name="sectionName" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID <span class="red">*</span></label> <input type="text"
									class="form-control intelliClass-1-sectionCode" id="secCode" placeholder="Section Code" readonly="readonly"
									name="sectionCode"/>
								</div>
							</div>
							<div class="col-md-12">
								<div class=" col-md-5">
									<div class="col-md-12 multiList"><font>All Subject</font></div>
								    <select multiple size="10" id="from" class="form-control">
								    </select>
								</div>
							    <div class="col-md-2 swapList" style="padding: 5px;"> 
							        <a class="btn abtn btn-info round" href="javascript:moveAll('from', 'to')">&nbsp;<i class="glyphicon glyphicon-forward"></i></a> 
							        <a class="btn abtn btn-info round" href="javascript:moveSelected('from', 'to')">&nbsp;<i class="glyphicon glyphicon-triangle-right"></i></a> 
							        <a class="btn abtn btn-info round" href="javascript:moveSelected('to', 'from')"><i class="glyphicon glyphicon-triangle-left"></i>&nbsp;</a> 
							        <a class="btn abtn btn-info round" href="javascript:moveAll('to', 'from')" href="#"><i class="glyphicon glyphicon-backward"></i>&nbsp;</a> 
							    </div>
							    <div class=" col-md-5">
									<div class="col-md-12 multiList"><font>Selected Subject</font></div>
							    	<select multiple id="to" class="form-control" size="10" name="selectedSubject"></select>
								</div>
						    </div>
					    </div>
					    <div class="box-footer">
							<input type="submit" class="btn btn-primary" value="Submit" />
						</div>
					</form>
				</div>
			</div>
    
		</div>
	</section>
</div>

<script>
$(document).ready(function(){

	initializeHints();
	
 /* $('input, select, textarea').attr('disabled', 'disabled'); */
		
 
 	$('#type').on('change', function(e){
 		$('#clsCode').val('');
 		$('#clsName').val('');
    	$('#secCode').val('');
    	$('#secName').val('');
    	$('#from').empty();
	 	$('#to').empty();
 	});
 
    $('#secName').on('focusout', function(e){
    	var selectedType = $("select[name=type] option:selected").val();
    	var classCode = $('#clsCode').val();
    	var sectionCode = $('#secCode').val();
    	if(!selectedType) {
    		alert("Please select Subject type..!!!");
    		return false;
    	} else if(!classCode) {
    		alert("Please select a Class..!!!");
    		return false;
    	} else if(!sectionCode) {
    		alert("Please select a Section..!!!");
    		return false;
    	}
    	
    	$.ajax({
			   url : "getSubjectDetailsForAllocateToClassSection",
			   data: "classCode=" + classCode + "&sectionCode=" + sectionCode + "&type=" + selectedType,
				//dataType : "json",
				type: "post",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
			 	  $('#from').empty();
			 	  $('#to').empty();
				  $.each(data.unAllocated, function( i, obj ) {
					  //alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					  $('#from').append('<option value=' + obj.subjectCode + '>' + obj.subjectName + '</option>');
				  });
				  $.each(data.allocated, function( i, obj ) {
					  //alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					  $('#to').append('<option value=' + obj.subjectCode + '>' + obj.subjectName + '</option>');
				  });
			   },
			   error: function(e) {
				   alert("error");
			   }
			 });
    });
    
});



function moveAll(from, to) {
    $('#'+from+' option').remove().appendTo('#'+to); 
}

function moveSelected(from, to) {
    $('#'+from+' option:selected').remove().appendTo('#'+to); 
}
function selectAll() {
    $("#from option, #to option").attr("selected","selected");
}

function navTo(action)
{
	if(!$('#subjectCodeED').val()){
		return false;
	}
	if(action == 'Delete')
	{
		sendRequest('deleteSubjectDetails', 'post', { "subjectCode": $('[name="subjectCode"] option:selected').val() } );
	}
}
</script>


<%-- <%@ include file="../common/footer.jsp"%> --%>