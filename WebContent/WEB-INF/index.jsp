<%-- <%@page import="com.school.common.use.StrongAES"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>





<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>

<%-- <%!
Map<String, String> parseAndReadmailConfigProperties(String mailServerConfig)
{
	Map<String, String> mailConfigProperties = null;

	if (mailServerConfig != null && !mailServerConfig.trim().equals("")) {
		String[] tempServerDetail = mailServerConfig.trim().split(";");
		if (tempServerDetail != null) {
			mailConfigProperties = new Hashtable<>();
			for (String tempStr : tempServerDetail) {
				String[] property = tempStr.split("=");
				if (property[0] != null && !property[0].trim().equals("")) {
					if (property[1] == null || property[1].trim().equals("")) {
						/* if (log.isDebugEnabled()) {
							//log.debug("The value for configuration paramter {} is empty or null.", property[0]);
						} */
						mailConfigProperties.put(property[0].trim(), null);
					} else {
						mailConfigProperties.put(property[0].trim(), property[1].trim());
					}
				}
			} // End of for loop
			/* if (log.isDebugEnabled()) {
				log.debug("The mail configuration values are {}", mailConfigProperties);
			} */
		}
	}
	return mailConfigProperties;
}
%> --%>

<%
/* String cookieFlag = "N";
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies)
{
	if("SMUser".equalsIgnoreCase(cookie.getName()))
	{
		cookieFlag = "Y";
		String cookieVal = "";
		System.out.println("Cookie found");
	}
}
if("N".equalsIgnoreCase(cookieFlag))
{
	System.out.println("Cookie not found");
} */
System.out.println("Cookie not found");
response.sendRedirect("indexPage"); 
%>



<%-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>Admin | Log in</title>
    
     <script type="text/javascript">
    	var jContextPath='${pageContext.request.contextPath}';
    	var urlSecurity = "<c:url value='j_spring_security_check' />";
 	</script>
 	
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/static/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/static/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="login-page">
    <div class="login-box">
    <c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
      <div class="login-logo">
        <a href="#"><b>Admin Web</b></a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        <form action="<c:url value='j_spring_security_check' />" method="post" name="j">
          <div class="form-group has-feedback">
            <input type="text" name="username" class="form-control" placeholder="Username"/>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">    
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"> Remember Me
                </label>
              </div>                        
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="button" class="btn btn-primary btn-block btn-flat SignIn">Sign In</button>
              <input type="submit" class="btn btn-primary btn-block btn-flat SignIn" value="Sign In" />
            </div><!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google-plus btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
        </div><!-- /.social-auth-links -->

        <a href="#">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath}/static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/static/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/ajax.js" type="text/javascript" ></script>
    <script>
      $(function () {
        
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });

          
//         var a="MonuKumar"
        <%String s=StrongAES.encrypt("a"); %>
        var en = '<%= s %>';
// 		alert(en);
		
		<%String decrpt = StrongAES.decrypt(s); %>
		var d=<%= decrpt %>;
// 		alert(d); 
      });
    </script>
  </body>
</html> --%>