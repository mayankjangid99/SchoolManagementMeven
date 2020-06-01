<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/static/plugins/iCheck/flat/blue.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/static/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
<a href="graphIndex">Graph Index</a>		
		<div class="appModal">
            <div class="modal fade app-modal-primary" id="myModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Forgot Password</h4>
                  </div>
                  <div class="modal-body">
                  	<div style="color: green; font-size: 15px; margin-bottom: 10px;" class="forgotMeg">&nbsp;</div>
                    <div class="col-md-12">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                    	<div class="form-group has-feedback">
			            	<input name="username" class="form-control" placeholder="Username" type="text" id="forgotUsername">
			            	<span class="glyphicon glyphicon-user form-control-feedback"></span>
			          	</div>
                    </div>
                    </div>
                    <div class="col-md-12">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                    	<div class="form-group has-feedback">
			            	<input name="email" class="form-control" placeholder="Email" type="text" id="forgotEmail">
			            	<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
			          	</div>
                    </div>
                    </div>
			          	<span style="font-size: 12px; color: grey;">Note: System will send a mail on registered email.</span>
                  </div>
                  <div class="modal-footer">
                    <!-- <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button> -->
                    <button type="button" class="btn btn-primary" id="forgotPassword">Forgot Password</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
          </div>
          
          
    <div class="login-box">
      <!-- <div class="login-logo">
        <b>Admin INF</b>
      </div> --><!-- /.login-logo -->
      <div class="login-box-body">
<c:if test="${not empty error}">
			<p class="" style="color: red; font-style: italic; text-align: center;">
				Your login attempt was not successful, try again.<br/>
<c:if test="${error ne 'true' }">
				 Or <br/>
				${error }
</c:if>
<%-- 				<br /> Caused :	${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} --%>
			</p>
</c:if> 
<c:if test="${empty error}">
			<p class="login-box-msg">Sign in to start your session</p>
</c:if>      
<!--         <p class="login-box-msg">Sign in to start your session</p> -->
		<form action="<c:url value='login' />" method="post" name="j">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
			<c:if test="${not empty uimg and uimg ne 'null'}">
				<div class="text-center uimgLogin"
					style="background-image: url('${pageContext.request.contextPath}/upload/user_images/${uimg}');">
				</div>
			</c:if>
			<c:if test="${empty uimg or uimg eq 'null' }">
				<div class="text-center uimgLogin"
					style="background-image: url('${pageContext.request.contextPath}/static/img/profile.png');">
				</div>
				<%-- <img src="${pageContext.request.contextPath}/static/img/profile.png" alt="" style="height: 150px;"> --%>
			</c:if>
			<br>
			<div class="form-group has-feedback">
				<input type="text" name="username" class="form-control"
					placeholder="Username" /> <span
					class="glyphicon glyphicon-user form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input type="password" name="password" class="form-control"
					placeholder="Password" /> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<label> <input type="checkbox" name="remember" value="Y">
							Remember Me
						</label>
					</div>
				</div>
				<!-- /.col -->
				<div class="col-xs-4">
					<!--               <button type="button" class="btn btn-primary btn-block btn-flat SignIn">Sign In</button> -->
					<input type="submit"
						class="btn btn-primary btn-block btn-flat SignIn" value="Sign In" />
				</div>
				<!-- /.col -->
			</div>
		</form>

		<!-- <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google-plus btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
        </div> --><!-- /.social-auth-links -->

        <a href="#" data-toggle="modal" data-target="#myModal">Forgot Password</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

	
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/static/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
	 <script>
      $(function () {
			$('body').removeClass().addClass('login-page');
        	$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
      });
    </script>
