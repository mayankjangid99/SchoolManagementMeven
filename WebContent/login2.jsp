<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
 <style>
#login h1 {
    color: #1fa67b;
    font-size: 18px;
    text-align: center;
    font-weight: bold;
    padding-bottom: 20px;
}
#login .form-group {
    margin-bottom: 25px;
}
#login .checkbox {
    margin-bottom: 20px;
    position: relative;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    -o-user-select: none;
    user-select: none;
}
#login .checkbox.show:before {
    content: '\e013';
    color: #1fa67b;
    font-size: 17px;
    margin: 1px 0 0 3px;
    position: absolute;
    pointer-events: none;
    font-family: 'Glyphicons Halflings';
}
#login .checkbox .character-checkbox {
    width: 25px;
    height: 25px;
    cursor: pointer;
    border-radius: 3px;
    border: 1px solid #ccc;
    vertical-align: middle;
    display: inline-block;
}
#login .checkbox .label {
    color: #6d6d6d;
    font-size: 13px;
    font-weight: normal;
}
#login .btn.btn-custom {
    font-size: 14px;
	margin-bottom: 20px;
}
#login .forget {
    font-size: 13px;
	text-align: center;
	display: block;
}

/*    --------------------------------------------------
	:: Inputs & Buttons
	-------------------------------------------------- */
.forget {outline :0 !important;}
.form-control {
    color: #212121;
}
.btn-custom {
    color: #fff;
	background-color: #1fa67b;
}
.btn-custom:hover,
.btn-custom:focus {
    color: #fff;
}

/*    --------------------------------------------------
    :: Footer
	-------------------------------------------------- */
#footer {
    color: #6d6d6d;
    font-size: 12px;
    text-align: center;
}
#footer p {
    margin-bottom: 0;
}
#footer a {
    color: inherit;
}
.hideshow{
display:block;
}
.btnstyle{
	margin-left:180px;
	margin-right:20px;
}

  </style>
</head>
<body>

<div class="container">
<section id="login">
  <h2>Modal Example</h2>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
	  <div class="form-wrap">
        <div class="modal-header">
          <h1 class="modal-title">Login</h1>
        </div>
        <div class="modal-body">
			<div class="slideuphide">
				<form role="form" action="javascript:;" method="post" id="login-form" autocomplete="off">
				<select name="colors" class="form-control" title="Login As...">
					<option data-hidden="true" style="font-weight:lighter;">Login As...</option>
					<option value="admin">Admin</option>
					<option value="teacher">Teacher</option>
					<option value="student">Student</option>
					<option value="staff">Staff</option>
					<option value="parents">Parents</option>
					
				</select>
				</br>
                        <div class="input-group">
							<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
						</span>
                            <label for="username" class="sr-only">UserName/Mobile No</label>
                            <input type="text" name="username" id="username" class="form-control" placeholder="username or your moblile number">
						
                        </div>
				</br>
						
                        <div class="input-group">
							<span class="input-group-addon">
							<span class="glyphicon glyphicon-lock"></span>
							</span>
                            <label for="key" class="sr-only">Password</label>
                            <input type="password" name="key" id="key" class="form-control" placeholder="Password">
                        </div>
                        <div class="checkbox">
							<span class="character-checkbox" onclick="showPassword()"></span>
                            <span class="label">Show password</span>
                        </div>
                        <input type="submit" id="btn-login" class="btn btn-custom btn-lg btn-block" value="Log in">
            </form>
			</div>
			 <a href="javascript:void(0);" class="forget" data-toggle="modal" data-target=".forget-modal">Forgot your password?</a>
             <div class="forgetemail" style="display:none">
			 	<p>Type your email account</p>
				
					<div class="input-group">
						<span class="input-group-addon">
						<span class="glyphicon glyphicon-envelope"></span>
						</span>
						<input type="email" name="recovery-email" id="recovery-email" class="form-control" autocomplete="off" placeholder="somebody@gmail.com"></br>
					</div>
				</br>
				 <button type="button" class="btn btn-primary btn-success btnstyle" data-dismiss="modal">Recovery</button>
				 <button type="button" class="btn btn-primary btn-info" data-dismiss="modal">Cancel</button>
		    </div>
        </div>
		
        <div class="modal-footer">
		
          <button type="button" class="btn btn-primary btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
	  </div>
      
    </div>
  </div>
  </section>
</div>
<script>

$(document).ready(function(){
    $(".forget").click(function(){
	$(".forgetemail").css('display','block');
	$('.slideuphide').slideUp( 1500, function() {
		});
    });
    
    $("button[data-dismiss~=modal]").click(function(){
    	$(".forgetemail").css('display','none');
    	$(".slideuphide").css('display','block');
    });
});
function showPassword() {
    
    var key_attr = $('#key').attr('type');
    
    if(key_attr != 'text') {
        
        $('.checkbox').addClass('show');
        $('#key').attr('type', 'text');
        
    } else {
        
        $('.checkbox').removeClass('show');
        $('#key').attr('type', 'password');
        
    }
    
}

</script>
</body>
</html>
