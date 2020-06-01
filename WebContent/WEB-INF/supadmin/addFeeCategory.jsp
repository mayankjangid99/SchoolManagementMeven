<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Fee Category <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 650px;">
			<div class="box-header">
				<h3 class="box-title">Add Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchFeeCategory" class="btn btn-primary pull-right">Search Fee Category</a>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Add Fee Category</h3>
						</div>
						<form role="form" action="saveFeeCategory" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Fee Category Name <span class="red">*</span></label> 
											<input type="text"
												class="form-control feeCategoryName" placeholder="Fee Category Name" style="cursor: text;"
												id="feeCategoryName" name="feeCategoryName" />
										</div>
									</div>
									<div class="col-md-4">
										<label>Category Code <span class="red">*</span></label> <input type="text"
											class="form-control feeCategoryCode" id="feeCategoryCode" placeholder="Category Code"
											name="feeCategoryCode"/>
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
							<h3 class="box-title">Edit/Delete Fee Category</h3>
						</div>
						<form role="form" action="editFeeCategory" method="post" class="formValid">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label>Fee Category <span class="red">*</span></label> 
											<select name="feeCategoryCode" class="form-control feeCategoryCode" id="feeCategoryCodeED">
												<option value="">-- Select --</option>
<c:forEach var="feeCategory" items="${FeeCategories }"><option value="${feeCategory.feeCategoryCode }">${feeCategory.feeCategoryName }</option></c:forEach>
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
						<h3 class="box-title">Allocate Fee Category to Class And Section</h3>
					</div>
					<form action="saveAllocatedFeeCategoryToClassSection" class="formValid" name="selection" method="post" onSubmit="return selectAll()"> 
						<div class="box-body">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="form-group">
										<label>Class <span class="red">*</span></label> 
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
								<div class="col-md-4">
									<div class="form-group">
										<label>Section <span class="red">*</span></label> <input type="text"
											class="form-control sectionName intelliClass-1-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
									</div>
								</div>
								<div class="col-md-2">
									<label>Section ID <span class="red">*</span></label> <input type="text"
										class="form-control intelliClass-1-sectionCode" placeholder="Section Code" id="sectionCode"
										name="sectionCode" readonly="readonly" />
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-3">
									<div class="form-group">
										<label>Fee Type <span class="red">*</span></label> 
										<select name="feeType" class="form-control feeType" id="feeType">
											<option value=""> -- Select -- </option>
											<option value="N">Non-Additional</option>
											<option value="A">Additional</option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Type <span class="red">*</span></label> 
										<select name="type" class="form-control type" id="type">
											<option value=""> -- Select -- </option>
											<option value="Y">Yearly</option>
<!-- 											<option value="H">Half-Yearly</option> -->
											<option value="M">Monthly</option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Fee Details <span class="red">*</span></label> 
										<select name="feeCategoryCode" class="form-control feeCategoryCode" id="feeDetails">
											<option value=""> -- Select -- </option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label>Amount <span class="red">*</span></label> 
										<input type="text" name="amount" class="form-control amount">
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-3 form-group">
									<label>Status <span class="red">*</span></label> 
									<select	class="form-control status" name="status">
										<%-- <option value="">-- <st:message code="ui.text.Select" /> --</option> --%>
										<option value="Y"><st:message code="ui.text.Active" /></option>
										<option value="N"><st:message code="ui.text.Inactive" /></option>
									</select>
								</div>
							</div>
							<!-- <div class="col-md-12">
								<div class=" col-md-5">
									<div class="col-md-12 multiList"><font>All Fee category</font></div>
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
									<div class="col-md-12 multiList"><font>Selected Fee category</font></div>
							    	<select multiple id="to" class="form-control" size="10" name="selectedFeeCategory"></select>
								</div>
						    </div> -->
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
    	var selectedVal = $("select[name=schoolCode] option:selected").val();
    	var type = $('#type option:selected').val();
    	if(!type)
    	{
    		alert("Please select a type...!!!");
    		return false;
    	}
    	if(!$('#clsCode').val())
    	{
    		alert("Please select a Class...!!!");
    		return false;
    	}
    	if(!$('#sectionCode').val())
    	{
    		alert("Please select a Section...!!!");
    		return false;
    	}
    	$.ajax({
			   url : "fetchFeeCategoryByClassForAllocateToClass",
			   data: "classCode=" + $('#clsCode').val() + "&sectionCode=" + $('#sectionCode').val() + "&type=" + type,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
			 	  $('#feeDetails').empty();
			 	  $('#feeDetails').append('<option value=""> -- Select -- </option>');
			 	  $.each(data.unAllocated, function( i, obj ) {
				  	//alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					$('#feeDetails').append('<option value=' + obj.feeCategoryCode + '>' + obj.feeCategoryName + '</option>');
				  });
			 	  /* $('#from').empty();
			 	  $('#to').empty();
				  $.each(data.unAllocated, function( i, obj ) {
				  	//alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					$('#from').append('<option value=' + obj.feeCategoryCode + '>' + obj.feeCategoryName + '</option>');
				  });
				  $.each(data.allocated, function( i, obj ) {
					//alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
					$('#to').append('<option value=' + obj.feeCategoryCode + '>' + obj.feeCategoryName + '</option>');
				  }); */
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
	if(!$('#feeCategoryCodeED').val()){
		return false;
	}
	if(action == 'Delete')
	{
		sendRequest('deleteFeeCategory', 'post', { "feeCategoryCode": $('[name="feeCategoryCode"] option:selected').val() } );
	}
}
</script>


<%-- <%@ include file="../common/footer.jsp"%> --%>