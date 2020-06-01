<%-- <%@ include file="../common/header.jsp"%> --%>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Add Micro Service Configuration <!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Micro Service Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="searchMicroServiceConfig" class="btn btn-primary pull-right">Search Micro Service Config</a>
				</div>
			</div>
			<form role="form" action="saveMicroServiceConfig" method="post" class="globalConfig formValid" id="">
				<div class="box-body">
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Code <span class="red">*</span></label> 
<!-- 								<input type="text" class="form-control configCode" id="configCode" name="configCode" placeholder="Code" /> -->
									<select name="configCode" class="form-control configCode">
										<option value="">-Select-</option>
<c:forEach var="config" items="${MicroServiceConfigParameters }">
										<option value="${config.key }">${config.value }</option>
</c:forEach>										
									</select>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Value<span class="red">*</span></label> 
								<textarea class="form-control configValue" name="configValue" placeholder="Value" rows="2" ></textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Description<span class="red">*</span></label> 
								<textarea class="form-control configDescription" name="configDescription" placeholder="Description" rows="3" ></textarea>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</form>
		</div>
	</section>
</div>

<script>
$(document).ready(function(){

// 	initializeHints();
// 	$('.attnDate').datepicker({format: 'dd-mm'});
// $('input, select, textarea').attr('disabled', 'disabled');
	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>