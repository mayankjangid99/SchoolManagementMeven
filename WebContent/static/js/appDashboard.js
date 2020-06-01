		$.ajax({
			   url : "fetchActiveSessions",
			   //data: "switchSession=" + schoolSession,
				//dataType : "json",
				cache: false,
				async: false,
			   success: function(data) {
			 	//called when successful
				   console.log(data);
				   $(".totalActionSession").text(data.totalActivated);
				   $(".actionSession").text(data.active);
			   },
			   error: function(e) {
				   alert("error");
			   }
		}); 