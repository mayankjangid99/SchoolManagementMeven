<%-- <%@ include file="../common/header.jsp"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="st" %>
<div class="content-wrapper" style="min-height: 1066px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Application Settings<!-- <small>it all starts here</small> -->
		</h1>
	</section>

<%@ include file="../common/message.jsp" %>

	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">
			<div class="box-header">
				<h3 class="box-title">Add Application Settings</h3>
				<!-- <div class="col-md-6 pull-right">
					<a href="addPaymentCategory" class="btn btn-primary pull-right">Add Payment Category</a>
				</div> -->
			</div>
				
			
            <!-- /.box-header -->
            <div class="box-body">
              <div class="box-group" id="accordion">
                <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                <div class="panel box box-primary">
                  <div class="box-header with-border">
                    <h4 class="box-title">
                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" class="collapsed">
                        <i class="fa fa-envelope"></i> &nbsp;&nbsp;Email
                      </a>
                    </h4>
                  </div>
                  <div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true">
                    <div class="box-body">
                    	<div class="col-md-12">
				          <div class="col-md-4 form-group" title="<st:message code="ui.text.thisParameterUseSendEmailMarkStudentDetails" />" >
                    			<div class="col-md-9">
									<label>Student Details</label> 
								</div>
								<div class="col-md-3 switch" >
						            <input id="EMAIL_STUDENT_DETAILS" name="emailStudentDetails" value="N" class="cmn-toggle cmn-toggle-round-small" type="checkbox" />
						            <label for="EMAIL_STUDENT_DETAILS"></label>
				            	</div>
						        <input type="hidden" name="applicationSettingId" class="applicationSettingId" value="" />
				          </div>
                    	  <div class="col-md-4 form-group" title="<st:message code="ui.text.thisParameterUseSendEmailMarkStudentAttendance" />" >
                    			<div class="col-md-9">
									<label>Student Attendance</label> 
								</div>
								<div class="col-md-3 switch">
						            <input id="EMAIL_STUDENT_ATTENDANCE" name="emailStudentAttendance" value="N" class="cmn-toggle cmn-toggle-round-small" type="checkbox" />
						            <label for="EMAIL_STUDENT_ATTENDANCE"></label>
				            	</div>
						        <input type="hidden" name="applicationSettingId" class="applicationSettingId" value="" />
				          </div>
				          <div class="col-md-4 form-group" title="<st:message code="ui.text.thisParameterUseSendEmailStudentFeeReceipt" />">
                    			<div class="col-md-9">
									<label>Student Fee Receipt</label> 
								</div>
								<div class="col-md-3 switch">
						            <input id="EMAIL_STUDENT_FEE_RECEIPT" name="emailStudentFeeReceipt" value="N" class="cmn-toggle cmn-toggle-round-small" type="checkbox" />
						            <label for="EMAIL_STUDENT_FEE_RECEIPT"></label>
				            	</div>
						        <input type="hidden" name="applicationSettingId" class="applicationSettingId" value="" />
				          </div>
                      </div>
                      <div class="col-md-12">
						  <div class="col-md-4 form-group" title="<st:message code="ui.text.thisParameterUseSendEmailUserDetails" />">
                    			<div class="col-md-9">
									<label>User Details</label> 
								</div>
								<div class="col-md-3 switch">
						            <input id="EMAIL_USER_DETAILS" name="emailUserDetails" value="N" class="cmn-toggle cmn-toggle-round-small" type="checkbox" />
						            <label for="EMAIL_USER_DETAILS"></label>
				            	</div>
						        <input type="hidden" name="applicationSettingId" class="applicationSettingId" value="" />
				          </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="panel box box-danger">
                  <div class="box-header with-border">
                    <h4 class="box-title">
                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" class="collapsed" aria-expanded="false">
                        &nbsp;<i class="fa fa-mobile"></i> &nbsp;&nbsp;SMS
                      </a>
                    </h4>
                  </div>
                  <div id="collapseTwo" class="panel-collapse collapse" aria-expanded="false">
                    <div class="box-body">
                      Body
                    </div>
                  </div>
                </div>
                <div class="panel box box-success">
                  <div class="box-header with-border">
                    <h4 class="box-title">
                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" class="" aria-expanded="false">
                        <i class="fa fa-gears"></i> &nbsp;&nbsp;Other
                      </a>
                    </h4>
                  </div>
                  <div id="collapseThree" class="panel-collapse collapse" aria-expanded="false">
                    <div class="box-body">
                      Body
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- /.box-body -->
            
            
            
		</div>
	</section>
</div>

  <script>
  $(".switch").on("change", function () {
  		var switchEle = $(this).find('input');
		var switchVal = $(switchEle).val();
		var switchName = $(switchEle).attr('name');
		var applicationSettingId = $(switchEle).closest('.form-group').find('.applicationSettingId').val();
		var val = '';
		if(switchVal == "N"){
			$(switchEle).val('Y');
			$(switchEle).attr('checked', 'checked');
			val = 'Y';
		} else {
			$(switchEle).val('N');
			$(switchEle).removeAttr('checked');
			val = 'N';
		}
		var response = $(document).appProcessRequest({url: 'addOrChangeEmailSettings', data: switchName+'='+val+"&applicationSettingId="+applicationSettingId , role: 'response-link'});
		if(response == "SUCCESS"){
			var msg = new String("Email Settings Successfully saved, Please login again to use changed application settings...!!!");
			alert(msg);
		} else {
			alert("Sorry! Email Settings is not Successfully saved...!!!");
		}
	});
  
var emailValJson = {
<c:forEach var="settings" items="${ApplicationSettings}">
	'${settings.settingCode}': '${settings.settingValue}~${settings.applicationSettingId}',
</c:forEach> 
};
//console.log('emailValJson = '+emailValJson);
$.each(emailValJson, function(key,val){
	var splitData = val.split('~');
	var element = $("#"+key);
    $(element).val(splitData[0]);
    $(element).closest('.form-group').find('.applicationSettingId').val(splitData[1]);
});
  $('.cmn-toggle').each(function(i){
	  var val = $(this).val();
	  if(val == 'Y'){
		  $(this).attr('checked', 'checked');
	  } else {
		  $(this).val('N');
	  }
  });
  </script>