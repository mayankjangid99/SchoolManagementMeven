<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <c:if test="${not empty ErrorMessage }"> --%>
	<div class="box-body hidden app-proj-error" style="padding: 0px;">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="closeMsg" data-dismiss="alert"
				aria-hidden="true">×</button>
			<h4>
				<i class="icon fa fa-ban"></i> Error...!
			</h4>
			<ul>
				<li>${ErrorMessage }</li>
			</ul>
		</div>
	</div>
<%-- </c:if> --%>

<%-- <c:if test="${not empty SuccessMessage }"> --%>
	<div class="box-body hidden app-proj-success" style="padding: 0px;">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="closeMsg" data-dismiss="alert"
				aria-hidden="true">×</button>
			<h4>
				<i class="icon fa fa-check"></i> Success...!
			</h4>
			<ul>
				<li>${SuccessMessage }</li>
			</ul>
		</div>
	</div>
<%-- </c:if> --%>

<%-- <c:if test="${not empty WarningMessage }"> --%>
	<div class="box-body hidden app-proj-warning" style="padding: 0px;">
		<div class="alert alert-warning alert-dismissable">
			<button type="button" class="closeMsg" data-dismiss="alert"
				aria-hidden="true">×</button>
			<h4>
				<i class="icon fa fa-warning"></i> Warning...!
			</h4>
			<ul>
				<li>${WarningMessage }</li>
			</ul>
		</div>
	</div>
<%-- </c:if> --%>

<%-- <c:if test="${not empty InfoMessage }"> --%>
	<div class="box-body hidden app-proj-info" style="padding: 0px;">
		<div class="alert alert-info alert-dismissable">
			<button type="button" class="closeMsg" data-dismiss="alert"
				aria-hidden="true">×</button>
			<h4>
				<i class="icon fa fa-info"></i> Information...!
			</h4>
			<ul>
				<li>${InfoMessage }</li>
			</ul>
		</div>
	</div>
<%-- </c:if> --%>