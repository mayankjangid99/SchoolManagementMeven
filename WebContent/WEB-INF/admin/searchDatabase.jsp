<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper" style="height: 766px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Database/Table (Backup/Restore)<!-- <small>it all starts here</small> -->
		</h1>
	</section>
	
<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary" style="height: 700px;">
			<div class="box-header">
				<h3 class="box-title">Database Details</h3>
				<div class="col-md-6 pull-right">&nbsp;
<!-- 					<a href="searchClassSection" class="btn btn-primary pull-right">Search Class Section</a> -->
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Backup Database</h3>
						</div>
						<form role="form" action="" method="post" id="dbBackup">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Database <span class="red">*</span></label> 
											<select	class="form-control" name="database">
												<option value=""> --<st:message code="ui.text.Select" />-- </option>
		<c:forEach var="database" items="${Databases }"><option value="${database }">${database }</option></c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-3">
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary db" value="Result All" /> &nbsp;&nbsp;
								<input type="submit" class="btn btn-primary db" value="Download" /> &nbsp;&nbsp;
								<input type="submit" class="btn btn-primary db" value="Backup on Server" />
							</div>
						</form>
					</div>
				</div>
				
				
					
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Backup Table</h3>
						</div>
						<form role="form" action="editMarkAttendance" method="post" id="dbtbBackup" class="">
							<div class="box-body">
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Database <span class="red">*</span></label> 
											<select	class="form-control database" name="database">
												<option value=""> --<st:message code="ui.text.Select" />-- </option>
		<c:forEach var="database" items="${Databases }"><option value="${database }">${database }</option></c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-3">
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-9">
										<div class="form-group">
											<label>Table <span class="red">*</span></label> 
											<select	class="form-control tb" name="table" id="table">
												<option value=""> --<st:message code="ui.text.Select" />-- </option>
											</select>
										</div>
									</div>
									<div class="col-md-3">
									</div>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary tb" value="Download" /> &nbsp;&nbsp;
								<input type="submit" class="btn btn-primary tb" value="Backup on Server" />
							</div>
						</form>
					</div>
				</div>
				
			
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Restore Database</h3>
						</div>
						<form role="form" action="restoreDatabase" method="post" enctype="multipart/form-data" class="">
							<div class="box-body">
								<div class="col-md-12">
									<fieldset class="form-group">
										<label>Database File <span class="red">*</span></label>
										<div style="display: table;" class="dbParent">
											<span class="btn btn-primary btn-file btn-xs" style="width: 120px;height: 30px;padding: 5px;">
												<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
												<input type="file" name="databaseFile" onchange="loadFile(event)" />
											</span>
											<font id="output"></font>
										</div>
									</fieldset>
								</div>
						
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Upload File..." />
							</div>
						</form>
					</div>
				</div>
				
				
				<div class="col-md-6">
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Restore Table</h3>
						</div>
						<form role="form" action="restoreTable" method="post" id="dbtbRestore" enctype="multipart/form-data">
							<div class="box-body">
									<div class="col-md-9">
										<div class="form-group">
											<label>Database <span class="red">*</span></label> 
											<select	class="form-control database" name="database">
												<option value=""> --<st:message code="ui.text.Select" />-- </option>
<c:forEach var="database" items="${Databases }"><option value="${database }">${database }</option></c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-3">
									</div>
								
								<div class="col-md-12">
									<fieldset class="form-group">
										<label>Database File <span class="red">*</span></label>
										<div style="display: table;" class="dbParent">
											<span class="btn btn-primary btn-file btn-xs" style="width: 120px;height: 30px;padding: 5px;">
												<span class="fa fa-upload"></span> &nbsp;&nbsp;&nbsp;Browse... 
												<input type="file" name="databaseFile" onchange="loadFile2(event)" />
											</span>
											<font id="output2"></font>
										</div>
									</fieldset>
								</div>
							</div>
							<!-- /.box-body -->
			
							<div class="box-footer">
								<input type="submit" class="btn btn-primary" value="Upload File..." />
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

	//initializeHints();
	
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
var loadFile = function(event) {
	console.log(event.target.files[0].name);
	$('#output').html(event.target.files[0].name);
   	//var output = document.getElementById('output');
   	//output.src = URL.createObjectURL(event.target.files[0]);
};

var loadFile2 = function(event) {
	console.log(event.target.files[0].name);
	$('#output2').html(event.target.files[0].name);
   	//var output = document.getElementById('output');
   	//output.src = URL.createObjectURL(event.target.files[0]);
};

$("input[type=submit].db").on("click", function(e){
	var btnVal = $(this).val();
	var form = $(this).closest("form");
	if(btnVal == "Result All")
	{
		form.attr("action", "resultDatabase");
	}
	else if(btnVal == "Download")
	{
		form.attr("action", "downloadDatabaseBackupDirect");
	}
	else
	{
		form.attr("action", "createDatabaseBackup");
	}
	//e.preventDefault();
});

$("input[type=submit].tb").on("click", function(e){
	var btnVal = $(this).val();
	var form = $(this).closest("form");
	if(btnVal == "Backup on Server")
	{
		form.attr("action", "createTableBackup");
	}
	else if(btnVal == "Download")
	{
		form.attr("action", "downloadTableBackupDirect");
	}
	//e.preventDefault();
});

$('.database').on('change', function(e){
	$.ajax({
		   url : "getTablesByDatabase",
		   data: "database=" + $('.database option:selected').val(),
			//dataType : "json",
			cache: false,
			async: false,
		   success: function(data) {
		 	//called when successful
		 	   $('.tb').empty();
		 	   $('.tb').append('<option value=""> --Select-- </option>');
			   $.each(data, function( i, obj ) {
				   //alert( "Key: " + obj.sectionCode + ", Value: " + obj.sectionName );
				   $('.tb').append('<option value=' + obj + '>' + obj + '</option>');
			   });
		   },
		   error: function(e) {
			   alert("error");
		   }
		 });
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>