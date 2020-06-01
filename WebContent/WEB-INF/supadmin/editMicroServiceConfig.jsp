<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			<c:if test="${Operation eq 'View'}">${Operation }</c:if> <c:if test="${Operation ne 'View'}">Edit</c:if> Micro Service Configuration 
			<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>
	
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Micro Service Config Details</h3>
				<div class="col-md-6 pull-right">
					<a href="addMicroServiceConfig" class="btn btn-primary pull-right">Add Micro Service Config</a>
					<a href="searchMicroServiceConfig" class="btn btn-primary pull-right">Search Micro Service Config</a>
				</div>
			</div>
			<form role="form" action="updateMicroServiceConfig" method="post" class="globalConfig formValid" id="">
				<div class="box-body">
					<div class="col-md-8">
						<div class="col-md-6">
							<div class="form-group">
								<label>Code <span class="red">*</span></label> 
								<%-- <input type="text"
									class="form-control configCode"
									id="configCode" name="configCode" placeholder="Code" readonly="readonly" value="${MicroServiceConfig.configCode }"/> --%>
								
									<select name="configCode" class="form-control configCode">
										<option value="">-Select-</option>
<c:forEach var="config" items="${MicroServiceConfigParameters }">
										<option value="${config.key }" <c:if test="${config.key eq MicroServiceConfig.configCode }"> selected="selected" </c:if>>${config.value }</option>
</c:forEach>										
									</select>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Value <span class="red">*</span></label> 
								<textarea class="form-control configValue" name="configValue" placeholder="Value" rows="2" >${MicroServiceConfig.configValue }</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-8">
							<div class="form-group">
								<label>Description <span class="red">*</span></label> 
								<textarea class="form-control configDescription" name="configDescription" placeholder="Description" rows="3" >${MicroServiceConfig.configDescription }</textarea>
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
	<c:if test="${Operation eq 'View'}">
		$('input, select, textarea').attr('disabled', 'disabled');
	</c:if>	
});
</script>

<%-- <%@ include file="../common/footer.jsp" %> --%>