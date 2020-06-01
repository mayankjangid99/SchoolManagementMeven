$(document).ready(function () {
	
	//This is generic function.
	function findPropertyNameForElement(thisObject){	
		var propertyName = null;
		var className = thisObject.attr('class');
		if(className){
			var classNames = className.split(' ');
			for(var i = 0; i < classNames.length; i++){
				if(classNames[i].match("^intelli")){
					elementClass = classNames[i];
					break;
				}
			}
			var parts = elementClass.split('-');
			if(parts && parts.length > 0){
				propertyName = parts[0]+"-"+parts[1];
			}
		}
	    
	    return propertyName;
	}
	
	

//	 SignIn Ajax
	 $('.SignInn').click(function() {
		   var email = $('input[name=username]').val();
		   var password = $('input[name=password]').val();
		  // console.log(email+'---'+password+'------');
		  
		   $.ajax({
			   url : jContextPath+"/Login",
			   data:"email="+email+"&password="+password,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
				   var dataRes = data.split('~');
				   //console.log(dataRes[0]);
				   if(dataRes[0] == 'success')
					   {
					   		window.location = jContextPath + '/' + dataRes[1] + '/welcome';
					   }
				   else
					   {
					   		$('.login-box-msg').text('');
					   		$('.login-box-msg').css({'color': 'red', 'font-style': 'italic'});
					   		$('.login-box-msg').text(data);
					   }
			   },
			   error: function(e) {
				   alert("error");
			   }
			 });  
	   });
	 
	 
	 	
	 $('.checkin').click(function(e) {
		 var status = $(this).attr("data-type"); 
		 var resData = "";
		   $.ajax({
			   url : "checkInUser",
			   data: "status="+status,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
				   //console.log(data);
				   resData = data;
			   },
			   error: function(e) {
				   alert("error");
			   }
			 }); 
		   $(this).attr("data-type", resData.status);
		   $(".checkinTime").text(resData.checkIn);
		   $(".checkoutTime").text(resData.checkOut);
		   if(resData.status == "checkIn")
		   {
			   $(this).css("color", "#dd4b39 !important")
			  //$(this).text("Check In");   
		   }
		   else
		   {
			   $(this).css("color", "#3c8dbc !important")
			   //$(".checkoutTime").text("Check Out");    
		   }
			 e.preventDefault();
	   });

//	 Forgot Password by Ajax
		 $('#forgotPassword').click(function() {
			 var username = $("#forgotUsername").val();
			 var email = $("#forgotEmail").val();
			 var forgotMsg = $(".forgotMeg");
			 forgotMsg.html("&nbsp;");
			   $.ajax({
				   url : "forgotPassword",
				   data: "username=" + username + "&email=" + email,
					//dataType : "json",
					cache: false,
					async: false,
				   success: function(data) {
				 	//called when successful
					   //console.log(data);
					   var status = data["status"];
					   var message = data["message"];
					   if(status == "success")
						   forgotMsg.css("color", "green");
					   else
						   forgotMsg.css("color", "red");
					   forgotMsg.html(message);
				   },
				   error: function(e) {
					   alert("error");
				   }
				 }); 
			   
		   });
		 
		 
		var schoolListElement = $(".school-list");
		var schoolDetailsElement = $(schoolListElement).children("ul");
		if(schoolListElement.length > 0)
		{
			$.ajax({
				url : "getActiveSchoolWithAllSchool",
				data: "",
				type : "post",
				//dataType : "json",
				cache: false,
				async: false,
				success: function(data) {
				//called when successful
				//console.log(data);
				$(schoolListElement).children("a").html(data.activeSchoolCode + ' <span class="caret"></span>');
				$.each(data.schoolProfileJsons, function(i, item){
					schoolDetailsElement.append('<li><a href="#" class="school-select" data-app-proj-value="'+item.schoolCode+'"><span class="sbu-code">'+item.schoolCode+'</span> <span class="sbu-code">'+item.name+'</span></a></li>');
				});
				
				},
				error: function(e) {
					alert("error");
				}
			}); 
		}
		
		$(".school-select").on("click", function(e){
			var schoolCode = $(this).attr("data-app-proj-value");
			$.ajax({
				url : "changeActiveSchoolProfile",
				data: "schoolCode=" + schoolCode,
				type : "post",
				//dataType : "json",
				cache: false,
				async: false,
				success: function(data) {
				//called when successful
				//console.log(data);
					window.location.reload();
				},
				error: function(e) {
					alert("error");
				}
			}); 
		});
			
			
			
	 
//	 Switch School Session by Ajax
	/* $('.schoolSession').click(function() {
		 var schoolSession = $(this).text(); 
		
		   $.ajax({
			   url : "switchSchoolSession",
			   data: "switchSession=" + schoolSession,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
				   //console.log(data);
				   window.location = "welcome";
			   },
			   error: function(e) {
				   alert("error");
			   }
			 }); 
		   
	   });*/
	 
	 
});