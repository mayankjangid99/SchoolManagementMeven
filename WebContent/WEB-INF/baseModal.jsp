<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="appProjModalBase">
	<div class="modal fade app-modal-primary" id="appProjModal">
		<div class="modal-dialog medium">
			<form action="" class="form-horizontal" data-app-proj-form="modal-form">
				<div style="display: none;">
				</div>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title">Modal Title</h4>
					</div>
					<div class="modal-body">
					
					<!-- Display Message  -->
					<%@ include file="./common/message.jsp" %>
					
					body here
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary modalSubmit formValidBtn" value="Save" />
						<button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</form>
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- /.modal -->

<script>
$.processMsg.init({errors:[]}, document.forms[1]);
</script>
</div>