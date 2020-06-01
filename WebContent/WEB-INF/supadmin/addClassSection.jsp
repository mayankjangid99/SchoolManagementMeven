<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Class Section <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 850px;">
			<div class="box-header">
				<h3 class="box-title">Add Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchClassSection" class="btn btn-primary pull-right">Search Class Section</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Class</h3>
						</div>
						<form role="form" action="saveClass" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Class <span class="red">*</span></label> 
											<input type="text"
												class="form-control intelliClass-0-className stdClassHints className" placeholder="Class Name" style="cursor: text;"
												id="sclass" name="className" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Class ID<span class="red">*</span></label> <input type="text"
											class="form-control intelliClass-0-classCode classCode" id="classCode" placeholder="Class Code"
											name="classCode"/>
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
								<input type="submit" class="btn btn-primary" value="Add" /> &nbsp;&nbsp;
								<a href="#" class="btn btn-primary" onClick="navTo('Class')">Edit</a> &nbsp;&nbsp;
								<a href="#" class="btn btn-primary" onClick="deleteNavTo('Class')">Delete</a>
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Section</h3>
						</div>
						<form role="form" action="saveSection" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Section <span class="red">*</span></label> 
											<input type="text"
												class="form-control sectionName" name="sectionName" placeholder="Section Name" style="cursor: text;" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Section ID <span class="red">*</span></label> <input type="text"
											class="form-control sectionCode" id="sectionCode" placeholder="Section Code" style="cursor: text;"
											name="sectionCode" />
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Add" /> &nbsp;&nbsp;
								<a href="#" class="btn btn-primary" onClick="navTo('Section')" >Edit</a>&nbsp;&nbsp;
								<a href="#" class="btn btn-primary" onClick="deleteNavTo('Section')">Delete</a>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			
			<div class="col-md-12" style="margin-top: 20px;">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Allocate Section Details</h3>
					</div>
					<form action="saveAllocatedSectionToClass" name="selection" method="post" onSubmit="return selectAll()"> 
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

							<!-- <div class="col-md-12">
								<div class="col-md-3">
									<div class="form-group">
										<label>Class </label> 
										<input type="text"
										class="form-control intelliClass-1-className stdClassHints" placeholder="Class Name"
										id="clsName" name="className" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Class ID</label> <input type="text"
									class="form-control intelliClass-1-classCode" id="clsCode" placeholder="Class Code" readonly="readonly"
									name="classCode"/>
								</div>
							</div> -->
							<div class="col-md-12">
								<div class="col-md-5">
									<div class="form-group">
										<label>Select Class </label> 
										<select id="clsCode" class="form-control" name="classCode">
<c:forEach var="cls" items="${Classes }">
											<option value="${cls.classCode }">${cls.className }</option>
</c:forEach>										
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-5">
									<div class="col-md-12 multiList"><font>All Section</font></div>
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
									<div class="col-md-12 multiList"><font>Selected Section</font></div>
							    	<select multiple id="to" class="form-control" size="10" name="selectedSection"></select>
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

	initializeHints("ALL");
	
 /* $('input, select, textarea').attr('disabled', 'disabled'); */
	
    $('#clsCode').on('change', function(e){
    	var selectedVal = $("select[name=schoolCode] option:selected").val();
    	$.ajax({
			   url : "fetchSectionInfoByClassForAllocateToClass",
			   data: "classCode=" + $('#clsCode').val(),
				//dataType : "json",
			   cache: false,
			   async: false,
			   success: function(data) {
			 	//called when successful
			 	   $('#from').empty();
			 	   $('#to').empty();
				   $.each(data.unAllocated, function( i, obj ) {
					   //alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					   $('#from').append('<option value=' + obj.sectionCode + '>' + obj.sectionName + '</option>');
				   });
				   $.each(data.allocated, function( i, obj ) {
					   //alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					   $('#to').append('<option value=' + obj.sectionCode + '>' + obj.sectionName + '</option>');
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
	if(action == 'Class')
	{
		sendRequest('editClass', 'post', {"classCode": $('[name="classCode"]').val()} );
	}
	else
	{
		sendRequest('editSection', 'post', {"sectionCode": $('[name="sectionCode"]').val()} )
	}
}

function deleteNavTo(action)
{
	if(action == 'Class')
	{
		sendRequest('deleteClass', 'post', {"classCode": $('[name="classCode"]').val()} );
	}
	else
	{
		sendRequest('deleteSection', 'post', {"sectionCode": $('[name="sectionCode"]').val()} )
	}
}
</script>


<%-- <%@ include file="../common/footer.jsp"%> --%>