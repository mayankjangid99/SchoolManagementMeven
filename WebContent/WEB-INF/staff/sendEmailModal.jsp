<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="appProjModalBase">
<script>
var queryString = '${Action }';
var actionArray = queryString.split('&');
var action = actionArray[0].substring(7, actionArray[0].length);
queryString = queryString.substring(actionArray[0].length+1, queryString.length);
//alert("action = "+action +" ~queryString="+queryString);
$('#sendEmailModal').attr('action', action + "?" +queryString);
</script>
		<div class="modal fade app-modal-primary" id="appProjModal">
			<div class="modal-dialog medium">
				<form action="" method="post" data-app-proj-role="modal-form" id="sendEmailModal" class="formValid">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<h4 class="modal-title"><i class="fa fa-envelope" style="font-size: 18px;"></i> &nbsp;&nbsp;Send Email</h4>
						</div>
						<div class="modal-body">
							<div class="row hidden app-proj-error">
								<div class="alert alert-danger alert-dismissable">
									<button type="button" class="closeMsg" data-dismiss="alert" aria-hidden="true">×</button>
									<h5><i class="icon fa fa-ban"></i> Error...!</h5>
									${ErrorMessage }
								</div>
							</div>
							<div class="row hidden app-proj-success">
								<div class="alert alert-success alert-dismissable">
									<button type="button" class="closeMsg" data-dismiss="alert" aria-hidden="true">×</button>
									<h5><i class="icon fa fa-check"></i> Success...!</h5>
									${SuccessMessage }
								</div>
							</div>
							<div class="row hidden app-proj-warning">
								<div class="alert alert-warning alert-dismissable">
									<button type="button" class="closeMsg" data-dismiss="alert" aria-hidden="true">×</button>
									<h5><i class="icon fa fa-warning"></i> Warning...!</h5>
									${WarningMessage }
								</div>
							</div>
							<div style="color: green; font-size: 15px; margin: 10px;" class="modalMessage"></div>
								<div class="col-md-12 form-group">
									<div class="col-md-3">
										<label>Recipient <span class="red">*</span></label>
									</div>
									<div class="col-md-9">
										<input type="email" name="recipient" class="form-control recipient" value="${param.emailTo }" />
									</div>
								</div>
								<div class="col-md-12 form-group">
									<div class="col-md-3">
										<label>CC-Recipient</label>
									</div>
									<div class="col-md-9">
										<input type="email" name="ccRecipient" class="form-control" />
									</div>
								</div>
								<div class="col-md-12 form-group">
									<div class="col-md-3">
										<label>BCC-Recipient</label>
									</div>
									<div class="col-md-9">
										<input type="email" name="bccRecipient" class="form-control" />
									</div>
								</div>
								<div class="col-md-12 form-group">
									<div class="col-md-3">
										<label>Subject <span class="red">*</span></label>
									</div>
									<div class="col-md-9">
										<input type="text" name="subject" class="form-control subject" />
									</div>
								</div>
								<div class="col-md-12 form-group">
									<div class="col-md-3">
										<label>Message <span class="red">*</span></label>
									</div>
									<!-- <div class="col-md-9">
										<textarea id="editor1" name="message" class="form-control message textarea" ></textarea>
									</div> -->
								</div>
	 							<div class="col-md-12 form-group">
	 								<div class="col-md-12">
	 									<textarea id="editor1" name="message" class="form-control" ></textarea>
	 									</div>
								</div>
						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-primary" value="Submit"/>
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</form>
			</div>
			<!-- /.modal-dialog -->
		</div>
<script src="${pageContext.request.contextPath}/static/plugins/ckeditor/ckeditor.js"></script>
<script>
CKEDITOR.replace('editor1');

$('input[type=button]').on('click', function(e){
	var form = $(this).closest('form');
	var valid = $(form).valid();
	if(valid){
		e.preventDefault();
		var emailMsg = $('#editor1').parent().find('iframe').contents().find('body').html();
		$('#editor1').val(emailMsg);
		var url = $(form).attr('action');
		var data = $(form).serialize();
		var response = $(form).appProcessRequest({url: url, data: data, role: 'response-link', asyncProcessing: false});
		response = JSON.parse(response);
		if(response.hasError){
			var errorDiv = $('#appProjModal').find('.app-proj-error');
			$(errorDiv).removeClass('hidden');
			if((errorDiv).children().length > 0){
				if($(errorDiv).find('ul').length > 0)
					$(errorDiv).find('ul').empty();
				$(errorDiv).children().append('<ul><li>'+response.Message+'</li></ul>');
			} else {
				var msgdiv = '<div class="alert alert-error alert-dismissable">'
							+'<button type="button" class="closeMsg" data-dismiss="alert" aria-hidden="true">×</button>'
							+'<h5><i class="icon fa fa-ban"></i> Error...!</h5><ul><li>'+response.Message+'</li></ul></div>';
				$(errorDiv).append(msgdiv);
			}
		} else {
			var successDiv = $('#appProjModal').find('.app-proj-success');
			$(successDiv).removeClass('hidden');
			if((successDiv).children().length > 0){
				if($(successDiv).find('ul').length > 0)
					$(successDiv).find('ul').empty();
				$(successDiv).children().append('<ul><li>'+response.Message+'</li></ul>');
			} else {
				var msgdiv = '<div class="alert alert-success alert-dismissable">'
							+'<button type="button" class="closeMsg" data-dismiss="alert" aria-hidden="true">×</button>'
							+'<h5><i class="icon fa fa-check"></i> Success...!</h5><ul><li>'+response.Message+'</li></ul></div>';
				$(successDiv).append(msgdiv);
			}
		}
	}
});
</script>	
</div>
<!-- /.modal -->