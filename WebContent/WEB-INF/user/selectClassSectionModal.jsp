<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="appProjModalBase">
	<form action="" method="post" data-app-proj-role="modal-form" id="sendEmailModal" class="formValid">
		<div class="modal fade app-modal-primary" id="appProjModal">
			<div class="modal-dialog small">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">Select Class and Section</h4>
					</div>
					<div class="modal-body">
					<%@ include file="../common/message.jsp" %>
						<div class="col-md-12">
							<div class="col-md-9">
								<div class="form-group">
									<label>Class <span class="red">*</span></label> 
									<input type="text" class="form-control className intelliClass-50-className stdClassHints" id="sclass" name="className" placeholder="Class Name" />
								</div>
							</div>
							<div class="col-md-3">			
								<div class="form-group">
									<label>Class ID <span class="red">*</span></label> 
									<input type="text" class="form-control intelliClass-50-classCode" id="classCode" name="classCode" readonly="readonly" />
								</div>
							</div>
						</div>
					
						<div class="col-md-12">
							<div class="col-md-9">
								<div class="form-group">
									<label>Section <span class="red">*</span></label> 
									<input type="text" class="form-control sectionName intelliClass-50-sectionName stdSectionHints" name="sectionName" placeholder="Section Name" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Section ID <span class="red">*</span></label> 
									<input type="text" class="form-control intelliClass-50-sectionCode" id="sectionCode" name="sectionCode" readonly="readonly" />
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value="Submit"/>
						<button type="button" class="btn btn-cancel" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
	</form>

<script>
$(document).ready(function(){

	initializeHints();
	
});
var queryString = '${Action }';
var actionArray = queryString.split('&');
var action = actionArray[0].substring(7, actionArray[0].length);
queryString = queryString.substring(actionArray[0].length+1, queryString.length);
//alert("action = "+action +" ~queryString="+queryString);
if(queryString)
	action = action + "?" +queryString;
$('#sendEmailModal').attr('action', action);
</script>
<!-- /.modal -->
</div>