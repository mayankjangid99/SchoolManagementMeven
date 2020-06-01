<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>

<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Search Micro Service Config <!-- <small>it all starts here</small> -->
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
				</div>
			</div>
			<form role="form" action="resultMicroServiceConfig" method="post" class="form">
				<div class="box-body">
					<div class="col-md-6">
						<div class="col-md-8">
							<div class="form-group">
								<label>Micro Service Config <!-- <span class="red">*</span> --></label> 
								<!-- <input type="text"
									class="form-control" placeholder="MicroService Config Code"
									id="globalCode" name="id.configCode" /> -->
									
									<select name="configCode" class="form-control">
										<option value="">-Select-</option>
<c:forEach var="config" items="${MicroServiceConfigParameters }">
										<option value="${config.key }">${config.value }</option>
</c:forEach>										
									</select>
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
	//initializeHints();
		
});
</script>

<%-- <%@ include file="../common/footer.jsp"%> --%>