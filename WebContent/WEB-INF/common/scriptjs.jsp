<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <script src="${pageContext.request.contextPath}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script> --%>
 <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    

   <!-- jQuery 2.1.4 -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script> --%>
    <!-- jQuery UI 1.11.2 -->
<!--     <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script> -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->

	<!--     appPlugin -->
	<script src="${pageContext.request.contextPath}/static/js/appPlugin.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.form.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/additional-methods.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/valid.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/angular.min.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/smartHints.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/userSmartHints.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/common.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/ajax.js" type="text/javascript" ></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery.base64.js" type="text/javascript" ></script>
    
    
    <!-- Bootstrap 3.3.2 JS -->
    <%-- <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>   --%>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap-3.3.2.js" type="text/javascript"></script>    
    <!-- Morris.js charts -->
<!--     <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script> -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/morris/morris.min.js" type="text/javascript"></script> --%>
    <!-- Sparkline -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script> --%>
    <!-- jvectormap -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script> --%>
<%--     <script src="${pageContext.request.contextPath}/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script> --%>
    <!-- jQuery Knob Chart -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/knob/jquery.knob.js" type="text/javascript"></script> --%>
    <!-- daterangepicker -->
<!--     <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script> -->
	<script src="${pageContext.request.contextPath}/static/plugins/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <!-- datepicker -->
    <script src="${pageContext.request.contextPath}/static/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
	<!-- CK Editor -->
<%-- 	<script src="${pageContext.request.contextPath}/static/js/ckeditor.js"></script> --%>
    <!-- Bootstrap WYSIHTML5 -->
<%--     <script src="${pageContext.request.contextPath}/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script> --%>
    <!-- Slimscroll -->
    <script src="${pageContext.request.contextPath}/static/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='${pageContext.request.contextPath}/static/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath}/static/dist/js/app.min.js" type="text/javascript"></script>    
    
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<%--     <script src="${pageContext.request.contextPath}/static/dist/js/pages/dashboard.js" type="text/javascript"></script>     --%>
    
    <!-- AdminLTE for demo purposes -->
<%--     <script src="${pageContext.request.contextPath}/static/dist/js/demo.js" type="text/javascript"></script> --%>

<script>
	var errors = [];
<c:if test="${empty sessionScope.SessionManager.schoolProfiles }">
	errors.push({element:$(this), msg:'Please select one school profile...!!!'});
</c:if>
<c:if test="${empty sessionScope.SessionManager.schoolProfileUtils.schoolSessions }">
	errors.push({element:$(this), msg:'Please select one school session....!!!'});
</c:if>	
	if(errors.length > 0) {
		$.processMsg.init({errors:errors}, document.forms[0]);
	}
</script>
