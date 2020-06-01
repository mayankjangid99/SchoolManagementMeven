<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	
	<title>405</title>
	
	<!-- stylecss Page -->
	
<%@ include file="WEB-INF/common/stylecss.jsp" %>
	
<script type="text/javascript">
    	var jContextPath='${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
</head>
<body class="skin-blue sidebar-mini">
    <div class="wrapper">
<c:if test="${not empty sessionScope.SessionManager }">
	<%@ include file="WEB-INF/common/header.jsp" %>
	<%@ include file="WEB-INF/common/menu.jsp" %>
    <div class="content-wrapper" style="height: 404px;">
</c:if>
<c:if test="${empty sessionScope.SessionManager }">
      <div class="content-wrapper" style="width: 100%; margin-left: 0px;">
</c:if>

        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            405 Error Page
          </h1>
          <!-- <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">Examples</a></li>
            <li class="active">405 error</li>
          </ol> -->
        </section>

        <!-- Main content -->
        <section class="content">

          <div class="error-page">
            <h4 style="text-align: center;">Requested Method is not matching...</h4>
            <h2 class="headline text-yellow" style="font-size: 200px; margin-left: 25%; margin-top: 10%;"> 405</h2>
            <!-- <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>
              <p>
                We could not find the page you were looking for.
                Meanwhile, you may <a href='../../index.html'>return to dashboard</a> or try using the search form.
              </p>
              <form class='search-form'>
                <div class='input-group'>
                  <input type="text" name="search" class='form-control' placeholder="Search"/>
                  <div class="input-group-btn">
                    <button type="submit" name="submit" class="btn btn-warning btn-flat"><i class="fa fa-search"></i></button>
                  </div>
                </div>/.input-group
              </form>
            </div>/.error-content -->
          </div><!-- /.error-page -->
        </section><!-- /.content -->
        </div>
        <%@ include file="WEB-INF/common/footer.jsp" %>
        <%@ include file="WEB-INF/common/rightMenu.jsp" %>
    <div class='control-sidebar-bg'></div>
    </div>
</body> 
<%@ include file="WEB-INF/common/scriptjs.jsp" %>
</html>