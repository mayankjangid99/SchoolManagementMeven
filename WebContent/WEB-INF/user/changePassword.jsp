<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>          

<c:if test="${not empty sessionScope.SessionManager }">
    <div class="content-wrapper" style="min-height: 1066px;">
</c:if>
<c:if test="${empty sessionScope.SessionManager }">
      <div class="content-wrapper" style="width: 100%; margin-left: 0px;">
</c:if>
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Change Password
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
          

    <div class="login-box" style="margin: 3% auto;">
      <!-- <div class="login-logo">
        <b>Admin INF</b>
      </div> --><!-- /.login-logo -->
      <div class="login-box-body"> 
			<p class="login-box-msg">Change Password</p>
        <form action="updatePassword" method="post" id="updatePassword">
        <c:if test="${not empty User.userDetails.image }">
        <div class="text-center uimgLogin" style="background-image: url('${pageContext.request.contextPath}/upload/user_images/${User.userDetails.image}');">	
        </div>
        </c:if>
        <c:if test="${empty User.userDetails.image }">
        <div class="text-center uimgLogin" style="background-image: url('${pageContext.request.contextPath}/static/img/profile.png');">	
        </div>
        <%-- <img src="${pageContext.request.contextPath}/static/img/profile.png" alt="" style="height: 150px;"> --%>
        </c:if><br>
        <input type="hidden" name="username" value="${User.username }" />
        <input type="hidden" name="userDetails.email" value="${User.userDetails.email }" />
          <div class="form-group has-feedback">
            <input type="password" name="password" class="form-control" id="password" placeholder="Password"/>
            <!-- <span class="glyphicon glyphicon-lock form-control-feedback"></span> -->
          </div>
          <div class="form-group has-feedback">
            <input type="password" name="repassword" class="form-control" placeholder="Re-Password"/>
            <!-- <span class="glyphicon glyphicon-lock form-control-feedback"></span> -->
          </div>
          <div class="row">
            <div class="col-xs-6">                         
            </div><!-- /.col -->
            <div class="col-xs-6">
<!--               <button type="button" class="btn btn-primary btn-block btn-flat SignIn">Sign In</button> -->
              <input type="submit" class="btn btn-primary btn-block btn-flat" value="Change Password" />
            </div><!-- /.col -->
          </div>
        </form>

        <!-- <a href="#" data-toggle="modal" data-target="#myModal">I forgot my password</a><br>
        <a href="register.html" class="text-center">Register a new membership</a> -->

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->


	</section>
</div>

    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/static/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
	 <script>
      $(function () {
    	  var queryString = '<%= request.getQueryString() %>';
    	if(queryString != "null")
    	{
  			$('body').removeClass().addClass('login-page');
  		}
        	$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
      });
    </script>
