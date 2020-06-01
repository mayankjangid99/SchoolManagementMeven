<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="userProfile" value="${sessionScope.SessionManager.userProfileUtils }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <meta charset="UTF-8">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	
	<!-- stylecss Page -->
	<tiles:insertAttribute name="stylecss" />

<script type="text/javascript">
    	var jContextPath='${pageContext.request.contextPath}';
</script>
<c:set var="myContextPath" value="${pageContext.request.contextPath}" />
<script src="${pageContext.request.contextPath}/static/js/jquery-2.1.4.min.js"></script>
</head>

<body class="sidebar-mini ${userProfile.theme }">
<section class="loaderSection">
    <div class="loaders">
      <div class="loader">
        <div class="loader-inner ball-triangle-path">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
    </div>
</section>
    <div class="wrapper" id="content-container">
    
	    <!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- End Header -->
		
		<!-- Menu -->
		<tiles:insertAttribute name="menu" />
		<!-- End Menu -->
		
		<!-- Body Page -->
		<tiles:insertAttribute name="body" />
		<!-- End Body Page -->
		
		<!-- Footer Page -->
		<tiles:insertAttribute name="footer" />
		<!-- End Footer Page -->
		
		<!-- Right Menu Page -->
		<tiles:insertAttribute name="rightMenu" />
		<!-- End Right Menu Page -->
	
    </div>
    
</body>
		<!-- scriptjs Page -->
		<tiles:insertAttribute name="scriptjs" />
		<!-- End scriptjs Page -->
</html>
